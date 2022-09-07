package com.ly.admin.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FileName:MyDataSource.class
 * Author:ly
 * Date:2022/9/7 0007
 * Description:
 */
@Configuration
public class MyDataSource {

    @Bean
    @ConfigurationProperties(prefix = "druid")
    public DruidDataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
//        dataSource.setUrl();
//        dataSource.setDriverClassName();
//        dataSource.setUsername();
//        dataSource.setPassword();
        System.out.println(dataSource.getUsername());
        System.out.println(dataSource.getDriverClassName());
        System.out.println(dataSource.getUrl());
        return dataSource;
    }
}
