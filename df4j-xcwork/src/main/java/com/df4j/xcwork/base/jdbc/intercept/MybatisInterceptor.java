package com.df4j.xcwork.base.jdbc.intercept;

import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.SqlCommandType;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Signature;
import org.springframework.util.ObjectUtils;

import java.util.Map;

/**
 * mybatis拦截器
 */
@Intercepts(@Signature(type = Executor.class, method = "update", args = {MappedStatement.class, Object.class}))
public class MybatisInterceptor implements Interceptor {


    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        Object param = args[1];
        if (!ObjectUtils.isEmpty(param)) {
            MappedStatement mappedStatement = (MappedStatement) args[0];
            String id = mappedStatement.getId();
            SqlCommandType sqlCommandType = mappedStatement.getSqlCommandType();
            boolean isMap = param instanceof Map;
            if (SqlCommandType.INSERT.equals(sqlCommandType)) {
                // 新增时处理
            }
            if (SqlCommandType.INSERT.equals(sqlCommandType) || SqlCommandType.UPDATE.equals(sqlCommandType)) {
                // 新增或更新都处理
            }
        }
        return invocation.proceed();
    }
}
