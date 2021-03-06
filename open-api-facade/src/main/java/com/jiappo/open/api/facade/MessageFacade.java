package com.jiappo.open.api.facade;

import com.jiappo.open.api.support.model.dto.inmessage.InMessageReq;
import com.jiappo.open.api.support.model.dto.outmessage.OutMessageReq;

/**
 * @Author: lee
 * @version:1.0.0
 * @Date: 2019/6/28 15:19
 **/
public interface MessageFacade {
    /**
     * in to site message business handle
     *
     * @param req in message
     * @return java.lang.Object
     * @author liguo
     * @date 2019/6/28 15:21
     * @since 1.0.0
     **/
    Object inMessage(InMessageReq req);

    /**
     * created new ticket
     *
     * @param req message
     * @return java.lang.String
     * @author liguo
     * @date 2019/7/10 16:50
     * @since 1.0.0
     **/
    String newTicket(InMessageReq req);

    /**
     * out message
     *
     * @param req req
     * @return java.lang.Object
     * @author liguo
     * @date 2019/7/12 17:10
     * @since 1.0.0
     **/
    Object outMessage(OutMessageReq req);
}
