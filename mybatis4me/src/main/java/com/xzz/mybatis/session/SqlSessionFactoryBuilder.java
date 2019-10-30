package com.xzz.mybatis.session;

import com.xzz.mybatis.config.Configuration;
import com.xzz.mybatis.config.JdbcProperties;
import com.xzz.mybatis.mapper.MapperStatement;
import com.xzz.mybatis.session.impl.DefaultSqlSessionFactory;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

/**
 * @program: mybatis4me
 * @description: 读取配置文件，根据配置文件信息创建session工厂
 * @author: XieZhuangZhuang
 * @create: 2019-10-30 10:27
 **/
public class SqlSessionFactoryBuilder {

    public SqlSessionFactory build(InputStream inputStream){

        Configuration configuration = new Configuration();

        // 创建 SAX 对象
        SAXReader reader = new SAXReader();
        Document document = null;

        try {
            // 解析配置文件文件流
            document = reader.read(inputStream);
            Element rootElement = document.getRootElement();

            // 获取 jdbc 配置文件并解析
            Element properties = rootElement.element("properties");
            String jdbcPropertiesPath = properties.attributeValue("resource");
            loadProperties(configuration, jdbcPropertiesPath);

            // 读取 mapper
            Element mappers = rootElement.element("mappers");
            List<Element> mapperList = mappers.elements("mapper");

            for (Element mapper : mapperList) {
                String mapperPath = mapper.attributeValue("resource");
                loadMapperStatement(configuration, mapperPath);
            }

        } catch (DocumentException e) {
            e.printStackTrace();
        }


        return new DefaultSqlSessionFactory(configuration);

    }

    /**
     * 读取 mapper 并添加到 Configuration 中
     * @param configuration
     * @param mapperPath
     */
    private void loadMapperStatement(Configuration configuration, String mapperPath) {

        MapperStatement mapperStatement = null;

        SAXReader reader = new SAXReader();
        try {
            InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(mapperPath);
            Document document = reader.read(inputStream);
            Element rootElement = document.getRootElement();
            // 获取命名空间
            String namespace = rootElement.attributeValue("namespace");
            // 遍历所有的查询，并添加到 configuration 中 mapper 里
            List<Element> elements = rootElement.elements();
            for (Element element : elements) {
                mapperStatement = new MapperStatement();
                mapperStatement.setId(namespace + "." + element.attributeValue("id"));
                mapperStatement.setSql(element.getText());
                mapperStatement.setQueryType(element.getName());
                mapperStatement.setResultType(element.attributeValue("resultType"));
                configuration.addMapperStatement(mapperStatement);
            }
        } catch (DocumentException e) {
            e.printStackTrace();
        }
    }

    /**
     * 解析 jdbc.properties 并添加到 Configuration 中
     * @param configuration
     * @param jdbcPropertiesPath
     */
    private void loadProperties(Configuration configuration, String jdbcPropertiesPath) {
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream(jdbcPropertiesPath);
        Properties properties = new Properties();
        try {
            properties.load(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        JdbcProperties jdbcProperties = new JdbcProperties();
        jdbcProperties.setDriver(properties.getProperty("driver"));
        jdbcProperties.setUrl(properties.getProperty("url"));
        jdbcProperties.setUsername(properties.getProperty("username"));
        jdbcProperties.setPassword(properties.getProperty("password"));

        configuration.setJdbcProperties(jdbcProperties);
    }

}
