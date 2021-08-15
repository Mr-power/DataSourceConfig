package com.power.sqlrollback.config.many.two;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

//@Configuration
//设置该数据源的下的mapper位置
@MapperScan(basePackages = "com.power.sqlrollback.mapper.two",sqlSessionFactoryRef = "twoSourceSqlSessionFactory")
public class TwoSourceConfig {
    @Autowired
    @Qualifier("twoSourceMapper")
    private DataSource twoSource;

    /**
     * 多数据源一起回滚注释掉
     * @return
     */
    @Bean(name = "twoTransactionManager")

    public DataSourceTransactionManager twoTransactionManager() {

          return new DataSourceTransactionManager(twoSource);

        }
    @Bean(name = "twoSourceSqlSessionFactory")
    public SqlSessionFactory twoSourceSqlSessionFactory(@Qualifier("twoSourceMapper") DataSource twoSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(twoSource);
    sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
            .getResources("classpath:mapper/two/*.xml"));
        return sessionFactory.getObject();
    }
}
