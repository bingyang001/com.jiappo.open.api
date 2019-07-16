package com.jiappo.open.api.support.model.dto.outmessage;

import com.jiappo.open.api.support.model.dto.BaseOpenApiRequest;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @Author: lee
 * @version:1.0.0
 * @Date: 2019/6/27 15:45
 **/
@EqualsAndHashCode(callSuper = true)
@Data
@ApiModel(description = "all out site message.")
public class OutMessageReq  extends BaseOpenApiRequest {
    private static final long serialVersionUID = -3614144896084355653L;
}
