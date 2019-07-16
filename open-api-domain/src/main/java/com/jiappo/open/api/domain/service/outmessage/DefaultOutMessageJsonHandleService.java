package com.jiappo.open.api.domain.service.outmessage;

import com.jiappo.open.api.domain.entity.OutMessageRule;
import com.jiappo.open.api.support.model.dto.outmessage.OutMessageReq;
import org.apache.http.Header;
import org.springframework.stereotype.Service;

import java.util.Collection;

/**
 * default implement out message
 *
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/12 17:50
 **/
@Service
public class DefaultOutMessageJsonHandleService extends BaseOutMessageHandleService {
    @Override
    public Collection<Header> builderHeader(OutMessageReq req, OutMessageRule rule) {
        return null;
    }

    @Override
    public Object builderBody(OutMessageReq req, OutMessageRule rule) {
        return req.getData();
    }

    @Override
    public String builderUrl(OutMessageReq req, OutMessageRule rule) {
        return rule.getTargetHttpApi();
    }
}
