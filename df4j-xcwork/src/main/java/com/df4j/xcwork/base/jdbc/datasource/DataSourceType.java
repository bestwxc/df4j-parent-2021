package com.df4j.xcwork.base.jdbc.datasource;

import com.df4j.xcwork.base.exception.XcworkException;

public enum DataSourceType {
    HIKARI(
            "hikari",
            "com.zaxxer.hikari.HikariDataSource",
            null
    ),
    DBCP2(
            "dbcp2",
            "org.apache.commons.dbcp2.BasicDataSource",
            null
    ),
    DRUID(
            "druid",
            "com.alibaba.druid.pool.DruidDataSource",
            null
    ),
    C3P0(
            "c3p0",
            "com.mchange.v2.c3p0.ComboPooledDataSource",
            "init"
    );

    private String name;
    private String fullName;
    private String initMethodName;

    private DataSourceType(String name, String fullName, String initMethodName) {
        this.name = name;
        this.fullName = fullName;
        this.initMethodName = initMethodName;
    }

    public static DataSourceType getDataSourceType(String name) {
        try {
            return DataSourceType.valueOf(name.toUpperCase());
        } catch (Exception e) {
            throw new XcworkException("[" + name + "]数据源类型不匹配", e);
        }
    }

    public String getName() {
        return name;
    }

    public String getFullName() {
        return fullName;
    }

    public String getInitMethodName() {
        return initMethodName;
    }
}
