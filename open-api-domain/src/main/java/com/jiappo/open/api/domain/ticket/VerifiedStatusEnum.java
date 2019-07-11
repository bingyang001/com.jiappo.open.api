package com.jiappo.open.api.domain.ticket;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/10 15:19
 **/
public enum VerifiedStatusEnum {
    /**
     * wait verified
     **/
    NO_VERIFIED(0, "wait verified"),
    /**
     * verified success
     **/
    VERIFIED_SUCCESS(1, "verified success"),
    /**
     * wait failed
     **/
    VERIFIED_FAILED(2, "failed");
    private int code;
    private String message;

    VerifiedStatusEnum(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public static VerifiedStatusEnum getByCode(int code) {
        for (VerifiedStatusEnum statusEnum : VerifiedStatusEnum.values()) {
            if (code == statusEnum.code) {
                return statusEnum;
            }
        }

        return VERIFIED_FAILED;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}
