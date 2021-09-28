package com.df4j.xcwork.web.advice;

import com.df4j.xcwork.base.exception.ErrorCode;
import com.df4j.xcwork.base.exception.XcworkBizException;
import com.df4j.xcwork.base.exception.XcworkException;
import com.df4j.xcwork.base.server.Result;
import com.df4j.xcwork.base.utils.ResultUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class XcworkExceptionHandler {
    private final Logger logger = LoggerFactory.getLogger(XcworkExceptionHandler.class);

    @ExceptionHandler
    @ResponseBody
    public Result handler(Throwable t) {
        String errorNo = null;
        String errorInfo = null;
        if (t instanceof XcworkBizException) {
            // 业务中已经定义的异常
            XcworkBizException xbe = (XcworkBizException) t;
            errorNo = xbe.getErrorNo();
            errorInfo = xbe.getErrorInfo();
            logger.info(String.format("业务中已经定义的业务错误场景, errorNo: %s, errorInfo: %s", errorNo, errorInfo), t);
        } else {
            // 业务中未定义的异常
            ErrorCode errorCode = null;
            if (t instanceof XcworkException) {
                errorCode = ErrorCode.UNHANDLE_BIZ_EXCEPTION;
            } else if (t instanceof RuntimeException) {
                errorCode = ErrorCode.UNHANDLE_RUNTIME_EXCEPTION;
            } else {
                errorCode = ErrorCode.UNHANDLE_SYSTEM_ERROR;
            }
            errorNo = errorCode.getErrorCode();
            errorInfo = errorCode.getErrorInfo();
            logger.error("业务中未定义的业务场景", t);
        }
        return ResultUtils.error(errorNo, errorInfo);
    }
}
