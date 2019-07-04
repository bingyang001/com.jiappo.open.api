package com.jiappo.open.api.domain.sign.impl;

import com.jiappo.open.api.domain.sign.BaseMessageSign;
import com.jiappo.open.api.support.model.dto.in.InMessageReq;
import com.jiappo.open.api.support.model.po.SignFieldPo;

/**
 * @author bingy
 * @since 1.0.0
 */
public class RsaMessageSignDefault extends BaseMessageSign {
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
        return null;
    }
}
