package com.df4j.xcwork.base.server;


import com.df4j.xcwork.base.exception.ErrorCode;

public abstract class Result<T> {

    private String errorNo = ErrorCode.SUCCESS.getErrorCode();
    private String errorInfo = "请求成功";
    private String resultType = ResultType.OBJECT;
    private T result;

    public Result(String resultType) {
        this.resultType = resultType;
    }

    public Result(String errorNo, String errorInfo, String resultType, T result) {
        this.errorNo = errorNo;
        this.errorInfo = errorInfo;
        this.resultType = resultType;
        this.result = result;
    }

    public String getErrorNo() {
        return errorNo;
    }

    public void setErrorNo(String errorNo) {
        this.errorNo = errorNo;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public T getResult() {
        return result;
    }

    public void setResult(T result) {
        this.result = result;
    }
}
