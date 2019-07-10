package com.jiappo.open.api.support.model.po;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/10 16:16
 **/
@Data
@Builder
public class MessageTicketRecordPo {
    private Integer id;
    private String platformName;
    private String messageType;
    private String signValue;
    private String signType;
    private Integer verifiedStatus;
    private Integer expiredDate;
    private Date verifiedDate;
    private Date createdDate;
    private String verifiedDescribe;
    private String messageId;
}
