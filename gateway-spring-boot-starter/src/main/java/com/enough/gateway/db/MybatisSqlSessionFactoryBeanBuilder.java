package com.enough.gateway.db;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.lang3.StringUtils;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

/**
 * @since 统一使用alibaba的druid
 */
@Deprecated
public class MybatisSqlSessionFactoryBeanBuilder {

    public static SqlSessionFactoryBean build(DatasourceConnInfo connInfo) {
        SqlSessionFactoryBean sqlSessionFactoryBean = null;
        String locationType = StringUtils.EMPTY;
        String driverClassName = StringUtils.EMPTY;
        String jdbcURL = StringUtils.EMPTY;
        System.out.println(connInfo.getServer() + connInfo.getDbName() + connInfo.getDbType());
        switch (connInfo.getDbType()) {
        case POSTGRESQL:
            driverClassName = "org.postgresql.Driver";
            locationType = "postgresql";
            jdbcURL = "jdbc:postgresql://" + connInfo.getServer() + ":" + connInfo.getPort() + "/" + connInfo.getDbName() + "";
            break;
        case MYSQL:
            driverClassName = "com.mysql.jdbc.Driver";
            locationType = "mysql";
            jdbcURL = "jdbc:" + locationType + "://" + connInfo.getServer() + ":" + connInfo.getPort() + "/" + connInfo.getDbName()
                    + "?useUnicode=true&characterEncoding=UTF-8&serverTimezone=Asia/Shanghai";
            break;
        case ORACLE:
            jdbcURL = "jdbc:oracle:thin:@" + connInfo.getServer() + ":" + connInfo.getPort() + ":" + connInfo.getDbName();
            driverClassName = "oracle.jdbc.OracleDriver";
            locationType = "oracle";
            break;

        case SQLSERVER:
            jdbcURL = "jdbc:sqlserver://" + connInfo.getServer() + ":" + connInfo.getPort() + ";DatabaseName=" + connInfo.getDbName();
            driverClassName = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
            locationType = "sqlserver";
            break;
        default:
            break;
        }
        try {
            //            DruidDataSource dataSource = new DruidDataSource();
            //            dataSource.setUrl(jdbcURL);
            //            dataSource.setUsername(DesUtil.decrypt(connInfo.getUsername()));
            //            dataSource.setPassword(DesUtil.decrypt(connInfo.getPassword()));
            //            dataSource.setUsername(connInfo.getUsername());
            //            dataSource.setPassword(connInfo.getPassword());
            //            dataSource.setDriverClassName(driverClassName);
            //            dataSource.setMinIdle(connInfo.getMaxIdleTime());
            //            dataSource.setMaxWait(connInfo.getMaxWait());
            //            dataSource.setInitialSize(connInfo.getInitialPoolSize());
            //            dataSource.setTestWhileIdle(true);

            ComboPooledDataSource dataSource = new ComboPooledDataSource();
            dataSource.setJdbcUrl(jdbcURL);
            //          dataSource.setUsername(DesUtil.decrypt(connInfo.getUsername()));
            //          dataSource.setPassword(DesUtil.decrypt(connInfo.getPassword()));
            dataSource.setUser(connInfo.getUsername());
            dataSource.setPassword(connInfo.getPassword());
            dataSource.setDriverClass(driverClassName);
            dataSource.setMaxIdleTime(connInfo.getMaxIdleTime());
            dataSource.setMinPoolSize(connInfo.getMinPoolSize());
            dataSource.setInitialPoolSize(connInfo.getInitialPoolSize());
            dataSource.setAutoCommitOnClose(true);
            dataSource.setCheckoutTimeout(connInfo.getCheckoutTimeout());

            // 实例SessionFactory
            sqlSessionFactoryBean = new SqlSessionFactoryBean();
            // 配置数据源
            sqlSessionFactoryBean.setDataSource(dataSource);
            // 加载MyBatis配置文件
            PathMatchingResourcePatternResolver resourcePatternResolver = new PathMatchingResourcePatternResolver();
            // 能加载多个，所以可以配置通配符(如：classpath*:mapper/**/*.xml)
            sqlSessionFactoryBean.setMapperLocations(
                    resourcePatternResolver.getResources(connInfo.getMapperLocations().replace(DatasourceConnInfo.TYPE_LOCATION_HOLDER, locationType)));
        } catch (Exception e) {
            System.out.println("创建SqlSession连接工厂错误：{}/n" + e);
        }
        return sqlSessionFactoryBean;
    }
}
