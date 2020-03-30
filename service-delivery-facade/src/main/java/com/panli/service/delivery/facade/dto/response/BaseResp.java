package com.panli.service.delivery.facade.dto.response;

import lombok.Data;

import java.util.Date;

@Data
public class BaseResp {
    private Integer id;
    private Date createdDateTime;
    private Integer createdUserId;
    private Date lastModifiedUserId;
    private Date lastModifiedDateTime;
    private Boolean isDeleted;
}
