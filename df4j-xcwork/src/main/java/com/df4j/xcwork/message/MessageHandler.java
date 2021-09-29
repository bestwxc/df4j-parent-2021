package com.df4j.xcwork.message;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;

/**
 * 用于标记用作消息处理的方法
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MessageHandler {

    @AliasFor("pattern")
    String value();

    @AliasFor("value")
    String pattern();
}
