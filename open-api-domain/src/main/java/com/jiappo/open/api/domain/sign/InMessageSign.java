package com.jiappo.open.api.domain.sign;

import com.jiappo.open.api.support.model.dto.in.InMessageReq;
import com.jiappo.open.api.support.model.po.SignFieldPo;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/2 19:02
 **/
public interface InMessageSign {
    /**
     * verified
     *
     * @param inMessageReq
     * @param po
     */
    void verified(InMessageReq inMessageReq, SignFieldPo po);

    /**
     * decrypt
     *
     * @param inMessageReq in message
     * @param po           route po
     */
    void decrypt(InMessageReq inMessageReq, SignFieldPo po);
}
