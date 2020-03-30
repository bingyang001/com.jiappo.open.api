package com.panli.service.delivery.support.model.po;

import lombok.Data;

import java.util.Date;

/**
 * @author bingy
 */
@Data
public class BasePo {
    private Integer id;
    private Date createdDateTime;
    private Integer createdUserId;
    private Date lastModifiedUserId;
    private Date lastModifiedDateTime;
    private Boolean isDeleted;
}
