package com.xzz.mybatis.config;

import com.xzz.mybatis.mapper.MapperStatement;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: mybatis4me
 * @description: 存储config配置
 * @author: XieZhuangZhuang
 * @create: 2019-10-30 10:23
 **/
public class Configuration {

    private JdbcProperties jdbcProperties;

    public Map<String, MapperStatement> getMapperMap() {
        return mapperMap;
    }

    // 存储 mapper 文件中所有的 query
    private Map<String, MapperStatement> mapperMap = new HashMap<String, MapperStatement>();

    public JdbcProperties getJdbcProperties() {
        return jdbcProperties;
    }

    public void setJdbcProperties(JdbcProperties jdbcProperties) {
        this.jdbcProperties = jdbcProperties;
    }

    public void addMapperStatement(MapperStatement mapperStatement) {
        mapperMap.put(mapperStatement.getId(), mapperStatement);
    }
}
