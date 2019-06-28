package com.jiappo.open.api.support.model.dto.in;

import com.jiappo.open.api.support.model.dto.BaseOpenApiResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author: lee
 * @version:1.0.0
 * @Date: 2019/6/27 15:45
 **/
@Data
@ApiModel(description = "all in site message response.")
public class InMessageResp extends BaseOpenApiResponse {
    private static final long serialVersionUID = 5330727003870949689L;
    @ApiModelProperty(value = "business response data")
    private Object data;
}
