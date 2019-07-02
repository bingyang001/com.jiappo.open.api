package com.jiappo.open.api.domain.route;

import com.jiappo.open.api.support.model.dto.in.InMessageReq;
import com.jiappo.open.api.support.model.po.MessageTransferRoutePo;
import org.springframework.stereotype.Service;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/2 17:55
 **/
@Service
public class DefaultInMessageRouteService extends BaseInMessageRoute {
    /**
     * child class need impl transfer logic
     *
     * @param po
     * @param req
     * @return java.lang.Object
     * @author liguo
     * @date 2019/7/2 17:59
     * @since 1.0.0
     **/
    @Override
    public Object transfer(MessageTransferRoutePo po, InMessageReq req) {
        return null;
    }
}
