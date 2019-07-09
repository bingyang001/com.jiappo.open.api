package com.jiappo.open.api.domain.sign.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.base.Strings;
import com.hummer.common.security.Rsa;
import com.jiappo.open.api.domain.exception.SignAuthException;
import com.jiappo.open.api.domain.sign.BaseMessageSign;
import com.jiappo.open.api.support.model.dto.in.InMessageReq;
import com.jiappo.open.api.support.model.bo.SignFieldBo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.jiappo.open.api.domain.route.ErrorConstant.SignError.SIGN_VERIFIED_FAILED;
import static com.jiappo.open.api.domain.route.ErrorConstant.SignError.SIGN_VERIFIED_FAILED_DOC;

/**
 * @author bingy
 * @since 1.0.0
 */
@Service
public class RsaMessageSignDefault extends BaseMessageSign {
    private static final Logger LOGGER = LoggerFactory.getLogger(RsaMessageSignDefault.class);

    @Override
    public void verified(InMessageReq inMessageReq, SignFieldBo po) {
        decrypt(inMessageReq, po);
    }

    @Override
    public void decrypt(InMessageReq inMessageReq, SignFieldBo po) {
        String decrypt = Rsa.decrypt(inMessageReq.getSign(), po.getPrivateKey());
        if (Strings.isNullOrEmpty(decrypt)) {
            LOGGER.error("platform {} message type {} rsa decrypt failed", inMessageReq.getPlatformName()
                    , inMessageReq.getMessageType());
            throw new SignAuthException(SIGN_VERIFIED_FAILED, SIGN_VERIFIED_FAILED_DOC);
        }
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
        String originVal = JSON.toJSONString(fieldMap);
        String val = Rsa.encrypted(originVal, po.getPublicKey());
        LOGGER.info("platform {} message type {} rsa encrypted done,origin value {} ,encrypted value {}"
                , inMessageReq.getPlatformName()
                , inMessageReq.getMessageType()
                , originVal
                , val);
        return val;
    }
}
