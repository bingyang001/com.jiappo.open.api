package com.jiappo.open.api.support.model.po;

import lombok.Data;

import java.util.Date;
import java.util.Map;

/**
 * @Author: lee
 * @version:1.0.0
 * @Date: 2019/6/27 16:57
 **/
@Data
public class MessageTransferRoutePo {
    private Integer id;
    private String platformName;
    private String messageType;
    private String targetHttpApi;
    private String  targetHttpMethod;
    private Date createTime;
    private Date lastModifiedTime;
    private Integer createUserId;
    private Integer lastModifiedUserId;
    private Byte messageRouteType;
    private Boolean isDeleted;
    private Date activateBeginTime;
    private Date expiredTime;
    private Integer callTargetTimeoutMillisecond;
    private String routeImplService;
    private String secretKey;
    private String signImplService;
    private String publicKey;
    private String privateKey;
    private Map<String,Object> signField;
    private Integer signExpiredTimeMinute;
}
