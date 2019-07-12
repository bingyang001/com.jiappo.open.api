package com.jiappo.open.api.domain.entity;

import com.hummer.common.exceptions.AppException;
import com.jiappo.open.api.support.model.po.MessageRulePo;
import lombok.Getter;

import java.util.Map;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/12 15:18
 **/
@Getter
public class OutMessageRule {
    private String platformName;
    private String messageType;
    private String targetHttpApi;
    private String targetHttpMethod;

    private Integer callTargetTimeoutMillisecond;
    private String secretKey;
    private String publicKey;
    private String privateKey;
    private Map<String, Object> ticketField;
    private String ticketImplService;

    /**
     * builder out message rule
     *
     * @param rulePo rule
     * @return
     * @throws AppException if rule po is null then throw this exception
     * @author liguo
     * @date 2019/7/12 18:47
     * @since 1.0.0
     **/
    public OutMessageRule(final MessageRulePo rulePo) {
        if (rulePo == null) {
            throw new AppException(40000, "rule not exists");
        }

        OutMessageRule rule = new OutMessageRule();
        rule.platformName = rulePo.getPlatformName();
        rule.messageType = rulePo.getMessageType();
        rule.targetHttpApi = rulePo.getTargetHttpApi();
        rule.targetHttpApi = rulePo.getTargetHttpApi();
        rule.targetHttpMethod = rulePo.getTargetHttpMethod();
        rule.callTargetTimeoutMillisecond = rulePo.getCallTargetTimeoutMillisecond();
        rule.secretKey = rulePo.getSecretKey();
        rule.publicKey = rulePo.getPublicKey();
        rule.privateKey = rulePo.getPrivateKey();
        rule.ticketField = rulePo.getTicketField();
        rule.ticketImplService = rulePo.getTicketImplService();
    }

    private OutMessageRule() {

    }
}
