package com.xzz.mybatis;

import com.xzz.mybatis.demo.mapper.DemoMapper;
import com.xzz.mybatis.demo.pojo.Demo;
import com.xzz.mybatis.session.SqlSession;
import com.xzz.mybatis.session.SqlSessionFactory;
import com.xzz.mybatis.session.SqlSessionFactoryBuilder;

import java.io.InputStream;
import java.util.List;

/**
 * @program: mybatis4me
 * @description: main
 * @author: XieZhuangZhuang
 * @create: 2019-10-30 13:53
 **/
public class App {
    public static void main(String[] args) {
        InputStream inputStream = App.class.getClassLoader().getResourceAsStream("Mybatis-config.xml");
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
        SqlSession sqlSession = sqlSessionFactory.openSession();
        List<Demo> list = sqlSession.selectList("com.xzz.mybatis.demo.mapper.DemoMapper.getAll");
        System.out.println(list);
        DemoMapper mapper = sqlSession.getMapper(DemoMapper.class);
        Demo demo = mapper.getById(1L);
        System.out.println(demo);
    }
}
