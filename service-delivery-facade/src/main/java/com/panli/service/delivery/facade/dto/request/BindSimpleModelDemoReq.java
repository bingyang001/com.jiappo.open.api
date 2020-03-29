package com.panli.service.delivery.facade.dto.request;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Collection;

/**
 * @author bingy
 */
@Data
public class BindSimpleModelDemoReq implements Serializable {
    private static final long serialVersionUID = -1984904147335519793L;
    @NotEmpty(message = "name can't null")
    private String name;
    @NotEmpty(message = "code can't null")
    @Min(value = 1)
    private Integer code;
    @NotNull(message = "collection can't null")
    private Collection<Integer> collection;
}
