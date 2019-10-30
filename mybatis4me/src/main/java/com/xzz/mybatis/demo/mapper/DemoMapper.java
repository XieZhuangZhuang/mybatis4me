package com.xzz.mybatis.demo.mapper;

import com.xzz.mybatis.demo.pojo.Demo;

import java.util.List;

/**
 * @program: mybatis4me
 * @description: mapper接口
 * @author: XieZhuangZhuang
 * @create: 2019-10-30 10:15
 **/
public interface DemoMapper {

    Demo getById(Long id);

    List<Demo> getAll();

}
