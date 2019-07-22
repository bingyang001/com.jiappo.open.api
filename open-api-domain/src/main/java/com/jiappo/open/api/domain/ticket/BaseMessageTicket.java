package com.jiappo.open.api.domain.ticket;

import com.google.common.collect.Maps;
import com.hummer.common.utils.DateUtil;
import com.jiappo.open.api.domain.exception.SignAuthException;
import com.jiappo.open.api.domain.service.MessageTicketService;
import com.jiappo.open.api.support.model.dto.inmessage.InMessageReq;
import com.jiappo.open.api.support.model.bo.TicketFieldBo;
import com.jiappo.open.api.support.model.po.MessageTicketRecordPo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Collection;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import static com.jiappo.open.api.domain.service.ErrorConstant.SignError.SIGN_STRING_ALREADY_EXPIRED;
import static com.jiappo.open.api.domain.service.ErrorConstant.SignError.SIGN_STRING_ALREADY_USED;
import static com.jiappo.open.api.domain.service.ErrorConstant.SignError.SIGN_STRING_ALREAD_EXPIRED_DOC;
import static com.jiappo.open.api.domain.service.ErrorConstant.SignError.SIGN_STRING_ALREAD_USED_DOC;
import static com.jiappo.open.api.domain.service.ErrorConstant.SignError.SIGN_STRING_NO_EXISTS;
import static com.jiappo.open.api.domain.service.ErrorConstant.SignError.SIGN_STRING_NO_EXISTS_DOC;

/**
 * impl sign field builder
 *
 * @author bingy
 * @since 1.0.0
 */
public abstract class BaseMessageTicket implements InMessageSign, OutMessageSign {
    @Autowired
    private MessageTicketService ticketService;
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseMessageTicket.class);

    /**
     * builder will sign field map
     *
     * @param inMessageReq          message
     * @param po                    file route
     * @param appendUUIDField       if true then append uuid to map
     * @param appendSignExpiredTime if true then append sign expired time to map
     * @return
     */
    protected Map<String, Object> buildSignField(InMessageReq inMessageReq
            , TicketFieldBo po
            , boolean appendUUIDField
            , boolean appendSignExpiredTime) {
        Map<String, Object> fieldMap = Maps.newHashMapWithExpectedSize(16);
        if (appendUUIDField) {
            fieldMap.put("_uuid_", UUID.randomUUID().toString().replaceAll("-", ""));
        }

        if (appendSignExpiredTime) {
            fieldMap.put("_expired_", po.getExpiredMinute());
            LOGGER.info("message {} ,now time is after  {} minute expired"
                    , inMessageReq.getMessageType()
                    , po.getExpiredMinute());
        }

        if (po.getFieldMap() == null) {
            return fieldMap;
        }

        Set<String> keys = po.getFieldMap().keySet();
        Collection<Map<String, Object>> data = inMessageReq.getData();
        for (Map<String, Object> dataInfo : data) {
            for (String key : keys) {
                Object value = dataInfo.getOrDefault(key, null);
                if (value != null) {
                    fieldMap.put(key, value);
                }
            }
        }
        LOGGER.debug("message source {} type {} encrypted filed {}"
                , inMessageReq.getMessageSource()
                , inMessageReq.getMessageType()
                , fieldMap);
        return fieldMap;
    }

    /**
     * verified in message ticket
     *
     * @param inMessageReq message
     * @param po           ticket po
     * @return void
     * @author liguo
     * @date 2019/7/11 14:15
     * @since 1.0.0
     **/
    public void verifiedTicket(InMessageReq inMessageReq, TicketFieldBo po) {
        //verified request ticket
        verified(inMessageReq, po);

        if (!po.getIsPreCreated()) {
            return;
        }
        // if ticket is per crated then verified is match db ticket.
        // verified is exists and is delete and is expired,any no match then throw exception
        MessageTicketRecordPo ticketRecordPo = ticketService.queryTicket(inMessageReq.getSign());
        if (ticketRecordPo == null) {
            throw new SignAuthException(SIGN_STRING_NO_EXISTS, SIGN_STRING_NO_EXISTS_DOC);
        }

        //verified message source and message type
        if (!ticketRecordPo.getMessageSource().equalsIgnoreCase(inMessageReq.getMessageSource())
                || !ticketRecordPo.getMessageType().equalsIgnoreCase(inMessageReq.getMessageType())) {
            throw new SignAuthException(SIGN_STRING_NO_EXISTS, SIGN_STRING_NO_EXISTS_DOC);
        }

        if (ticketRecordPo.getIsDelete()
                || VerifiedStatusEnum.getByCode(ticketRecordPo.getVerifiedStatus()) != VerifiedStatusEnum.NO_VERIFIED) {
            throw new SignAuthException(SIGN_STRING_ALREADY_USED, SIGN_STRING_ALREAD_USED_DOC);
        }

        Date expiredTime = DateUtil.addMinute(ticketRecordPo.getCreatedDate(), ticketRecordPo.getExpiredDate());
        long diffMinute = DateUtil.subtractNowDate(expiredTime) / 1000 / 60;
        LOGGER.info("message source {}, message type {}, message id {} sign after {} expired"
                , inMessageReq.getMessageSource()
                , inMessageReq.getMessageType()
                , inMessageReq.getMessageId()
                , expiredTime);
        if (diffMinute < 0) {
            throw new SignAuthException(SIGN_STRING_ALREADY_EXPIRED, SIGN_STRING_ALREAD_EXPIRED_DOC);
        }
    }
}
