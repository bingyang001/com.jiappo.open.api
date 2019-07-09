package com.jiappo.open.api.domain.sign;

import com.jiappo.open.api.domain.exception.SignAuthException;
import com.jiappo.open.api.support.model.dto.in.InMessageReq;
import com.jiappo.open.api.support.model.bo.SignFieldBo;

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
    void verified(InMessageReq inMessageReq, SignFieldBo po);

    /**
     * decrypt
     *
     * @param inMessageReq in message
     * @param po           route po
     */
    void decrypt(InMessageReq inMessageReq, SignFieldBo po);
}
