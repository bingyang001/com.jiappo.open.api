package com.jiappo.open.api.domain.exception;


/**
 * message sign authentic exception
 *
 * @author bingy
 * @since 1.0.0
 */
public class SignAuthException extends RuntimeException {
    private int errorCode;
    private String message;

    public SignAuthException() {
        super();
    }

    public SignAuthException(int errorCode, String message) {
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
