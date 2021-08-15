package com.power.sqlrollback.config.many.one;

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
@MapperScan(basePackages = "com.power.sqlrollback.mapper.one",sqlSessionFactoryRef="oneSourceSqlSessionFactory")
public class OneSourceConfig {
    @Autowired
    @Qualifier("oneSourceMapper")
    private DataSource oneSource;

    /**
     * 多数据源要一起回滚的话，注释下面的事务器
     * @return
     */
   @Bean(name = "oneTransactionManager")
   public DataSourceTransactionManager oneTransactionManager() {
       return new DataSourceTransactionManager(oneSource);
   }

    @Bean(name = "oneSourceSqlSessionFactory")
    public SqlSessionFactory oneSourceSqlSessionFactory(@Qualifier("oneSourceMapper") DataSource oneSource)
            throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(oneSource);
      sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                   .getResources("classpath:mapper/one/*.xml"));
        return sessionFactory.getObject();
    }





}
