package com.jiappo.open.api.support.model.dto;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;

/**
 * @Author: lee
 * @version:1.0.0
 * @Date: 2019/6/27 15:41
 **/
public abstract class BaseOpenApiRequest implements Serializable {
    private static final long serialVersionUID = 3262722339858846704L;

    @Override
    public String toString() {
        return String.format("this request `%s` - %s", this.getClass().getSimpleName()
                , JSON.toJSONString(this));
    }
}
