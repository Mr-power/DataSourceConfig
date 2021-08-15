package com.power.sqlrollback.config.rollback.two;

import com.mysql.cj.jdbc.MysqlXADataSource;
import com.power.sqlrollback.config.rollback.one.OneConfig;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.jta.atomikos.AtomikosDataSourceBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;
import java.sql.SQLException;

@Configuration
//设置该数据源的下的mapper位置
@MapperScan(basePackages = "com.power.sqlrollback.mapper.two",sqlSessionFactoryRef = "twoSourceSqlSessionFactory")
public class TwoSourceConfig {
    /**
     * 创建DataSource
     * @return
     */
    @Bean("twoDataSource")
    //@ConfigurationProperties(prefix = "spring.datasource.one")
    public DataSource memberDataSource(){
        return DataSourceBuilder.create().build();
    }

    /**
     * 创建sqlSessionFactory
     * @param dataSource
     * @return
     * @throws Exception
     */
    @Bean(name = "twoSourceSqlSessionFactory")
    public SqlSessionFactory memberSqlSessionFactory(@Qualifier("twoDataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources("classpath:mapper/two/*.xml"));
        return sessionFactory.getObject();
    }

    //    /**
    //     * 创建事务管理器
    //     * @param dataSource
    //     * @return
    //     */
    //    @Bean(name = "memberTransactionManager")
    //    public DataSourceTransactionManager memberTransactionManager(@Qualifier("memberDataSource") DataSource dataSource) {
    //        return new DataSourceTransactionManager(dataSource);
    //    }

    /**
     * 创建sqlSession模板
     * @param sqlSessionFactory
     * @return
     */
    @Bean(name = "twoSourceSqlSessionTemplate")
    public SqlSessionTemplate memberSqlSessionTemplate(@Qualifier("twoSourceSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }
    @Bean("twoDataSource")
    public DataSource memberDataSource(TwoConfig memberConfig) throws SQLException {
        System.out.println(memberConfig);
        MysqlXADataSource mysqlXaDataSource = new MysqlXADataSource();
        mysqlXaDataSource.setUrl(memberConfig.getUrl());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        mysqlXaDataSource.setPassword(memberConfig.getPassword());
        mysqlXaDataSource.setUser(memberConfig.getUsername());
        mysqlXaDataSource.setPinGlobalTxToPhysicalConnection(true);
        //注册到全局事务
        AtomikosDataSourceBean xaDataSource = new AtomikosDataSourceBean();
        xaDataSource.setXaDataSource(mysqlXaDataSource);
        xaDataSource.setUniqueResourceName(memberConfig.getUniqueResourceName());
        xaDataSource.setMinPoolSize(memberConfig.getMinPoolSize());
        xaDataSource.setMaxPoolSize(memberConfig.getMaxPoolSize());
        xaDataSource.setMaxLifetime(memberConfig.getMaxLifetime());
        xaDataSource.setBorrowConnectionTimeout(memberConfig.getBorrowConnectionTimeout());
        xaDataSource.setLoginTimeout(memberConfig.getLoginTimeout());
        xaDataSource.setMaintenanceInterval(memberConfig.getMaintenanceInterval());
        xaDataSource.setMaxIdleTime(memberConfig.getMaxIdleTime());
        xaDataSource.setTestQuery(memberConfig.getTestQuery());
        return xaDataSource;
    }
}
