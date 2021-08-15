package com.power.sqlrollback;

import com.power.sqlrollback.config.rollback.one.OneConfig;
import com.power.sqlrollback.config.rollback.two.TwoConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({OneConfig.class, TwoConfig.class})
public class SqlRollbackApplication {

    public static void main(String[] args) {
        SpringApplication.run(SqlRollbackApplication.class, args);
    }

}
