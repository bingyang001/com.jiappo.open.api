package com.jiappo.open.api.support.model.dto;

import com.alibaba.fastjson.JSON;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Collection;
import java.util.Map;

/**
 * @Author: lee
 * @version:1.0.0
 * @Date: 2019/6/27 15:41
 **/
@Data
public abstract class BaseOpenApiRequest implements Serializable {
    private static final long serialVersionUID = 3262722339858846704L;
    /***
     * message source, out of service platform name
     **/
    @ApiModelProperty(hidden = true)
    private String messageSource;
    /**
     * message body
     */
    @ApiModelProperty(value = "message body")
    private Collection<Map<String, Object>> data;
    /**
     * message batch id,need unique
     **/
    private String messageId;
    /***
     * message type . ie: create order,create user
     **/
    @ApiModelProperty(value = "message business type", hidden = true)
    private String messageType;

    @Override
    public String toString() {
        return String.format("this request `%s` - %s", this.getClass().getSimpleName()
                , JSON.toJSONString(this));
    }
}
