package com.df4j.xcwork.base.jdbc.datasource;

import com.df4j.xcwork.base.exception.XcworkException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;
import org.springframework.util.Assert;
import org.springframework.util.ObjectUtils;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

public class DynamicDataSource extends AbstractRoutingDataSource {

    private Logger logger = LoggerFactory.getLogger(DynamicDataSource.class);

    private boolean finishInit = false;

    //  用于存放当前数据源Bean 名称
    private ThreadLocal<String> currentDataSourceKey = new ThreadLocal<>();

    // 用于临时存放数据源引用
    private Map<String, DataSource> tmpTargetDataSources = new HashMap<>();

    // 默认数据库集群
    private String defaultDateSource = null;

    // 以集群为单位存放
    // 数据源节点key容器
    private Map<String, Map<String, String>> nodes = new HashMap<>();

    // 各数据库集群默认节点容器
    private Map<String, String> defaultNodeMap = new HashMap<>();

    @Override
    protected Object determineCurrentLookupKey() {
        String key = currentDataSourceKey.get();
        if (ObjectUtils.isEmpty(key)) {
            logger.debug("未指定当前连接的数据源，使用默认数据源: {}", key);
        }
        return key;
    }


    /**
     * 根据数据库key和节点key拼接成唯一的对象名称
     *
     * @param dataSourceKey
     * @param nodeKey
     * @return
     */
    public String getDataSourceKey(String dataSourceKey, String nodeKey) {
        return String.format("%s-%s-DataSource", dataSourceKey, nodeKey);
    }

    /**
     * 添加一个数据库集群的数据源
     *
     * @param key
     * @param defaultNodeKey
     * @param dataSources
     */
    public synchronized void addDataSource(String key, String defaultNodeKey, Map<String, DataSource> dataSources) {
        synchronized (this) {
            if (finishInit) {
                throw new XcworkException("不支持对已经初始化的动态数据源中添加新的数据源");
            }
            Assert.hasText(key, "数据源key不能为空");
            Assert.hasText(defaultNodeKey, "数据源默认节点key不能为空");
            Assert.notEmpty(dataSources, "数据源节点集合不能为空");
            Assert.isTrue(dataSources.containsKey(defaultNodeKey), "数据源节点集合中找不到指定的默认节点key");
            // 将所有节点放入集合
            Map<String, String> keyMap = new HashMap<>();
            for (String nodeKey : dataSources.keySet()) {
                DataSource dataSource = dataSources.get(nodeKey);
                String saveKey = getDataSourceKey(key, nodeKey);
                this.tmpTargetDataSources.put(saveKey, dataSource);
                keyMap.put(nodeKey, saveKey);
            }
            nodes.put(key, keyMap);
            // 将所有的默认节点放入集合
            defaultNodeMap.put(key, defaultNodeKey);
        }
    }

    /**
     * 设置默认的数据源key
     *
     * @param defaultDateSource
     */
    public void setDefaultDateSource(String defaultDateSource) {
        Assert.notEmpty(nodes, "数据源节点为空， 请先添加数据源节点， 再设置默认节点");
        Assert.isTrue(nodes.containsKey(defaultDateSource), "无法在已经添加的数据源中找到指定的数据源");
        this.defaultDateSource = defaultDateSource;
    }

    public void init() {
        synchronized (this) {
            if (finishInit) {
                throw new XcworkException("动态数据源已经初始化，请勿重复初始化");
            }
            try {
                // 设置目标数据源集合
                Map targetDataSources = new HashMap();
                targetDataSources.putAll(this.tmpTargetDataSources);
                this.setTargetDataSources(targetDataSources);
                // 设置默认数据源
                Assert.hasText(this.defaultDateSource, "未找到默认的数据源key, 请先使用setDefaultDateSource设置");
                String defaultSaveKey = nodes.get(defaultDateSource).get(defaultNodeMap.get(defaultDateSource));
                DataSource defaultTargetDataSource = tmpTargetDataSources.get(defaultSaveKey);
                this.setDefaultTargetDataSource(defaultTargetDataSource);
                // 初始化动态数据源
                this.afterPropertiesSet();
                // 清除临时数据源集合的引用
                this.tmpTargetDataSources.clear();
            } catch (Exception e) {
                throw new XcworkException("初始化动态数据源出现异常", e);
            }
        }
    }

    /**
     * 设置当前逻辑使用的数据源
     * @param dataSourceKey
     * @param nodeKey
     * @param useDefault
     */
    public void setCurrentDataSource(String dataSourceKey, String nodeKey, boolean useDefault) {
        String selectDataSourceKey = null;
        String selectNodeKey = null;
        if(this.nodes.containsKey(dataSourceKey)) {
            selectDataSourceKey = dataSourceKey;
        } else {
            selectDataSourceKey = this.defaultDateSource;
        }
        if(!useDefault &&this.nodes.get(selectDataSourceKey).containsKey(nodeKey)) {
            selectNodeKey = this.defaultNodeMap.get(selectDataSourceKey);
        }
        String selectKey = this.nodes.get(selectDataSourceKey).get(nodeKey);
        this.currentDataSourceKey.set(selectKey);
        if(logger.isInfoEnabled()) {
            logger.info("设置当前逻辑使用的数据源, 传入dataSource: {}, node: {}, useDefault, 实际: dataSource: {}, node: {}",
                    dataSourceKey, nodeKey, useDefault, selectDataSourceKey, selectNodeKey);
        }
    }

    /**
     * 设置当前使用的数据源
     * @param dataSourceKey
     */
    public void setCurrentDataSourceKey(String dataSourceKey) {
        this.setCurrentDataSource(dataSourceKey, null, true);
    }

    /**
     * 移除当前使用的数据源
     */
    public void remoreCurrentDataSource() {
        this.currentDataSourceKey.remove();
    }
}
