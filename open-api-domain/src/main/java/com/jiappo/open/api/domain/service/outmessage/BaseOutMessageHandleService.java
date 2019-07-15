package com.jiappo.open.api.domain.service.outmessage;

import com.google.common.eventbus.EventBus;
import com.hummer.common.http.HttpAsyncClient;
import com.hummer.common.http.RequestCustomConfig;
import com.jiappo.open.api.domain.entity.OutMessageRule;
import com.jiappo.open.api.domain.event.OutMessageEvent;
import com.jiappo.open.api.domain.service.OutMessageHandle;
import com.jiappo.open.api.support.model.dto.out.OutMessageReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * send to out of platform the message logic handle template
 * ,child class need implement {@link OutMessageHandle} all method
 *
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/12 17:47
 **/
public abstract class BaseOutMessageHandleService implements OutMessageHandle {
    private final static Logger LOGGER = LoggerFactory.getLogger(BaseOutMessageHandleService.class);
    @Autowired
    private EventBus eventBus;

    /**
     * <p>
     * this method is handle message logic template,only work call child class
     * method {@link OutMessageHandle#builderBody(OutMessageReq, OutMessageRule)}
     * ,{@link OutMessageHandle#builderHeader(OutMessageReq, OutMessageRule)}
     * ,{@link OutMessageHandle#builderUrl(OutMessageReq, OutMessageRule)}
     * builder {@link RequestCustomConfig} after send message to out of platform and write log
     * </p>
     *
     * @param req  message
     * @param rule rule
     * @return java.lang.String
     * @author liguo
     * @date 2019/7/15 14:50
     * @since 1.0.0
     **/
    public String handle(final OutMessageReq req, final OutMessageRule rule) {
        final String postForm = "postform";
        boolean isPostForm = postForm.equalsIgnoreCase(rule.getTargetHttpMethod());
        String localMethod = isPostForm ? "post" : rule.getTargetHttpMethod();

        RequestCustomConfig config = RequestCustomConfig.builder()
                .setRequestBody(builderBody(req, rule))
                .setSocketTimeOutMillisecond(rule.getCallTargetTimeoutMillisecond())
                .setHeaders(builderHeader(req, rule))
                .setMethod(RequestMethod.valueOf(localMethod.toUpperCase()))
                .setUrl(builderUrl(req, rule))
                .build();

        final String httpMethod = "get";
        String result = null;
        long start = System.currentTimeMillis();
        try {
            if (httpMethod.equalsIgnoreCase(rule.getTargetHttpMethod())) {
                result = HttpAsyncClient.instance().sendGet(config);
            } else {
                result = HttpAsyncClient.instance().send(config);
            }
            eventBus.post(OutMessageEvent.success(req
                    , rule
                    , config
                    , System.currentTimeMillis() - start
                    , result));
            LOGGER.info("out message handle done,message id is {}", req.getBatchId());
            return result;
        } catch (Throwable throwable) {
            LOGGER.error("out message handle exception,message id is {},body is {},rule is {}",
                    req.getBatchId()
                    , req
                    , rule
                    , throwable);
            eventBus.post(OutMessageEvent.failed(req
                    , rule
                    , config
                    , System.currentTimeMillis() - start
                    , throwable));
            throw throwable;
        }
    }
}
