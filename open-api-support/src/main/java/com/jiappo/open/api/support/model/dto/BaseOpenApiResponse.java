package com.jiappo.open.api.support.model.dto;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @Author: lee
 * @version:1.0.0
 * @Date: 2019/6/27 15:43
 **/
public abstract class BaseOpenApiResponse implements Serializable {
    private static final long serialVersionUID = -4745835347861166869L;


    @Override
    public String toString() {
        return String.format("this response `%s` - %s", this.getClass().getSimpleName()
                , JSON.toJSONString(this));
    }
}
