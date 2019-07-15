package com.jiappo.open.api.domain.entity;

import com.jiappo.open.api.support.model.po.MessageRulePo;
import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * out of platform name send to jiappo site message rule
 *
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/12 15:18
 **/
@Data
public class InMessageRule {
    private String messageSource;
    private String messageType;
    private String targetPlatformName;
    private String targetHttpApi;
    private String targetHttpMethod;
    private Integer callTargetTimeoutMillisecond;
    private Boolean inMessageResponseSnapshotData;
    private Date activateBeginTime;
    private Date expiredTime;
    private String secretKey;
    private String ticketImplService;
    private Map<String, Object> ticketFieldMap;
    private Integer ticketExpiredTimeMinute;
    private String privateKey;
    private String publicKey;
    private Boolean isPreCreated;
    private String routeImplService;


    public InMessageRule(final MessageRulePo rulePo) {
        this.messageSource = rulePo.getPlatformName();
        this.messageType = rulePo.getMessageType();
        this.targetPlatformName = rulePo.getTargetPlatformName();
        this.targetHttpApi = rulePo.getTargetHttpApi();
        this.targetHttpMethod = rulePo.getTargetHttpMethod();
        this.callTargetTimeoutMillisecond = rulePo.getCallTargetTimeoutMillisecond();
        this.inMessageResponseSnapshotData = rulePo.getInMessageResponseSnapshotData();
        this.activateBeginTime = rulePo.getActivateBeginTime();
        this.expiredTime = rulePo.getExpiredTime();
        this.secretKey = rulePo.getSecretKey();
        this.ticketImplService = rulePo.getTicketImplService();
        this.ticketFieldMap = rulePo.getTicketField();
        this.ticketExpiredTimeMinute = rulePo.getTicketExpiredTimeMinute();
        this.privateKey = rulePo.getPrivateKey();
        this.publicKey = rulePo.getPublicKey();
        this.isPreCreated = rulePo.getIsPreCreated();
        this.routeImplService = rulePo.getRouteImplService();
    }
}
