package com.jiappo.open.api.domain.ticket;

import com.hummer.spring.plugin.context.SpringApplicationContext;
import com.jiappo.open.api.domain.exception.ServiceProviderNotFindException;

import java.util.Map;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/2 19:04
 **/
public class MessageSignFactory {

    /**
     * factory sign instance.
     *
     * @param signType sign type aes or md5 or rsa
     * @return com.jiappo.open.api.domain.sign.BaseMessageSign
     * @author liguo
     * @date 2019/7/9 13:55
     * @since 1.0.0
     **/
    public static BaseMessageTicket factory(final String signType) {

        Map<String, BaseMessageTicket> signMap = SpringApplicationContext
                .getBeans(BaseMessageTicket.class);

        if (!signMap.containsKey(signType)) {
            throw new ServiceProviderNotFindException(50000, "sig provider not find");
        }

        return signMap.get(signType);
    }
}
