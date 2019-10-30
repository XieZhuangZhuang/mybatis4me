package com.xzz.mybatis.excutor.impl;

import com.xzz.mybatis.config.JdbcProperties;
import com.xzz.mybatis.excutor.Executor;
import com.xzz.mybatis.mapper.MapperStatement;

import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * @program: mybatis4me
 * @description: 简易执行器
 * @author: XieZhuangZhuang
 * @create: 2019-10-30 12:22
 **/
public class SimpleExecutor implements Executor {

    private JdbcProperties jdbcProperties;

    public SimpleExecutor(JdbcProperties jdbcProperties){
        this.jdbcProperties = jdbcProperties;
    }

    @Override
    public <E> List<E> query(MapperStatement ms, Object parameter) {
        List<E> result = new ArrayList<>();

        try {
            Class.forName(jdbcProperties.getDriver());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        Connection con = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // 获取连接
            con = DriverManager.getConnection(jdbcProperties.getUrl(), jdbcProperties.getUsername(),
                    jdbcProperties.getPassword());
            // 预编译 sql 语句
            preparedStatement = con.prepareStatement(ms.getSql());
            // 处理占位符
            parameterize(preparedStatement, parameter);
            // 执行 sql 语句
            resultSet = preparedStatement.executeQuery();
            // 处理结果
            handlerResultSet(resultSet, result, ms.getResultType());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 处理结果集
     * @param resultSet
     * @param result
     * @param resultType
     * @param <E>
     */
    private <E> void handlerResultSet(ResultSet resultSet, List<E> result, String resultType) {
        Class<E> clazz = null;
        try {
            // 加载 pojo 类的 class 文件，并创建 Class 对象
            clazz = (Class<E>) Class.forName(resultType);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            while (resultSet.next()){
                // 通过反射创建实例对象，和该类的属性列表
                Object entity = clazz.newInstance();
                Field[] fields = clazz.getDeclaredFields();
                // 遍历属性列表，进行查询结果映射
                for (Field field : fields) {
                    // 属性设置成可访问
                    field.setAccessible(true);
                    // 获取属性名和属性类型
                    String name = field.getName();
                    Type type = field.getGenericType();
                    // 将查询列映射到实例对象的属性上
                    if(type.toString().equals("class java.lang.String")){
                        String column = resultSet.getString(name);
                        field.set(entity, column);
                    }else if(type.toString().equals("long")){
                        long column = resultSet.getLong(name);
                        field.set(entity, column);
                    }
                    // 将查询结果添加到结果集上
                    result.add((E)entity);
                }
            }
        } catch (InstantiationException ex) {
            ex.printStackTrace();
        } catch (IllegalAccessException ex) {
            ex.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * 处理占位符
     * @param preparedStatement
     * @param parameter
     * @throws SQLException
     */
    private void parameterize(PreparedStatement preparedStatement, Object parameter) throws SQLException {
        if (parameter instanceof String) {
            preparedStatement.setString(1, (String) parameter);
        } else if (parameter instanceof Long) {
            preparedStatement.setLong(1, (Long) parameter);
        } else if (parameter instanceof Integer) {
            preparedStatement.setInt(1, (Integer) parameter);
        }
    }
}
