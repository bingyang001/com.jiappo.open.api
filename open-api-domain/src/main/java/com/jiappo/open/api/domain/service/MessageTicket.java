package com.jiappo.open.api.domain.service;

import com.hummer.common.exceptions.AppException;
import com.jiappo.open.api.dao.mapper.openapi.MessageTicketPoMapper;
import com.jiappo.open.api.domain.ticket.BaseMessageTicket;
import com.jiappo.open.api.support.model.bo.SignFieldBo;
import com.jiappo.open.api.support.model.dto.in.InMessageReq;
import com.jiappo.open.api.support.model.po.MessageRulePo;
import com.jiappo.open.api.support.model.po.MessageTicketRecordPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/10 17:04
 **/
@Service
public class MessageTicket {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageTicket.class);
    @Autowired
    private Map<String, BaseMessageTicket> ticketMap;
    @Autowired
    private MessageTicketPoMapper ticketPoMapper;

    /**
     * new ticket and save to db
     *
     * @param req req message
     * @param po  rule po
     * @return java.lang.String
     * @author liguo
     * @date 2019/7/10 19:01
     * @since 1.0.0
     **/
    public String newTicket(InMessageReq req, MessageRulePo po) {
        //query rule
        BaseMessageTicket messageTicket = ticketMap.get(po.getSignImplService());
        if (messageTicket == null) {
            LOGGER.error("platformName {} message type {},ticket service {} provide business no implement"
                    , req.getPlatformName()
                    , req.getMessageType()
                    , po.getSignImplService());
            throw new AppException(4000, "message ticket rule no settings");
        }
        //builder sign field
        SignFieldBo signFieldBo = SignFieldBo
                .builder()
                .expiredMinute(po.getSignExpiredTimeMinute())
                .privateKey(po.getPrivateKey())
                .publicKey(po.getPublicKey())
                .useOneExpired(true)
                .fieldMap(po.getSignField())
                .build();
        //encrypted
        String ticket = messageTicket.encryption(req, signFieldBo);

        //
        try {
            int dbResult = ticketPoMapper.saveTicket(MessageTicketRecordPo
                    .builder()
                    .platformName(req.getPlatformName())
                    .expiredDate(po.getSignExpiredTimeMinute())
                    .messageId(req.getBatchId())
                    .messageType(req.getMessageType())
                    .signValue(ticket)
                    .signType(po.getSignImplService())
                    .build());
            LOGGER.info("platformName {} message type {} ticket created success,save to db success {}"
                    , req.getPlatformName()
                    , req.getMessageType()
                    , ticket
                    , dbResult);
        } catch (DuplicateKeyException e) {
            //ignore
            LOGGER.debug("platformName {},message id {} in db already exists, so ignore DuplicateKeyException"
                    , req.getPlatformName()
                    , req.getBatchId());
        }
        return ticket;
    }
}
