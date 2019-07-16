package com.jiappo.open.api.domain.ticket.impl;

import com.alibaba.fastjson.JSON;
import com.hummer.common.security.Md5;
import com.jiappo.open.api.domain.exception.SignAuthException;
import com.jiappo.open.api.domain.ticket.BaseMessageTicket;
import com.jiappo.open.api.support.model.dto.inmessage.InMessageReq;
import com.jiappo.open.api.support.model.bo.TicketFieldBo;
import org.apache.logging.log4j.util.Strings;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.jiappo.open.api.domain.service.ErrorConstant.SignError.SIGN_STRING_NULL;
import static com.jiappo.open.api.domain.service.ErrorConstant.SignError.SIGN_STRING_NULL_DOC;
import static com.jiappo.open.api.domain.service.ErrorConstant.SignError.SIGN_VERIFIED_FAILED;
import static com.jiappo.open.api.domain.service.ErrorConstant.SignError.SIGN_VERIFIED_FAILED_DOC;

/**
 * this class is default impl message verified md5 strategy
 *
 * @author bingy
 * @since 1.0.0
 */
@Service
public class Md5MessageTicketDefault extends BaseMessageTicket {
    private static final Logger LOGGER = LoggerFactory.getLogger(Md5MessageTicketDefault.class);

    @Override
    public void verified(InMessageReq inMessageReq, TicketFieldBo po) {
        verifiedSign(inMessageReq, po);
    }

    @Override
    public void decrypt(InMessageReq inMessageReq, TicketFieldBo po) {
        verifiedSign(inMessageReq, po);
    }

    @Override
    public String createSign(InMessageReq inMessageReq, TicketFieldBo po) {
        return createMd5Sign(inMessageReq, po);
    }

    @Override
    public String encryption(InMessageReq inMessageReq, TicketFieldBo po) {
        return createMd5Sign(inMessageReq, po);
    }

    private String createMd5Sign(final InMessageReq inMessageReq, final TicketFieldBo po) {
        //all field
        Map<String, Object> signFieldMap = buildSignField(inMessageReq
                , po
                , false
                , false);
        String signJson = JSON.toJSONString(signFieldMap);
        String md5String = Md5.encryptMd5(signJson);
        LOGGER.info("create sign done ,md5 string is :{}", md5String);
        return md5String;
    }

    private void verifiedSign(final InMessageReq inMessageReq, final TicketFieldBo po) {
        if (Strings.isEmpty(inMessageReq.getSign())) {
            throw new SignAuthException(SIGN_STRING_NULL, SIGN_STRING_NULL_DOC);
        }

        String md5String = createMd5Sign(inMessageReq, po);
        if (!md5String.equals(inMessageReq.getSign())) {
            throw new SignAuthException(SIGN_VERIFIED_FAILED, SIGN_VERIFIED_FAILED_DOC);
        }
    }
}
