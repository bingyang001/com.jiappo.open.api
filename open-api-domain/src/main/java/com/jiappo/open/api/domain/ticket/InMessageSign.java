package com.jiappo.open.api.domain.ticket;

import com.jiappo.open.api.domain.exception.SignAuthException;
import com.jiappo.open.api.support.model.dto.inmessage.InMessageReq;
import com.jiappo.open.api.support.model.bo.TicketFieldBo;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/2 19:02
 **/
public interface InMessageSign {
    /**
     * verified,if failed then throw {@link SignAuthException}
     *
     * @param inMessageReq message
     * @param po           sign field
     * @throws SignAuthException
     */
    void verified(InMessageReq inMessageReq, TicketFieldBo po);

    /**
     * decrypt
     *
     * @param inMessageReq in message
     * @param po           route po
     */
    void decrypt(InMessageReq inMessageReq, TicketFieldBo po);
}
