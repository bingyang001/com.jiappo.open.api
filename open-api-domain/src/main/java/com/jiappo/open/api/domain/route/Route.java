package com.jiappo.open.api.domain.route;

import com.jiappo.open.api.support.model.dto.in.InMessageReq;
import com.jiappo.open.api.support.model.po.MessageTransferRoutePo;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/2 17:28
 **/
public interface Route {
    /**
     * verified in site message
     *
     * @param inMessageReq message
     * @param po           route
     * @return void
     * @author liguo
     * @date 2019/7/2 17:57
     * @since 1.0.0
     **/
    void verified(InMessageReq inMessageReq, MessageTransferRoutePo po);

    /**
     * transfer request
     *
     * @param po
     * @param req
     * @return java.lang.Object
     * @author liguo
     * @date 2019/7/2 17:40
     * @since 1.0.0
     **/
    Object transfer(MessageTransferRoutePo po, InMessageReq req);
}
