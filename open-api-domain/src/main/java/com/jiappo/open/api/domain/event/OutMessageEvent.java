package com.jiappo.open.api.domain.event;

import com.alibaba.fastjson.JSON;
import com.hummer.common.exceptions.SysException;
import com.hummer.common.http.RequestCustomConfig;
import com.jiappo.open.api.domain.entity.OutMessageRule;
import com.jiappo.open.api.support.model.dto.out.OutMessageReq;
import lombok.Builder;
import lombok.Getter;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/15 14:22
 **/
@Builder
@Getter
public class OutMessageEvent {
    private String messageSource;
    private String innerServiceApi;
    private String messageId;
    private String messageType;
    private String messageBody;
    private String outPlatformName;
    private String outPlatformHttpApiHead;
    private String outPlatformHttpApi;
    private String outPlatformHttpMethod;
    private Integer outPlatformResponseCode;
    private String outPlatformResponseMessage;
    private Integer outPlatformResponseCostTime;


    public static OutMessageEvent success(final OutMessageReq req
            , final OutMessageRule rule
            , final RequestCustomConfig customConfig
            , final Long costTime
            , String responseBody) {
        return OutMessageEvent
                .builder()
                .messageSource(req.getPlatformName())
                .messageId(req.getBatchId())
                .messageType(req.getMessageType())
                .messageBody(JSON.toJSONString(req.getData()))
                .outPlatformHttpApi(customConfig.getUrl())
                .outPlatformHttpMethod(customConfig.getMethod().name())
                .outPlatformHttpApiHead(JSON.toJSONString(customConfig.getHeaders()))
                .outPlatformResponseCostTime(costTime.intValue())
                .outPlatformResponseMessage(responseBody)
                .outPlatformName(rule.getTargetPlatformName())
                .build();
    }


    public static OutMessageEvent failed(final OutMessageReq req, final OutMessageRule rule
            , final RequestCustomConfig customConfig
            , final Long costTime
            , Throwable throwable) {

        int code = 50000;
        if (throwable instanceof SysException) {
            code = ((SysException) throwable).getCode();
        }

        return OutMessageEvent
                .builder()
                .messageSource(req.getPlatformName())
                .messageId(req.getBatchId())
                .messageType(req.getMessageType())
                .messageBody(JSON.toJSONString(req.getData()))
                .outPlatformHttpApi(customConfig.getUrl())
                .outPlatformHttpMethod(customConfig.getMethod().name())
                .outPlatformHttpApiHead(JSON.toJSONString(customConfig.getHeaders()))
                .outPlatformResponseCostTime(costTime.intValue())
                .outPlatformResponseCode(code)
                .outPlatformName(rule.getTargetPlatformName())
                .outPlatformResponseMessage(throwable.toString())
                .build();
    }
}
