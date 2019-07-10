package com.jiappo.open.api.domain.ticket.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.hummer.common.security.Aes;
import com.jiappo.open.api.domain.exception.SignAuthException;
import com.jiappo.open.api.domain.ticket.BaseMessageTicket;
import com.jiappo.open.api.support.model.dto.in.InMessageReq;
import com.jiappo.open.api.support.model.bo.SignFieldBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.jiappo.open.api.domain.service.ErrorConstant.SignError.SIGN_VERIFIED_FAILED;
import static com.jiappo.open.api.domain.service.ErrorConstant.SignError.SIGN_VERIFIED_FAILED_DOC;

/**
 * aes sign default impl.
 *
 * @author bingy
 * @since 1.0.0
 */
@Service
public class AesMessageTicketDefault extends BaseMessageTicket {
    private static final Logger LOGGER = LoggerFactory.getLogger(AesMessageTicketDefault.class);

    @Override
    public void verified(InMessageReq inMessageReq, SignFieldBo po) {
        decrypt(inMessageReq, po);
    }

    @Override
    public void decrypt(InMessageReq inMessageReq, SignFieldBo po) {
        String decryptValue = Aes.decrypt(po.getPrivateKey(), po.getPublicKey(), inMessageReq.getSign());
        if (Strings.isNullOrEmpty(decryptValue)) {
            LOGGER.error("platform {} message type {} aes decrypt failed", inMessageReq.getPlatformName()
                    , inMessageReq.getMessageType());
            throw new SignAuthException(SIGN_VERIFIED_FAILED, SIGN_VERIFIED_FAILED_DOC);
        }
        //notice：can continue verified decrypt value equals origin body ?
    }

    @Override
    public String createSign(InMessageReq inMessageReq, SignFieldBo po) {
        return encryption(inMessageReq, po);
    }

    @Override
    public String encryption(InMessageReq inMessageReq, SignFieldBo po) {
        Map<String, Object> fieldMap = buildSignField(inMessageReq
                , po
                , true
                , true);
        String aesString = Aes.encryptToString(po.getPrivateKey()
                , po.getPublicKey(), JSON.toJSONString(fieldMap));
        LOGGER.info("platform {} message type {} sign done {}"
                , inMessageReq.getPlatformName()
                , inMessageReq.getMessageType()
                , aesString);

        return aesString;
    }
}