package com.enough.gateway.config;

import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.boot.autoconfigure.MybatisAutoConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.context.annotation.Primary;

import javax.sql.DataSource;

/**
 * @since 统一使用alibaba的druid
 */
@Deprecated
//@Configuration
//@MapperScan(basePackages = "com.zxt.**.dao")
//@Import({MybatisAutoConfiguration.class})
public class SqlSessionConfig {

    //@Primary
    //@Bean(name = "mysql")
    //@Qualifier("mysql")
    //@ConfigurationProperties(prefix = "spring.datasource.mysql")
    //public DataSource mysqlDataSource() {
    //    return DataSourceBuilder.create().build();
    //}
    //
    //    @Primary
    //    @ConfigurationProperties(prefix = "spring.datasource")
    //    public DataSource DataSource() {
    //        return DataSourceBuilder.create().build();
    //    }

}
