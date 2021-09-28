package com.df4j.xcwork.boot.utils;

import com.df4j.xcwork.base.exception.XcworkException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.support.SpringFactoriesLoader;
import org.springframework.lang.Nullable;
import org.springframework.util.ClassUtils;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * SpringBoot 获取spring.factories的工具类
 */
public class FactoryConfigUtils {

    private static Logger logger = LoggerFactory.getLogger(FactoryConfigUtils.class);

    private static ClassLoader getClassLoader() {
        return ClassUtils.getDefaultClassLoader();
    }


    public static Map<String, List<String>> loadSpringFactories() {
        return loadSpringFactories(getClassLoader());
    }

    public static Map<String, List<String>> loadSpringFactories(@Nullable ClassLoader classLoader) {
        Method method = null;
        try {
            method = SpringFactoriesLoader.class.getDeclaredMethod("loadSpringFactories", ClassLoader.class);
            method.setAccessible(true);
        } catch (Exception e) {
            throw new XcworkException("反射未正常获取到SpringFactoriesLoader中的loadSpringFactories方法", e);
        }
        Map<String, List<String>> res = (Map<String, List<String>>) ReflectionUtils.invokeMethod(method, null, classLoader);
        return res;
    }

    public static List<String> loadFactoryNames(String factoryTypeName) {
        return loadFactoryNames(factoryTypeName, getClassLoader());
    }

    public static List<String> loadFactoryNames(String factoryTypeName, @Nullable ClassLoader classLoader) {
        return loadSpringFactories(classLoader).getOrDefault(factoryTypeName, Collections.emptyList());
    }
}
