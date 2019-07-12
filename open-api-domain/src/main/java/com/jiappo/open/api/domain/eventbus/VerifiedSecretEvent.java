package com.jiappo.open.api.domain.eventbus;

import com.jiappo.open.api.domain.exception.SecretKeyAuthException;
import com.jiappo.open.api.domain.exception.SignAuthException;
import com.jiappo.open.api.domain.ticket.VerifiedStatusEnum;
import com.jiappo.open.api.support.model.dto.in.InMessageReq;
import lombok.Builder;
import lombok.Data;


/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/12 14:41
 **/
@Builder
@Data
public class VerifiedSecretEvent {
    private String platformName;
    private String messageType;
    private String messageId;
    private String secretKey;
    private Integer verifiedStatus;
    private String verifiedDescribe;
    private Throwable throwable;
    private String originTicket;

    public static VerifiedSecretEvent success(final InMessageReq req) {
        return VerifiedSecretEvent
                .builder()
                .verifiedStatus(VerifiedStatusEnum.VERIFIED_SUCCESS.getCode())
                .verifiedDescribe("ok")
                .platformName(req.getPlatformName())
                .messageType(req.getMessageType())
                .secretKey(req.getSecretKey())
                .messageId(req.getBatchId())
                .originTicket(req.getSign())
                .build();
    }


    public static VerifiedSecretEvent failed(final InMessageReq req
            , final Throwable throwable) {
        int errorCode = VerifiedStatusEnum.VERIFIED_FAILED.getCode();
        String errorMessage;
        if (throwable instanceof SignAuthException) {
            errorCode = ((SignAuthException) throwable).getErrorCode();
            errorMessage = throwable.getMessage();
        } else if (throwable instanceof SecretKeyAuthException){
            errorCode = ((SecretKeyAuthException) throwable).getErrorCode();
            errorMessage = throwable.getMessage();
        } else{
            errorMessage = throwable.toString();
        }

        return VerifiedSecretEvent
                .builder()
                .verifiedStatus(errorCode)
                .verifiedDescribe(errorMessage)
                .platformName(req.getPlatformName())
                .messageType(req.getMessageType())
                .secretKey(req.getSecretKey())
                .messageId(req.getBatchId())
                .originTicket(req.getSign())
                .build();
    }


}
