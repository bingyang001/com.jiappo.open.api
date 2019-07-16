package com.jiappo.open.api.domain.service;

import com.jiappo.open.api.domain.entity.OutMessageRule;
import com.jiappo.open.api.support.model.dto.outmessage.OutMessageReq;
import org.apache.http.Header;

import java.util.Collection;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/12 17:47
 **/
public interface OutMessageHandle {
    /**
     * builder http request header
     *
     * @param req  message
     * @param rule message rule
     * @return {@link java.util.Collection<org.apache.http.Header>}
     * @author liguo
     * @date 2019/7/12 17:59
     * @since 1.0.0
     **/
    Collection<Header> builderHeader(final OutMessageReq req, final OutMessageRule rule);

    /**
     * builder body
     *
     * @param req  req message
     * @param rule message rule
     * @return java.lang.String
     * @author liguo
     * @date 2019/7/12 18:08
     * @since 1.0.0
     **/
    Object builderBody(final OutMessageReq req, final OutMessageRule rule);

    /**
     * builder target url
     *
     * @param req  message
     * @param rule rule
     * @return java.lang.String
     * @author liguo
     * @date 2019/7/12 19:10
     * @since 1.0.0
     **/
    String builderUrl(final OutMessageReq req, final OutMessageRule rule);
}
