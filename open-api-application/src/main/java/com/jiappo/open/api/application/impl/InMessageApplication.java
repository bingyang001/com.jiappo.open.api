package com.jiappo.open.api.application.impl;

import com.jiappo.open.api.application.facade.InMessageFacade;
import com.jiappo.open.api.dao.mapper.openapi.PlatformForwarderRouteMapper;
import com.jiappo.open.api.support.model.dto.in.InMessageReq;
import com.jiappo.open.api.support.model.po.PlatformForwarderRoutePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author: lee
 * @version:1.0.0
 * @Date: 2019/6/28 15:14
 **/
@Service
public class InMessageApplication implements InMessageFacade {
    @Autowired
    private PlatformForwarderRouteMapper routeMapper;

    @Override
    public Object inMessage(InMessageReq req) {
        PlatformForwarderRoutePo po = routeMapper.queryRouteSingleBy("platform_A"
                , "create_user", 0);
        return null;
    }
}
