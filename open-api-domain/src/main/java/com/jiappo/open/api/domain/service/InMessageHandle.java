package com.jiappo.open.api.domain.service;

import com.jiappo.open.api.domain.entity.InMessageRule;
import com.jiappo.open.api.support.model.dto.inmessage.InMessageReq;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/2 17:28
 **/
public interface InMessageHandle {
    /**
     * verified in site message
     *
     * @param inMessageReq message
     * @param rule           rule
     * @return void
     * @author liguo
     * @date 2019/7/2 17:57
     * @since 1.0.0
     **/
    void verified(InMessageReq inMessageReq, InMessageRule rule);

    /**
     * transfer request
     *
     * @param rule           rule
     * @param req
     * @return java.lang.Object
     * @author liguo
     * @date 2019/7/2 17:40
     * @since 1.0.0
     **/
    Object transfer(InMessageRule rule, InMessageReq req);
}
