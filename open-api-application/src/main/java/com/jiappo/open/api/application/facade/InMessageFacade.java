package com.jiappo.open.api.application.facade;

import com.jiappo.open.api.support.model.dto.in.InMessageReq;

/**
 * @Author: lee
 * @version:1.0.0
 * @Date: 2019/6/28 15:19
 **/
public interface InMessageFacade {
    /**
    * in to site message business handle
    * @author liguo
    * @date 2019/6/28 15:21
    * @since 1.0.0
    * @param [req]
    * @return java.lang.Object
    **/
    Object inMessage(InMessageReq req);
}
