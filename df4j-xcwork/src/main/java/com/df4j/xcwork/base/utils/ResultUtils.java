package com.df4j.xcwork.base.utils;

import com.df4j.xcwork.base.exception.ErrorCode;
import com.df4j.xcwork.base.server.MultiResult;
import com.df4j.xcwork.base.server.PageResult;
import com.df4j.xcwork.base.server.Result;
import com.df4j.xcwork.base.server.SingleResult;

import java.util.Collections;
import java.util.List;

public class ResultUtils {

    // 组装结果
    // 返回异常结果
    // 返回空结果
    // 返回对象结果
    // 返回列表结果，但是不分页
    // 返回分页列表结果


    private static Result build(String errorNo, String errorInfo, Object result) {
        return new SingleResult(errorNo, errorInfo, result);
    }





    public static Result success() {
        return build(ErrorCode.SUCCESS.getErrorCode(), "请求成功", null);
    }

    public static Result success(Object result) {
        if(result == null) {
            return new MultiResult(Collections.emptyList());
        }
        if(result instanceof List) {
            return new MultiResult((List) result);
        } else {
            return new SingleResult(result);
        }
    }

    public static Result success(Integer pageNum, Integer pageSize, Integer total, List result) {
        return new PageResult(pageNum, pageSize, total, result);
    }

    public static Result error(String errorNo, String errorInfo) {
        return new SingleResult(errorNo, errorInfo, null);
    }
}
