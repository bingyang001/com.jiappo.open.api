package com.jiappo.open.api.domain.exception;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/11 16:34
 **/
public class SecretKeyAuthException extends RuntimeException {
    private int errorCode;
    private String message;

    public SecretKeyAuthException() {
        super();
    }

    public SecretKeyAuthException(int errorCode, String message) {
        super();
        this.errorCode = errorCode;
        this.message = message;
    }

    public int getErrorCode() {
        return errorCode;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
