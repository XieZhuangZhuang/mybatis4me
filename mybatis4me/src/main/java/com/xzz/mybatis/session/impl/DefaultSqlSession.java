package com.xzz.mybatis.session.impl;

import com.xzz.mybatis.config.Configuration;
import com.xzz.mybatis.excutor.Executor;
import com.xzz.mybatis.mapper.MapperProxy;
import com.xzz.mybatis.session.SqlSession;

import java.lang.reflect.Proxy;
import java.util.List;

/**
 * @program: mybatis4me
 * @description: 默认session会话
 * @author: XieZhuangZhuang
 * @create: 2019-10-30 10:46
 **/
public class DefaultSqlSession implements SqlSession {

    private Configuration configuration;

    private Executor executor;

    public DefaultSqlSession(Configuration configuration, Executor executor){
        this.configuration = configuration;
        this.executor = executor;
    }

    @Override
    public <T> T selectOne(String statement, Object parameter) {
        List<T> list = executor.query(configuration.getMapperMap().get(statement), parameter);
        if(list.size() > 0){
            return list.get(0);
        }
        return null;
    }

    @Override
    public <E> List<E> selectList(String statement) {
        List<E> list = executor.query(configuration.getMapperMap().get(statement), null);
        return list;
    }

    @Override
    public <E> List<E> selectList(String statement, Object parameter) {
        List<E> list = executor.query(configuration.getMapperMap().get(statement), parameter);
        return list;
    }

    public <T> T getMapper(Class<T> type) {
        System.out.println();
        T newProxyInstance = (T) Proxy.newProxyInstance(type.getClassLoader(),new Class[]{type},new MapperProxy(this));
        return newProxyInstance;
    }
}
