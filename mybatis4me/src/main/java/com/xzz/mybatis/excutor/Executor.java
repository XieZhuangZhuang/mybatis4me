package com.xzz.mybatis.excutor;

import com.xzz.mybatis.mapper.MapperStatement;

import java.util.List;

/**
 * @program: mybatis4me
 * @description: 执行器接口
 * @author: XieZhuangZhuang
 * @create: 2019-10-30 12:20
 **/
public interface Executor {

    <E> List<E> query(MapperStatement ms, Object parameter);

}
