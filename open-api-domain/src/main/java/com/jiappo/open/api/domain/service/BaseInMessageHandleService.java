package com.jiappo.open.api.domain.service;

import com.alibaba.fastjson.TypeReference;
import com.google.common.base.Strings;
import com.google.common.eventbus.EventBus;
import com.hummer.common.exceptions.AppException;
import com.hummer.common.http.HttpAsyncClient;
import com.hummer.common.http.RequestCustomConfig;
import com.hummer.rest.model.ResourceResponse;
import com.jiappo.open.api.domain.eventbus.VerifiedSecretEvent;
import com.jiappo.open.api.domain.eventbus.VerifiedSignEvent;
import com.jiappo.open.api.support.model.dto.in.InMessageReq;
import com.jiappo.open.api.support.model.po.MessageRulePo;
import com.jiappo.open.api.support.model.po.MessageTicketRecordPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * in site message route
 *
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/2 17:30
 **/
public abstract class BaseInMessageHandleService implements InMessageHandle {
    private final static Logger LOGGER = LoggerFactory.getLogger(BaseInMessageHandleService.class);
    @Autowired
    private EventBus eventBus;
    @Autowired
    private MessageTicketService ticketService;
    /**
     * impl general verified logic
     *
     * @param inMessageReq out site request message
     * @param po           route
     * @return void
     * @author liguo
     * @date 2019/7/2 18:45
     * @since 1.0.0
     **/
    @Override
    public void verified(InMessageReq inMessageReq, MessageRulePo po) {
        //
        verifiedPrecondition(inMessageReq, po);
        //verified secret key
        try {
            verifiedSecretKey(inMessageReq.getPlatformName(), inMessageReq.getSecretKey(), po.getSecretKey());
            eventBus.post(VerifiedSecretEvent
                    .success(inMessageReq));
        } catch (Throwable throwable) {
            eventBus.post(VerifiedSecretEvent
                    .failed(inMessageReq,throwable));
            throw throwable;
        }
        //verified request body sign data
        try {
            verifiedSign(inMessageReq, po);
            eventBus.post(VerifiedSignEvent
                    .success(inMessageReq));
        } catch (Throwable throwable) {
            eventBus.post(VerifiedSignEvent
                    .failed(inMessageReq,throwable));
            throw throwable;
        }
    }

    private void verifiedPrecondition(InMessageReq inMessageReq, MessageRulePo po) {
        //verify request and route
        if (inMessageReq == null || po == null) {
            LOGGER.warn("request body can not null and route can not null.");
            throw new AppException(ErrorConstant.SignError.DATA_ERROR, ErrorConstant.SignError.DATA_ERROR_DOC);
        }

        if (Strings.isNullOrEmpty(inMessageReq.getSign())) {
            LOGGER.warn("sign can not null.");
            throw new AppException(ErrorConstant.SignError.SIGN_ERROR
                    , ErrorConstant.SignError.SIGN_ERROR_DOC);
        }

        if (Strings.isNullOrEmpty(inMessageReq.getSecretKey())) {
            LOGGER.warn("secret can not null.");
            throw new AppException(ErrorConstant.SignError.SECRET_KEY_ERROR
                    , ErrorConstant.SignError.SECRET_KEY_ERROR_DOC);
        }

        Date now = new Date();
        if (po.getActivateBeginTime() != null && now.before(po.getActivateBeginTime())) {
            LOGGER.warn("api authorize is no begin");
            throw new AppException(ErrorConstant.SignError.NO_ACTIVATE_ERROR
                    , ErrorConstant.SignError.NO_ACTIVATE_ERROR_DOC);
        }

        if (po.getExpiredTime() != null && now.after(po.getExpiredTime())) {
            LOGGER.warn("api authorize already expired");
            throw new AppException(ErrorConstant.SignError.ALREADY_EXPIRED_ERROR
                    , ErrorConstant.SignError.AUTHORIZE_ALREADY_EXPIRED_DOC);
        }
    }

    /**
     * verified request secret key ,if failed then throw app exception. child class can override
     *
     * @param platformName    platform name
     * @param reqSecretKey    request secret key
     * @param originSecretKey db origin secret key
     * @return void
     * @author liguo
     * @date 2019/7/2 18:42
     * @since 1.0.0
     **/
    protected abstract void verifiedSecretKey(String platformName, String reqSecretKey, String originSecretKey);

    /**
     * verified request sign
     *
     * @param inMessageReq request data
     * @param po           route type
     * @return void
     * @author liguo
     * @date 2019/7/2 18:56
     * @since 1.0.0
     **/
    protected abstract void verifiedSign(InMessageReq inMessageReq, MessageRulePo po);

    /**
     * child class need impl transfer logic
     *
     * @param po
     * @param req
     * @return java.lang.Object
     * @author liguo
     * @date 2019/7/2 17:59
     * @since 1.0.0
     **/
    @Override
    @SuppressWarnings("unchecked")
    public Object transfer(MessageRulePo po, InMessageReq req) {
        //if use snapshot data then return ticket record data body
        if(po.getInMessageResponseSnapshotData()){
            MessageTicketRecordPo ticketRecordPo = ticketService.queryTicket(req.getSign());
            return ticketRecordPo.getDataBody();
        }

        if (Strings.isNullOrEmpty(po.getTargetHttpApi())) {
            LOGGER.warn("platform {} message  type {} no settings target url,can not send message"
                    , req.getPlatformName()
                    , req.getMessageType());
            return null;
        }

        RequestCustomConfig config = RequestCustomConfig.builder()
                .setMethod(RequestMethod.POST)
                .setRequestBody(req.getData())
                .setUrl(po.getTargetHttpApi())
                .setSocketTimeOutMillisecond(po.getCallTargetTimeoutMillisecond())
                .build();

        ResourceResponse response = HttpAsyncClient.instance()
                .send(config, new TypeReference<ResourceResponse>() {
                });
        LOGGER.info("send message to {},response {}", po.getTargetHttpApi(), response);
        return response;
    }
}
