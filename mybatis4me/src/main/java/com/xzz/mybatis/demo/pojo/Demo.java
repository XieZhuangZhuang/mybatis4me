package com.xzz.mybatis.demo.pojo;

/**
 * @program: mybatis4me
 * @description: demo
 * @author: XieZhuangZhuang
 * @create: 2019-10-30 10:16
 **/
public class Demo {
    private long id;
    private String name;
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    @Override
    public String toString() {
        return "Demo [id=" + id + ", name=" + name + "]";
    }
}
