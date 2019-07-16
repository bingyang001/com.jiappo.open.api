package com.jiappo.open.api.domain.event;

import com.jiappo.open.api.domain.exception.SecretKeyAuthException;
import com.jiappo.open.api.domain.exception.SignAuthException;
import com.jiappo.open.api.domain.ticket.VerifiedStatusEnum;
import com.jiappo.open.api.support.model.dto.inmessage.InMessageReq;
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
    private String messageSource;
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
                .messageSource(req.getMessageSource())
                .messageType(req.getMessageType())
                .secretKey(req.getSecretKey())
                .messageId(req.getMessageId())
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
                .messageSource(req.getMessageSource())
                .messageType(req.getMessageType())
                .secretKey(req.getSecretKey())
                .messageId(req.getMessageId())
                .originTicket(req.getSign())
                .build();
    }


}
