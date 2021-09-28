package com.df4j.xcwork.base.exception;

/**
 * 异常字典
 */
public enum ErrorCode {

    SUCCESS("0", "成功"),
    UNHANDLE_SYSTEM_ERROR("E999999", "未处理的系统异常"),
    UNHANDLE_RUNTIME_EXCEPTION("E999989","未处理的运行时异常"),
    UNHANDLE_BIZ_EXCEPTION("E999979", "未处理的业务异常");

    private final String errorCode;

    private final String errorInfo;

    ErrorCode(String errorCode, String errorInfo) {
        this.errorCode = errorCode;
        this.errorInfo = errorInfo;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getErrorInfo() {
        return this.errorInfo;
    }

    @Override
    public String toString() {
        return String.format("ErrorCode[%s:%s:%s]", this.name(), this.getErrorCode(), this.getErrorInfo());
    }
}
