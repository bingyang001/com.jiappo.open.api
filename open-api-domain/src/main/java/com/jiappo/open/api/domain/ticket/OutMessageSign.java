package com.jiappo.open.api.domain.ticket;

import com.jiappo.open.api.support.model.dto.inmessage.InMessageReq;
import com.jiappo.open.api.support.model.bo.TicketFieldBo;

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
    String createSign(InMessageReq inMessageReq, TicketFieldBo po);

    /**
     * new encryption
     *
     * @param inMessageReq in message
     * @param po           po
     * @return
     */
    String encryption(InMessageReq inMessageReq, TicketFieldBo po);
}
