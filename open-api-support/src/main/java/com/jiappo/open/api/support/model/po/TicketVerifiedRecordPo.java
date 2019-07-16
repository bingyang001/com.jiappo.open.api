package com.jiappo.open.api.support.model.po;

import lombok.Data;

import java.util.Date;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/11 15:49
 **/
@Data
public class TicketVerifiedRecordPo {
    private Integer id;
    private String messageSource;
    private String messageType;
    private String messageId;
    private String ticketValue;
    private String ticketType;
    private Integer verifiedStatus;
    private String verifiedDescribe;
    private Date verifiedDate;
    private String secretKey;
}
