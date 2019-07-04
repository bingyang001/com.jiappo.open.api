package com.jiappo.open.api.domain.sign;

import com.jiappo.open.api.support.model.dto.in.InMessageReq;
import com.jiappo.open.api.support.model.po.SignFieldPo;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/2 19:02
 **/
public interface InMessageSign {
    Integer SIGN_STRING_NULL = 40001;
    String SIGN_STRING_NULL_DOC = "sign can not null.";
    Integer SIGN_VERIFIED_FAILED = 40002;
    String SIGN_VERIFIED_FAILED_DOC = "sign verified failed";

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
