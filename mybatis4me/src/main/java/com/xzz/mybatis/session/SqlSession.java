package com.xzz.mybatis.session;

import java.util.List;

/**
 * @program: mybatis4me
 * @description: session接口
 * @author: XieZhuangZhuang
 * @create: 2019-10-30 10:29
 **/
public interface SqlSession {

    <T> T selectOne(String statement, Object parameter);
    <E> List<E> selectList(String statement);
    <E> List<E> selectList(String statement, Object parameter);
    <T> T getMapper(Class<T> type);

}
