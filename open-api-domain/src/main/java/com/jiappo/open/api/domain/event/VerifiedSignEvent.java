package com.jiappo.open.api.domain.event;

import com.jiappo.open.api.domain.exception.SecretKeyAuthException;
import com.jiappo.open.api.domain.exception.SignAuthException;
import com.jiappo.open.api.domain.ticket.VerifiedStatusEnum;
import com.jiappo.open.api.support.model.dto.inmessage.InMessageReq;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/10 15:00
 **/
@Data
@Builder
public class VerifiedSignEvent extends VerifiedTicketEvent {
    private String platformName;
    private String messageType;
    private String messageId;
    private String secretKey;
    private Integer verifiedStatus;
    private String verifiedType;
    @Builder.Default
    private Date verifiedDate = new Date();
    private String verifiedDescribe;
    private Throwable throwable;
    private String originTicket;
    private Integer recordId;

    /**
     * verified success
     *
     * @param req          req message
     * @param verifiedType verified type value secret or sign
     * @return com.jiappo.open.api.domain.event.VerifiedSignEvent
     * @author liguo
     * @date 2019/7/10 15:43
     * @since 1.0.0
     **/
    public static VerifiedSignEvent success(final InMessageReq req) {
        return VerifiedSignEvent
                .builder()
                .verifiedStatus(VerifiedStatusEnum.VERIFIED_SUCCESS.getCode())
                .verifiedDescribe("ok")
                .verifiedType("sign")
                .platformName(req.getPlatformName())
                .messageType(req.getMessageType())
                .verifiedDate(new Date())
                .secretKey(req.getSecretKey())
                .messageId(req.getBatchId())
                .originTicket(req.getSign())
                .build();
    }

    /**
     * verified failed
     *
     * @param req          req message
     * @param throwable    exception
     * @param verifiedType verified type value secret or sign
     * @return com.jiappo.open.api.domain.event.VerifiedSignEvent
     * @author liguo
     * @date 2019/7/10 15:44
     * @since 1.0.0
     **/
    public static VerifiedSignEvent failed(final InMessageReq req
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

        return VerifiedSignEvent
                .builder()
                .verifiedStatus(errorCode)
                .verifiedDescribe(errorMessage)
                .verifiedType("sign")
                .platformName(req.getPlatformName())
                .messageType(req.getMessageType())
                .verifiedDate(new Date())
                .secretKey(req.getSecretKey())
                .messageId(req.getBatchId())
                .originTicket(req.getSign())
                .build();
    }
}
