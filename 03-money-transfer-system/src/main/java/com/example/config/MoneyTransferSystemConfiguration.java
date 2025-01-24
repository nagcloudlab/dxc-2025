package com.example.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.transaction.annotation.EnableTransactionManagement;


@Configuration
@Import({
        DataSourceConfiguration.class,
        JdbcConfiguration.class
})
@ComponentScan(basePackages = {
        "com.example.service",
        "com.example.repository"
})
@EnableTransactionManagement
public class MoneyTransferSystemConfiguration {

}
