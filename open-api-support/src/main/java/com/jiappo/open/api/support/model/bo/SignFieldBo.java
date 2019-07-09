package com.jiappo.open.api.support.model.bo;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

/**
 * sign field
 */
@Builder
@Data
public class SignFieldBo {
    /**
     * sign field map
     */
    private Map<String, Object> fieldMap;
    /**
     * expired time unit minute
     */
    private int expiredMinute;
    /**
     * used expired after
     */
    private boolean useOneExpired;
    /**
     * <p>
     * if rsa encryption then private key
     * if aes encryption then key
     * </p>
     */
    private String privateKey;
    /**
     * <p>
     * if rsa encryption then public key
     * if aes encryption then iv
     * </p>
     */
    private String publicKey;
}
