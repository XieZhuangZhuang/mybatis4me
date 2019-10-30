package com.xzz.mybatis.mapper;

import com.xzz.mybatis.session.SqlSession;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.util.Collection;

/**
 * @program: mybatis4me
 * @description: 执行具体的代理方法
 * @author: XieZhuangZhuang
 * @create: 2019-10-30 15:46
 **/
public class MapperProxy implements InvocationHandler {

    private SqlSession sqlSession;
    public MapperProxy(SqlSession sqlSession){
        this.sqlSession = sqlSession;
    }
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 获取查询方法的全路径名
        String statement = method.getDeclaringClass().getName() + "." + method.getName();
        // 判断返回值类型是否是集合，是集合则调用 selectList，否则调用 selectOne
        if(Collection.class.isAssignableFrom(method.getReturnType())){
            return sqlSession.selectList(statement, args == null ? null : args[0]);
        }else {
            return sqlSession.selectOne(statement, args == null ? null : args[0]);
        }
    }
}

