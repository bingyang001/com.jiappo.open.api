package com.jiappo.open.api.support.model.po;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;


/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/15 10:13
 **/
@Data
@Getter
@Setter
public class InMessagePo {
    private Integer id;
    private String platformName;
    private String messageId;
    private String messageType;
    private String messageBody;
    private String sign;
    private String secretKey;
    private Date createDateTime;
    private String innerServiceHttpApi;
    private Integer innerServiceRespCode;
    private String innerServiceRespMessage;
}
