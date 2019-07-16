package com.jiappo.open.api.domain.service;

import com.hummer.common.exceptions.AppException;
import com.hummer.common.security.Md5;
import com.jiappo.open.api.dao.mapper.openapi.MessageTicketPoMapper;
import com.jiappo.open.api.dao.mapper.openapi.MessageTicketVerifiedResultPoMapper;
import com.jiappo.open.api.domain.ticket.BaseMessageTicket;
import com.jiappo.open.api.domain.ticket.VerifiedStatusEnum;
import com.jiappo.open.api.support.model.bo.TicketFieldBo;
import com.jiappo.open.api.support.model.bo.TicketVerifiedResultBo;
import com.jiappo.open.api.support.model.dto.inmessage.InMessageReq;
import com.jiappo.open.api.support.model.po.MessageRulePo;
import com.jiappo.open.api.support.model.po.MessageTicketRecordPo;
import com.jiappo.open.api.support.model.po.TicketVerifiedRecordPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Map;

/**
 * message ticket service
 *
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/10 17:04
 **/
@Service
public class MessageTicketService {
    private static final Logger LOGGER = LoggerFactory.getLogger(MessageTicketService.class);
    @Autowired
    private Map<String, BaseMessageTicket> ticketMap;
    @Autowired
    private MessageTicketPoMapper ticketPoMapper;
    @Autowired
    private MessageTicketVerifiedResultPoMapper ticketVerifiedResultPoMapper;

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
        if (po.getIsPreCreated() == null || !po.getIsPreCreated()) {
            throw new AppException(40001, "please settings `IsPreCreated` value is true");
        }
        //query rule
        BaseMessageTicket messageTicket = ticketMap.get(po.getTicketImplService());
        if (messageTicket == null) {
            LOGGER.error("message source {} message type {},ticket service {} provide business no implement"
                    , req.getMessageType()
                    , req.getMessageType()
                    , po.getTicketImplService());
            throw new AppException(40002, "message ticket rule no settings");
        }
        //builder sign field
        TicketFieldBo ticketFieldBo = TicketFieldBo
                .builder()
                .expiredMinute(po.getTicketExpiredTimeMinute())
                .privateKey(po.getPrivateKey())
                .publicKey(po.getPublicKey())
                .useOneExpired(true)
                .fieldMap(po.getTicketField())
                .build();
        //encrypted
        String ticket = messageTicket.encryption(req, ticketFieldBo);

        //
        try {
            MessageTicketRecordPo recordPo = new MessageTicketRecordPo();
            recordPo.setIsPreCreated(true);
            recordPo.setMessageSource(req.getMessageSource());
            recordPo.setExpiredDate(po.getTicketExpiredTimeMinute());
            recordPo.setMessageId(req.getMessageId());
            recordPo.setMessageType(req.getMessageType());
            recordPo.setTicketValue(ticket);
            recordPo.setTicketMd5(Md5.encryptMd5(ticket));
            recordPo.setTicketType(po.getTicketImplService());
            recordPo.setDataBody(Collections.emptyList());
            recordPo.setVerifiedStatus(VerifiedStatusEnum.NO_VERIFIED.getCode());
            if (po.getInMessageResponseSnapshotData()) {
                recordPo.setDataBody(req.getData());
            }
            int dbResult = ticketPoMapper.saveTicket(recordPo);
            LOGGER.info("message source {} message type {} ticket created success,save to db success {}"
                    , req.getMessageSource()
                    , req.getMessageType()
                    , ticket
                    , dbResult);
        } catch (DuplicateKeyException e) {
            //ignore
            LOGGER.debug("message source {},message id {} in db already exists, so ignore DuplicateKeyException"
                    , req.getMessageSource()
                    , req.getMessageId());
        }
        return ticket;
    }

    /**
     * query db ticket
     *
     * @param ticket
     * @return com.jiappo.open.api.support.model.po.MessageTicketRecordPo
     * @author liguo
     * @date 2019/7/11 9:43
     * @since 1.0.0
     **/
    public MessageTicketRecordPo queryTicket(String ticket) {
        //cache MessageTicketRecordPo
        return ticketPoMapper.querySingleTicket(Md5.encryptMd5(ticket));
    }


    /**
     * set ticket verified result
     *
     * @param verifiedResult result bo {@link TicketVerifiedResultBo}
     * @return void
     * @author liguo
     * @date 2019/7/11 10:08
     * @since 1.0.0
     **/
    public void setTicketVerifiedResult(MessageTicketRecordPo verifiedResult) {
        ticketPoMapper.setTicketVerifiedResult(verifiedResult);
    }

    /**
     * save ticket verified
     *
     * @param recordPo po
     * @return void
     * @author liguo
     * @date 2019/7/11 14:49
     * @since 1.0.0
     **/
    public void saveNewTicketVerifiedResult(TicketVerifiedRecordPo recordPo) {
        ticketVerifiedResultPoMapper.saveVerifiedResult(recordPo);
    }
}
