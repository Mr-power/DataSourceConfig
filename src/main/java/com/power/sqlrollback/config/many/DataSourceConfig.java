package com.power.sqlrollback.config.many;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

//@Configuration
public class DataSourceConfig {
  @Bean(name="oneSource")
  @Qualifier("oneSourceMapper")
  //@ConfigurationProperties(prefix = "spring.datasource.one")
  public DataSource oneSource(){
      DruidDataSource dataSource=new DruidDataSource();
      return dataSource;
  }
  @Bean(name="twoSource")
  @Qualifier("twoSourceMapper")
  //@ConfigurationProperties(prefix = "spring.datasource.two")
  public DataSource twoSource(){
      DruidDataSource dataSource=new DruidDataSource();
      return dataSource;
  }
}
