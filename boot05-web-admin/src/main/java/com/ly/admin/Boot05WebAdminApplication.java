package com.ly.admin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;


@MapperScan(basePackages = "com.ly.admin.mapperInterface")
@SpringBootApplication
public class Boot05WebAdminApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Boot05WebAdminApplication.class, args);
        ConfigurableEnvironment environment = run.getEnvironment();
        System.out.println(environment);
    }

}
