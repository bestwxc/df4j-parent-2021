package com.df4j.xcwork.boot.initializer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;


public class DynamicDataSourceApplicationContextInitializer implements ApplicationContextInitializer {

    private Logger logger = LoggerFactory.getLogger(DynamicDataSourceApplicationContextInitializer.class);


    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {

    }
}
