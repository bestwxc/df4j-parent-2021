package com.df4j.xcwork.base.server;


public class SingleResult<T> extends Result<T> {

    public SingleResult() {
        super(ResultType.OBJECT);
    }

    public SingleResult(Object result) {
        this();
        this.setResult((T) result);
    }

    public SingleResult(String errorNo, String errorInfo, T result) {
        super(errorNo, errorInfo, ResultType.OBJECT, result);
    }
}
