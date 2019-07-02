package com.jiappo.open.api.domain.sign;

import com.jiappo.open.api.support.model.dto.in.InMessageReq;
import com.jiappo.open.api.support.model.po.MessageTransferRoutePo;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/2 19:02
 **/
public interface InMessageSign {
    void verified(InMessageReq inMessageReq, MessageTransferRoutePo po);
}
