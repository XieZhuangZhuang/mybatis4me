package com.xzz.mybatis.session.impl;

import com.xzz.mybatis.config.Configuration;
import com.xzz.mybatis.excutor.Executor;
import com.xzz.mybatis.excutor.impl.SimpleExecutor;
import com.xzz.mybatis.session.SqlSession;
import com.xzz.mybatis.session.SqlSessionFactory;

/**
 * @program: mybatis4me
 * @description: 默认session工厂，用于创建session会话
 * @author: XieZhuangZhuang
 * @create: 2019-10-30 10:44
 **/
public class DefaultSqlSessionFactory implements SqlSessionFactory {

    private Configuration configuration;

    public DefaultSqlSessionFactory(Configuration configuration){
        this.configuration = configuration;
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public SqlSession openSession() {
        Executor executor = new SimpleExecutor(configuration.getJdbcProperties());
        return new DefaultSqlSession(configuration, executor);
    }
}
