package com.jiappo.open.api.support.model.dto.in;

import com.jiappo.open.api.support.model.dto.BaseOpenApiRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.util.Collection;
import java.util.Map;

/**
 * create user message
 *
 * @Author: lee
 * @version:1.0.0
 * @Date: 2019/6/27 15:39
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "all in site message.")
public class InMessageReq extends BaseOpenApiRequest {
    private static final long serialVersionUID = -1184824199882253607L;

    /**
     * body sign
     **/
    @NotEmpty(message = "sign cant null")
    private String sign;
    /**
     * secret key
     */
    @ApiModelProperty(hidden = true)
    private String secretKey;
}
