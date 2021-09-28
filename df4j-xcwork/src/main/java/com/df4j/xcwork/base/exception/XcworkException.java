package com.df4j.xcwork.base.exception;


/**
 * 所有框架异常的超类
 */
public class XcworkException extends RuntimeException {

    public XcworkException() {
    }

    public XcworkException(String message) {
        super(message);
    }

    public XcworkException(String message, Throwable cause) {
        super(message, cause);
    }

    public XcworkException(Throwable cause) {
        super(cause);
    }

    public XcworkException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
