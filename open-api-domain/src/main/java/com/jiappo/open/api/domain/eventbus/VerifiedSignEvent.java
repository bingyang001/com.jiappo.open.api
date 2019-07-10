package com.jiappo.open.api.domain.eventbus;

import com.jiappo.open.api.domain.exception.SignAuthException;
import com.jiappo.open.api.domain.ticket.VerifiedStatusEnum;
import com.jiappo.open.api.support.model.dto.in.InMessageReq;
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
public class VerifiedSignEvent {
    private String platformName;
    private String messageType;
    private String messageId;
    private String signValue;
    private String secretKey;
    private Integer verifiedStatus;
    private String verifiedType;
    @Builder.Default
    private Date verifiedDate = new Date();
    private String verifiedDescribe;
    private Throwable throwable;

    /**
     * verified success
     *
     * @param req          req message
     * @param verifiedType verified type value secret or sign
     * @return com.jiappo.open.api.domain.eventbus.VerifiedSignEvent
     * @author liguo
     * @date 2019/7/10 15:43
     * @since 1.0.0
     **/
    public static VerifiedSignEvent success(final InMessageReq req, final String verifiedType) {
        String tempVerifiedType = verifiedType(verifiedType);
        return VerifiedSignEvent
                .builder()
                .verifiedStatus(VerifiedStatusEnum.VERIFIED_SUCCESS.getCode())
                .verifiedDescribe("ok")
                .verifiedType(tempVerifiedType)
                .platformName(req.getPlatformName())
                .messageType(req.getMessageType())
                .verifiedDate(new Date())
                .secretKey(req.getSecretKey())
                .signValue(req.getSign())
                .messageId(req.getBatchId())
                .build();
    }

    /**
     * verified failed
     *
     * @param req          req message
     * @param throwable    exception
     * @param verifiedType verified type value secret or sign
     * @return com.jiappo.open.api.domain.eventbus.VerifiedSignEvent
     * @author liguo
     * @date 2019/7/10 15:44
     * @since 1.0.0
     **/
    public static VerifiedSignEvent failed(final InMessageReq req
            , final Throwable throwable, final String verifiedType) {
        String tempVerifiedType = verifiedType(verifiedType);
        int errorCode = 2;
        String errorMessage;
        if (throwable instanceof SignAuthException) {
            errorCode = ((SignAuthException) throwable).getErrorCode();
            errorMessage = throwable.getMessage();
        } else {
            errorMessage = throwable.toString();
        }

        return VerifiedSignEvent
                .builder()
                .verifiedStatus(errorCode)
                .verifiedDescribe(errorMessage)
                .verifiedType(tempVerifiedType)
                .platformName(req.getPlatformName())
                .messageType(req.getMessageType())
                .verifiedDate(new Date())
                .secretKey(req.getSecretKey())
                .signValue(req.getSign())
                .messageId(req.getBatchId())
                .build();
    }

    private static String verifiedType(final String verifiedType) {
        String tempVerifiedType = "secret";
        if ("sign".equals(verifiedType)) {
            tempVerifiedType = "sign";
        }
        return tempVerifiedType;
    }
}
