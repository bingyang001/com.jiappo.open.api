package com.jiappo.open.api.application.impl;

import com.hummer.common.exceptions.AppException;
import com.jiappo.open.api.application.facade.InMessageFacade;
import com.jiappo.open.api.dao.mapper.openapi.MessageTransferRoutePoMapper;
import com.jiappo.open.api.domain.route.BaseInMessageRoute;
import com.jiappo.open.api.domain.route.Route;
import com.jiappo.open.api.support.model.dto.in.InMessageReq;
import com.jiappo.open.api.support.model.po.MessageTransferRoutePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: lee
 * @version:1.0.0
 * @Date: 2019/6/28 15:14
 **/
@Service
public class InMessageApplication implements InMessageFacade {
    @Autowired
    private MessageTransferRoutePoMapper routeMapper;
    @Autowired
    private Map<String, BaseInMessageRoute> routeMap;


    @Override
    public Object inMessage(InMessageReq req) {
        MessageTransferRoutePo po = routeMapper.queryRouteSingleBy(req.getPlatformName()
                , req.getMessageType(), 0);
        Route route = routeMap.get(po.getRouteImplService());
        if (route == null) {
            throw new AppException(40000, "no match route provide service");
        }
        //verified
        route.verified(req, po);
        //send message
        return route.transfer(po, req);
    }
}
