package com.jiappo.open.api.domain.service.in;

import com.google.common.base.Strings;
import com.jiappo.open.api.domain.entity.InMessageRule;
import com.jiappo.open.api.domain.exception.SecretKeyAuthException;
import com.jiappo.open.api.domain.service.ErrorConstant;
import com.jiappo.open.api.domain.ticket.MessageSignFactory;
import com.jiappo.open.api.support.model.bo.TicketFieldBo;
import com.jiappo.open.api.support.model.dto.in.InMessageReq;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/2 17:55
 **/
@Service
public class DefaultInMessageHandleService extends BaseInMessageHandleService {
    private final static Logger LOGGER = LoggerFactory.getLogger(DefaultInMessageHandleService.class);
    /**
     * verified request secret key ,if failed then throw app exception. child class can override
     *
     * @param platformName    platform name
     * @param reqSecretKey    request secret key
     * @param originSecretKey db origin secret key
     * @return void
     * @author liguo
     * @date 2019/7/2 18:42
     * @since 1.0.0
     **/
    @Override
    protected void verifiedSecretKey(String platformName, String reqSecretKey, String originSecretKey)  {
        if (Strings.isNullOrEmpty(originSecretKey)) {
            LOGGER.warn("please settings secret key in db.");
            return;
        }

        if (!originSecretKey.equals(reqSecretKey)) {
            LOGGER.error("{} request secret key not match", platformName);
            throw new SecretKeyAuthException(ErrorConstant.SignError.SECRET_KEY_NO_MATCH_ERROR
                    , ErrorConstant.SignError.SECRET_KEY_NO_MATCH_ERROR_DOC);
        }
    }

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
    protected void verifiedSign(InMessageReq inMessageReq, InMessageRule po) {

        TicketFieldBo ticketFieldBo = TicketFieldBo
                .builder()
                .expiredMinute(po.getTicketExpiredTimeMinute())
                .privateKey(po.getPrivateKey())
                .publicKey(po.getPublicKey())
                .useOneExpired(true)
                .fieldMap(po.getTicketFieldMap())
                .isPreCreated(po.getIsPreCreated())
                .build();
        MessageSignFactory
                .factory(po.getTicketImplService())
                .verifiedTicket(inMessageReq, ticketFieldBo);
    }
}
