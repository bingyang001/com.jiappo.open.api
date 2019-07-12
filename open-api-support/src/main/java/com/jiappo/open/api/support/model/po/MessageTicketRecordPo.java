package com.jiappo.open.api.support.model.po;

import lombok.Data;

import java.util.Collection;
import java.util.Date;
import java.util.Map;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/10 16:16
 **/
@Data
public class MessageTicketRecordPo {

    private Integer id;
    private String platformName;
    private String messageType;
    private String ticketValue;
    private String ticketType;
    private Integer verifiedStatus;
    private Integer expiredDate;
    private Date verifiedDate;
    private Date createdDate;
    private String verifiedDescribe;
    private String messageId;
    private String ticketMd5;
    private Boolean isDelete;
    private Boolean isPreCreated;
    private Collection<Map<String,Object>> dataBody;
}
