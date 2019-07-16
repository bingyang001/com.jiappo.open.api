package com.jiappo.open.api.domain.ticket.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.hummer.common.security.Rsa;
import com.jiappo.open.api.domain.exception.SignAuthException;
import com.jiappo.open.api.domain.ticket.BaseMessageTicket;
import com.jiappo.open.api.support.model.dto.inmessage.InMessageReq;
import com.jiappo.open.api.support.model.bo.TicketFieldBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.jiappo.open.api.domain.service.ErrorConstant.SignError.SIGN_VERIFIED_FAILED;
import static com.jiappo.open.api.domain.service.ErrorConstant.SignError.SIGN_VERIFIED_FAILED_DOC;

/**
 * @author bingy
 * @since 1.0.0
 */
@Service
public class RsaMessageTicketDefault extends BaseMessageTicket {
    private static final Logger LOGGER = LoggerFactory.getLogger(RsaMessageTicketDefault.class);

    @Override
    public void verified(InMessageReq inMessageReq, TicketFieldBo po) {
        decrypt(inMessageReq, po);
    }

    @Override
    public void decrypt(InMessageReq inMessageReq, TicketFieldBo po) {
        String decrypt =new  Rsa(po.getPublicKey(),po.getPrivateKey()).decryptByPriavte(inMessageReq.getSign());
        if (Strings.isNullOrEmpty(decrypt)) {
            LOGGER.error("platform {} message type {} rsa decrypt failed", inMessageReq.getPlatformName()
                    , inMessageReq.getMessageType());
            throw new SignAuthException(SIGN_VERIFIED_FAILED, SIGN_VERIFIED_FAILED_DOC);
        }
    }

    @Override
    public String createSign(InMessageReq inMessageReq, TicketFieldBo po) {
        return encryption(inMessageReq, po);
    }

    @Override
    public String encryption(InMessageReq inMessageReq, TicketFieldBo po) {
        Map<String, Object> fieldMap = buildSignField(inMessageReq
                , po
                , true
                , true);
        String originVal = JSON.toJSONString(fieldMap);
        String val = new Rsa(po.getPublicKey(),po.getPrivateKey()).encryptByPublish(originVal);
        LOGGER.info("platform {} message type {} rsa encrypted done,origin value {} ,encrypted value {}"
                , inMessageReq.getPlatformName()
                , inMessageReq.getMessageType()
                , originVal
                , val);
        return val;
    }
}
