package com.df4j.xcwork.autoconfigure.jdbc;

import org.springframework.boot.autoconfigure.AutoConfigureBefore;
import org.springframework.context.annotation.Configuration;

@Configuration
@AutoConfigureBefore({org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration.class})
public class DataSourceAutoConfiguration {
}
