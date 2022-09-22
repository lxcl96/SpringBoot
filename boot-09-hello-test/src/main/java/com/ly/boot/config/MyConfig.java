package com.ly.boot.config;

import com.ly.hello.service.HelloService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FileName:MyConfig.class
 * Author:ly
 * Date:2022/9/21 0021
 * Description:
 */
@Configuration
public class MyConfig {
    //@Bean
    public HelloService helloService() {

        return new HelloService();
    }
}
