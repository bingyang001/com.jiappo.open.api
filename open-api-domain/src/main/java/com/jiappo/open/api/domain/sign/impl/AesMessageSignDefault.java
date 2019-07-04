package com.jiappo.open.api.domain.sign.impl;

import com.alibaba.fastjson.JSON;
import com.hummer.common.security.Aes;
import com.jiappo.open.api.domain.sign.BaseMessageSign;
import com.jiappo.open.api.support.model.dto.in.InMessageReq;
import com.jiappo.open.api.support.model.po.SignFieldPo;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * aes sign default impl.
 *
 * @author bingy
 * @since 1.0.0
 */
@Service
public class AesMessageSignDefault extends BaseMessageSign {
    @Override
    public void verified(InMessageReq inMessageReq, SignFieldPo po) {

    }

    @Override
    public void decrypt(InMessageReq inMessageReq, SignFieldPo po) {

    }

    @Override
    public String createSign(InMessageReq inMessageReq, SignFieldPo po) {
        return null;
    }

    @Override
    public String encryption(InMessageReq inMessageReq, SignFieldPo po) {
        Map<String, Object> fieldMap = buildSignField(inMessageReq
                , po
                , true
                , true);
        String aesString = Aes.encryptToString(po.getPrivateKey(), po.getPublicKey(), JSON.toJSONString(fieldMap));

        return aesString;
    }
}
