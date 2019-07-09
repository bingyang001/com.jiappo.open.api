package com.jiappo.open.api.domain.sign;

import com.jiappo.open.api.support.model.dto.in.InMessageReq;
import com.jiappo.open.api.support.model.bo.SignFieldBo;

/**
 * out message sign
 *
 * @author bingy
 * @since 1.0.0
 */
public interface OutMessageSign {
    /**
     * created sign
     *
     * @param inMessageReq in message
     * @param po           route po
     * @return
     */
    String createSign(InMessageReq inMessageReq, SignFieldBo po);

    /**
     * new encryption
     *
     * @param inMessageReq in message
     * @param po           po
     * @return
     */
    String encryption(InMessageReq inMessageReq, SignFieldBo po);
}
