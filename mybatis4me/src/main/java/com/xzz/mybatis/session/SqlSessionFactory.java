package com.xzz.mybatis.session;

import com.xzz.mybatis.config.Configuration;

/**
 * @program: mybatis4me
 * @description: session工厂接口
 * @author: XieZhuangZhuang
 * @create: 2019-10-30 10:30
 **/
public interface SqlSessionFactory {

    Configuration getConfiguration();
    SqlSession openSession();

}
