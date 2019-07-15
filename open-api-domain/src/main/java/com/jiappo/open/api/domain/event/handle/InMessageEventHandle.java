package com.jiappo.open.api.domain.event.handle;

import com.google.common.eventbus.Subscribe;
import com.hummer.common.utils.DateUtil;
import com.hummer.common.utils.ObjectCopyUtils;
import com.jiappo.open.api.domain.event.InMessageEvent;
import com.jiappo.open.api.domain.event.VerifiedSecretEvent;
import com.jiappo.open.api.domain.event.VerifiedSignEvent;
import com.jiappo.open.api.domain.service.InMessageService;
import com.jiappo.open.api.domain.service.MessageTicketService;
import com.jiappo.open.api.domain.ticket.VerifiedStatusEnum;
import com.jiappo.open.api.support.model.po.InMessagePo;
import com.jiappo.open.api.support.model.po.MessageTicketRecordPo;
import com.jiappo.open.api.support.model.po.TicketVerifiedRecordPo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/10 14:55
 **/
@Component
@Slf4j
public class InMessageEventHandle {
    @Autowired
    private MessageTicketService ticketService;
    @Autowired
    private InMessageService inMessageService;

    @Subscribe
    public void verifiedSecretResultHande(final VerifiedSecretEvent event) {
        //save verified result
        TicketVerifiedRecordPo verifiedRecordPo = new TicketVerifiedRecordPo();
        verifiedRecordPo.setVerifiedStatus(event.getVerifiedStatus());
        verifiedRecordPo.setVerifiedDescribe(event.getVerifiedDescribe());
        verifiedRecordPo.setPlatformName(event.getPlatformName());
        verifiedRecordPo.setMessageId(event.getMessageId());
        verifiedRecordPo.setMessageType(event.getMessageType());
        verifiedRecordPo.setSecretKey(event.getSecretKey());
        verifiedRecordPo.setTicketValue(event.getOriginTicket());
        verifiedRecordPo.setTicketType("secret");
        ticketService.saveNewTicketVerifiedResult(verifiedRecordPo);
    }

    @Subscribe
    public void verifiedResulthandle(final VerifiedSignEvent event) {
        log.info("begin handle verified event {}", event);
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
        verifiedRecordPo.setMessageType(event.getMessageType());
        verifiedRecordPo.setTicketType(event.getVerifiedType());
        verifiedRecordPo.setSecretKey(event.getSecretKey());
        ticketService.saveNewTicketVerifiedResult(verifiedRecordPo);
    }

    @Subscribe
    public void appendInMessageLog(final InMessageEvent event) {
        inMessageService.save(ObjectCopyUtils.copy(event, InMessagePo.class));
    }
}
