package com.xzz.mybatis.mapper;

/**
 * @program: mybatis4me
 * @description: 存储 mapper.xml 信息
 * @author: XieZhuangZhuang
 * @create: 2019-10-30 10:57
 **/
public class MapperStatement {

    // 对应 mapper 文件中 sql 语句的 id
    private String id;

    // 对应 sql 语句
    private String sql;

    // 对应返回值类型
    private String resultType;

    // 查询类型
    private String queryType;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }

    public String getResultType() {
        return resultType;
    }

    public void setResultType(String resultType) {
        this.resultType = resultType;
    }

    public String getQueryType() {
        return queryType;
    }

    public void setQueryType(String queryType) {
        this.queryType = queryType;
    }

    @Override
    public String toString() {
        return "MapperStatement{" +
                "id='" + id + '\'' +
                ", sql='" + sql + '\'' +
                ", resultType='" + resultType + '\'' +
                ", queryType='" + queryType + '\'' +
                '}';
    }
}
