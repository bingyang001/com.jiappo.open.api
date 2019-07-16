package com.jiappo.open.api.domain.service;

import com.jiappo.open.api.domain.entity.OutMessageRule;
import com.jiappo.open.api.support.model.dto.outmessage.OutMessageReq;
import org.apache.http.Header;
import org.apache.http.NameValuePair;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

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
     * builder body,will serial json content send to server
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
     * builder form body content
     *
     * @param req  req message
     * @param rule rule
     * @return {@link java.util.List<org.apache.http.NameValuePair>}
     * @author liguo
     * @date 2019/7/16 10:31
     * @since 1.0.0
     **/
    default List<NameValuePair> builderFormBody(final OutMessageReq req, final OutMessageRule rule) {
        return Collections.emptyList();
    }

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
