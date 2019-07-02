package com.jiappo.open.api.application.impl;

import com.hummer.common.exceptions.AppException;
import com.jiappo.open.api.application.facade.InMessageFacade;
import com.jiappo.open.api.dao.mapper.openapi.MessageTransferRoutePoMapper;
import com.jiappo.open.api.domain.route.Route;
import com.jiappo.open.api.support.model.dto.in.InMessageReq;
import com.jiappo.open.api.support.model.po.MessageTransferRoutePo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lee
 * @version:1.0.0
 * @Date: 2019/6/28 15:14
 **/
@Service
public class InMessageApplication implements InMessageFacade {
    private static final Logger LOGGER = LoggerFactory.getLogger(InMessageApplication.class);
    @Autowired
    private MessageTransferRoutePoMapper routeMapper;
    @Autowired
    private Route route;

    @Override
    public Object inMessage(InMessageReq req) {
        MessageTransferRoutePo po = routeMapper.queryRouteSingleBy(req.getPlatformName()
                , req.getMessageType(), 0);
        route.verified(req, po);
        return route.transfer(po, req);
    }
}
