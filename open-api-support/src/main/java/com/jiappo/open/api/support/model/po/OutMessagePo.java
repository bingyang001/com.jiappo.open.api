package com.jiappo.open.api.support.model.po;

import lombok.Data;

import java.util.Date;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/15 13:51
 **/
@Data
public class OutMessagePo {
    private Integer id;
    private String messageSource;
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
    private Date createTime;
}
