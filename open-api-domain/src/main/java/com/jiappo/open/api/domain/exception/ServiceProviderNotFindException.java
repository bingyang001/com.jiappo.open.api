package com.jiappo.open.api.domain.exception;

/**
 * @Author: lee
 * @since:1.0.0
 * @Date: 2019/7/9 13:53
 **/
public class ServiceProviderNotFindException extends RuntimeException  {
    private int errorCode;
    private String message;

    public ServiceProviderNotFindException() {
        super();
    }

    public ServiceProviderNotFindException(int errorCode, String message) {
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
