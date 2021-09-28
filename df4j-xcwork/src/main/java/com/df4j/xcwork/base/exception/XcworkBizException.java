package com.df4j.xcwork.base.exception;

/**
 * 业务型异常， 包含错误码和错误信息
 */
public class XcworkBizException extends XcworkException {

    private String errorNo = ErrorCode.UNHANDLE_BIZ_EXCEPTION.getErrorCode();

    public XcworkBizException(String message) {
        super(message);
    }

    public XcworkBizException(Throwable cause) {
        super(cause);
    }

    public XcworkBizException(String errorNo, String message) {
        super(message);
        this.errorNo = errorNo;
    }

    public XcworkBizException(String errorNo, String message, Throwable cause) {
        super(message, cause);
        this.errorNo = errorNo;
    }

    public XcworkBizException(String errorNo, String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
        this.errorNo = errorNo;
    }

    public String getErrorNo() {
        return errorNo;
    }

    public String getErrorInfo() {
        return super.getMessage();
    }
}
