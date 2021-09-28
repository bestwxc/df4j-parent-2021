package com.df4j.xcwork.base.jdbc.datasource;


import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@Documented
public @interface UseDataSource {


    @AliasFor("dataSourceKey")
    String value() default "";

    /**
     * 数据源key
     * @return
     */
    @AliasFor("value")
    String dataSourceKey() default "";


    /**
     * 节点key
     * @return
     */
    String nodeKey() default "";

    /**
     * 使用的数据源节点名称
     * @return
     */
    boolean useMaster() default true;
}
