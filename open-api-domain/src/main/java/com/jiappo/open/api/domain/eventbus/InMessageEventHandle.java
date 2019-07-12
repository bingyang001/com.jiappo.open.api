package com.jiappo.open.api.domain.eventbus;

import com.google.common.eventbus.Subscribe;
import com.hummer.common.utils.DateUtil;
import com.jiappo.open.api.domain.service.MessageTicketService;
import com.jiappo.open.api.domain.ticket.VerifiedStatusEnum;
import com.jiappo.open.api.support.model.po.MessageTicketRecordPo;
import com.jiappo.open.api.support.model.po.TicketVerifiedRecordPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/10 14:55
 **/
@Component
public class InMessageEventHandle {
    private static final Logger LOGGER = LoggerFactory.getLogger(InMessageEventHandle.class);
    @Autowired
    private MessageTicketService ticketService;

    @Subscribe
    public void verifiedSecretResultHande(VerifiedSecretEvent event){
        //save verified result
        TicketVerifiedRecordPo verifiedRecordPo = new TicketVerifiedRecordPo();
        verifiedRecordPo.setVerifiedStatus(event.getVerifiedStatus());
        verifiedRecordPo.setVerifiedDescribe(event.getVerifiedDescribe());
        verifiedRecordPo.setPlatformName(event.getPlatformName());
        verifiedRecordPo.setMessageId(event.getMessageId());
        verifiedRecordPo.setMessageType(event.getMessageId());
        verifiedRecordPo.setSecretKey(event.getSecretKey());
        verifiedRecordPo.setTicketValue(event.getOriginTicket());
        verifiedRecordPo.setTicketType("secret");
        ticketService.saveNewTicketVerifiedResult(verifiedRecordPo);
    }

    @Subscribe
    public void verifiedResulthandle(VerifiedSignEvent event) {
        LOGGER.info("begin handle verified event {}", event);
        MessageTicketRecordPo ticketRecordPo = ticketService.queryTicket(event.getOriginTicket());
        //update ticket record verified status
        if (ticketRecordPo != null && VerifiedStatusEnum.getByCode(event.getVerifiedStatus())
                == VerifiedStatusEnum.VERIFIED_SUCCESS) {
            MessageTicketRecordPo recordPo = new MessageTicketRecordPo();
            recordPo.setId(ticketRecordPo.getId());
            recordPo.setVerifiedDate(DateUtil.now());
            recordPo.setVerifiedStatus(event.getVerifiedStatus());
            recordPo.setVerifiedDescribe(event.getVerifiedDescribe());
            ticketService.setTicketVerifiedResult(recordPo);
        }
        //save verified result
        TicketVerifiedRecordPo verifiedRecordPo = new TicketVerifiedRecordPo();
        verifiedRecordPo.setTicketValue(event.getOriginTicket());
        verifiedRecordPo.setVerifiedStatus(event.getVerifiedStatus());
        verifiedRecordPo.setVerifiedDescribe(event.getVerifiedDescribe());
        verifiedRecordPo.setPlatformName(event.getPlatformName());
        verifiedRecordPo.setMessageId(event.getMessageId());
        verifiedRecordPo.setMessageType(event.getMessageId());
        verifiedRecordPo.setTicketType(event.getVerifiedType());
        verifiedRecordPo.setSecretKey(event.getSecretKey());
        ticketService.saveNewTicketVerifiedResult(verifiedRecordPo);
    }
}
