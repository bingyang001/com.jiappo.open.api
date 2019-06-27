package com.jiappo.open.api.dao.init;


import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageInterceptor;

import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.support.TransactionTemplate;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

/**
 * open api data source mysql
 * Created by liguo on 2017/8/11.
 * @author liguo
 */
@Configuration
@MapperScan(value = {"com.jiappo.open.api.dao.mapper"}
        , sqlSessionFactoryRef = "openApiSessionFactory")
@EnableTransactionManagement
public class OpenApiDataSourceConfig {
    @Bean(name = "openApiSessionFactory")
    public SqlSessionFactory userSessionFactory() throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        Resource[] resources = resolver.getResources("classpath*:mapper/hjclass/*.xml");
        Resource[] resources2 = resolver.getResources("classpath*:mapper/mysqlhjclassuserdefined/*.xml");
        List<Resource> list = new ArrayList<>();
        Arrays.stream(resources).forEach(c -> list.add(c));
        Arrays.stream(resources2).forEach(c -> list.add(c));
        sqlSessionFactoryBean.setMapperLocations(list.toArray(new Resource[0]));
        sqlSessionFactoryBean.setConfigLocation(resolver.getResource("classpath:spring/mybatis-settings.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    @Primary
    @Bean(name = "openApiDataSource", initMethod = "init", destroyMethod = "close")
    @ConfigurationProperties(prefix = "jdbc.hj_class_courseware")
    public DataSource dataSource() {
        DruidDataSource druidSource = new DruidDataSource();
        //2018/06/23 设置默认的属性
        druidSource.setValidationQuery("select 1");
        druidSource.setTestWhileIdle(true);
        //超过时间限制是否回收
        druidSource.setRemoveAbandoned(true);
        //超时时间；单位为秒。180秒=3分钟
        druidSource.setRemoveAbandonedTimeout(180);
        //关闭abanded连接时输出错误日志
        druidSource.setLogAbandoned(true);

        return druidSource;
    }

    @Bean(name = "openApiJdbcTemplate")
    public JdbcTemplate jdbcTemplate(@Qualifier("openApiDataSource") DataSource msDataSource) {
        return new JdbcTemplate(msDataSource);
    }

    @Bean(name = "openApiTransactionTemplate")
    public TransactionTemplate transactionTemplate() {
        TransactionTemplate transactionTemplate = new TransactionTemplate();
        transactionTemplate.setTransactionManager(transactionManager());
        transactionTemplate.setTimeout(2000);
        return transactionTemplate;
    }

    @Bean(name = "openApiTransactionManager")
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }
}
