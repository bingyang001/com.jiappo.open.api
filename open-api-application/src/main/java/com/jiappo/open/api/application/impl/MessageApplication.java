package com.jiappo.open.api.application.impl;

import com.hummer.common.exceptions.AppException;
import com.jiappo.open.api.application.facade.MessageFacade;
import com.jiappo.open.api.dao.mapper.openapi.MessageTransferRulePoMapper;
import com.jiappo.open.api.domain.entity.OutMessageRule;
import com.jiappo.open.api.domain.service.in.BaseInMessageHandleService;
import com.jiappo.open.api.domain.service.out.BaseOutMessageHandleService;
import com.jiappo.open.api.domain.service.InMessageHandle;
import com.jiappo.open.api.domain.service.MessageTicketService;
import com.jiappo.open.api.support.model.dto.in.InMessageReq;
import com.jiappo.open.api.support.model.dto.out.OutMessageReq;
import com.jiappo.open.api.support.model.po.MessageRulePo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: lee
 * @version:1.0.0
 * @Date: 2019/6/28 15:14
 **/
@Service
public class MessageApplication implements MessageFacade {
    @Autowired
    private MessageTransferRulePoMapper routeMapper;
    @Autowired
    private Map<String, BaseInMessageHandleService> handleMap;
    @Autowired
    private MessageTicketService messageTicketService;
    @Autowired
    @Lazy
    private Map<String, BaseOutMessageHandleService> outMessageHandleServiceMap;

    @Override
    public Object inMessage(InMessageReq req) {
        MessageRulePo po = routeMapper.queryRouteSingleBy(req.getPlatformName()
                , req.getMessageType(), 0);
        if (po == null) {
            throw new AppException(40000, "no match route provide service");
        }

        InMessageHandle inMessageHandle = handleMap.get(po.getRouteImplService());
        if (inMessageHandle == null) {
            throw new AppException(40001, "no match route provide service");
        }
        //verified
        inMessageHandle.verified(req, po);
        //send message
        return inMessageHandle.transfer(po, req);
    }

    /**
     * created new ticket
     *
     * @param req message
     * @return java.lang.String
     * @author liguo
     * @date 2019/7/10 16:50
     * @since 1.0.0
     **/
    @Override
    public String newTicket(InMessageReq req) {
        MessageRulePo po = routeMapper.queryRouteSingleBy(req.getPlatformName()
                , req.getMessageType(), 0);
        if (po == null) {
            throw new AppException(40000, "no match route provide service");
        }
        return messageTicketService.newTicket(req, po);
    }

    /**
     * out message
     *
     * @param req req
     * @return java.lang.Object
     * @author liguo
     * @date 2019/7/12 17:10
     * @since 1.0.0
     **/
    @Override
    public Object outMessage(OutMessageReq req) {
        //query message rule
        MessageRulePo po = routeMapper.queryRouteSingleBy(req.getPlatformName()
                , req.getMessageType(), 1);
        //builder domain out message rule
        OutMessageRule rule = new OutMessageRule(po);
        //get out message business implement service
        BaseOutMessageHandleService service = outMessageHandleServiceMap.get(po.getRouteImplService());
        if (service == null) {
            throw new AppException(40001, "no match route provide service");
        }
        //call service handle message
        return service.handle(req, rule);
    }
}
