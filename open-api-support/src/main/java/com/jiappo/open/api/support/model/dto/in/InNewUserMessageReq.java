package com.jiappo.open.api.support.model.dto.in;

import com.jiappo.open.api.support.model.dto.BaseOpenApiRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Collection;
import java.util.Map;
import org.hibernate.validator.constraints.NotEmpty;
/**
 * create user message
 *
 * @Author: lee
 * @version:1.0.0
 * @Date: 2019/6/27 15:39
 **/
@Data
@ApiModel(description = "new user message in to jiappo service.")
public class InNewUserMessageReq extends BaseOpenApiRequest {
    private static final long serialVersionUID = -1184824199882253607L;
    /***
     * out of service platform name
     **/
    private String platformName;
    /***
     * out of service user identity
     **/
    @NotEmpty(message="user id can not empty.")
    @ApiModelProperty(value = "platform user identity.")
    private String userId;
    /***
     * out of service user name
     **/
    @ApiModelProperty(value = "platform user name.")
    private String name;
    /***
     * extend message use array
     **/
    @ApiModelProperty(value = "backup filed")
    private Collection<Map<String, Object>> extendData;
    /***
     * message type . ie: create order,create user
     **/
    @ApiModelProperty(value = "message business type")
    private String messageType;
}
