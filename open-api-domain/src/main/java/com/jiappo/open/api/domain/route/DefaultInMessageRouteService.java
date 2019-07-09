package com.jiappo.open.api.domain.route;

import com.jiappo.open.api.domain.sign.MessageSignFactory;
import com.jiappo.open.api.support.model.bo.SignFieldBo;
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
     * verified request sign
     *
     * @param inMessageReq request data
     * @param po           route type
     * @return void
     * @author liguo
     * @date 2019/7/2 18:56
     * @since 1.0.0
     **/
    @Override
    protected void verifiedSign(InMessageReq inMessageReq, MessageTransferRoutePo po) {
        SignFieldBo signFieldBo = SignFieldBo
                .builder()
                .expiredMinute(po.getSignExpiredTimeMinute())
                .privateKey(po.getPrivateKey())
                .publicKey(po.getPublicKey())
                .useOneExpired(true)
                .fieldMap(po.getSignField())
                .build();
        MessageSignFactory.factory(po.getSignImplService()).verified(inMessageReq, signFieldBo);
    }
}
