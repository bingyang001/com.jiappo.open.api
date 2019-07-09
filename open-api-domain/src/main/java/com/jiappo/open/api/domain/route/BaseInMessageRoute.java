package com.jiappo.open.api.domain.route;

import com.alibaba.fastjson.TypeReference;
import com.google.common.base.Strings;
import com.hummer.common.exceptions.AppException;
import com.hummer.common.http.HttpAsyncClient;
import com.hummer.common.http.RequestCustomConfig;
import com.hummer.rest.model.ResourceResponse;
import com.jiappo.open.api.domain.sign.MessageSignFactory;
import com.jiappo.open.api.support.model.dto.in.InMessageReq;
import com.jiappo.open.api.support.model.po.MessageTransferRoutePo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Date;

/**
 * in site message route
 *
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/2 17:30
 **/
public abstract class BaseInMessageRoute implements Route {
    private final static Logger LOGGER = LoggerFactory.getLogger(BaseInMessageRoute.class);

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
    public void verified(InMessageReq inMessageReq, MessageTransferRoutePo po) {
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

        //verified secret key,if exist db Secret key
        verifiedSecretKey(inMessageReq.getPlatformName(), inMessageReq.getSecretKey(), po.getSecretKey());
        //verified request body sign data
        verifiedSign(inMessageReq, po);
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
    protected void verifiedSecretKey(String platformName, String reqSecretKey, String originSecretKey) {
        if (Strings.isNullOrEmpty(originSecretKey)) {
            LOGGER.warn("please settings secret key in db.");
            return;
        }

        if (!originSecretKey.equals(reqSecretKey)) {
            LOGGER.error("{} request secret key not match", platformName);
            throw new AppException(ErrorConstant.SignError.SECRET_KEY_NO_MATCH_ERROR
                    , ErrorConstant.SignError.SECRET_KEY_NO_MATCH_ERROR_DOC);
        }
    }

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
    protected abstract void verifiedSign(InMessageReq inMessageReq, MessageTransferRoutePo po);

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
    public Object transfer(MessageTransferRoutePo po, InMessageReq req) {
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
