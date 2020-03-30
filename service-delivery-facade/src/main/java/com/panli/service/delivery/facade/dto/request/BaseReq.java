package com.panli.service.delivery.facade.dto.request;

import lombok.Data;

import java.util.Date;

@Data
public class BaseReq {
    private Integer id;
    private Date createdDateTime;
    private Integer createdUserId;
    private Date lastModifiedUserId;
    private Date lastModifiedDateTime;
    private Boolean isDeleted;
}
