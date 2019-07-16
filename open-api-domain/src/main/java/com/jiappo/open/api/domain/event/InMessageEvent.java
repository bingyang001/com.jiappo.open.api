package com.jiappo.open.api.domain.event;

import com.alibaba.fastjson.JSON;
import com.hummer.common.exceptions.SysException;
import com.jiappo.open.api.domain.entity.InMessageRule;
import com.jiappo.open.api.support.model.dto.inmessage.InMessageReq;
import lombok.Builder;
import lombok.Data;


/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/15 10:41
 **/
@Builder
@Data
public class InMessageEvent {

    private String messageSource;
    private String messageId;
    private String messageType;
    private String messageBody;
    private String sign;
    private String secretKey;
    private String innerServiceHttpApi;
    private Integer innerServiceRespCode;
    private String innerServiceRespMessage;
    private Boolean isResponseSnapshot;

    /**
     * create message event
     *
     * @param inMessageReq message
     * @param po           po
     * @return com.jiappo.open.api.domain.event.InMessageEvent
     * @author liguo
     * @date 2019/7/15 10:56
     * @since 1.0.0
     **/
    public static InMessageEvent createMessageEvent(final InMessageReq inMessageReq
            , final InMessageRule po
            , Object responseBody) {
        return InMessageEvent.builder().messageSource(inMessageReq.getMessageSource())
                .messageId(inMessageReq.getMessageId())
                .messageBody(JSON.toJSONString(inMessageReq.getData()))
                .messageType(inMessageReq.getMessageType())
                .sign(inMessageReq.getSign())
                .secretKey(inMessageReq.getSecretKey())
                .innerServiceHttpApi(po.getTargetHttpApi())
                .innerServiceRespCode(0)
                .innerServiceRespMessage(JSON.toJSONString(responseBody))
                .isResponseSnapshot(po.getInMessageResponseSnapshotData())
                .build();
    }

    /**
     * create message event
     *
     * @param inMessageReq message
     * @param po           po
     * @param throwable    throwable
     * @return com.jiappo.open.api.domain.event.InMessageEvent
     * @author liguo
     * @date 2019/7/15 11:30
     * @since 1.0.0
     **/
    public static InMessageEvent createMessageEvent(final InMessageReq inMessageReq
            , final InMessageRule po
            , final Throwable throwable) {
        int code = 50000;
        if (throwable instanceof SysException) {
            code = ((SysException) throwable).getCode();
        }
        return InMessageEvent.builder().messageSource(inMessageReq.getMessageSource())
                .messageId(inMessageReq.getMessageId())
                .messageBody(JSON.toJSONString(inMessageReq.getData()))
                .sign(inMessageReq.getSign())
                .secretKey(inMessageReq.getSecretKey())
                .innerServiceHttpApi(po.getTargetHttpApi())
                .messageType(inMessageReq.getMessageType())
                .isResponseSnapshot(po.getInMessageResponseSnapshotData())
                .innerServiceRespCode(code)
                .innerServiceRespMessage(throwable.toString())
                .build();
    }
}
