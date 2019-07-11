package com.jiappo.open.api.support.model.bo;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/11 10:04
 **/
@Builder
@Data
public class TicketVerifiedResultBo {
    private int ticketId;
    private int verifiedStatus;
    private String verifiedDescribe;
    private Date verifiedDate;
}
