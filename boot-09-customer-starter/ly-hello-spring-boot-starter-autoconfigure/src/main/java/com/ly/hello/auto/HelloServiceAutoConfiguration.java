package com.ly.hello.auto;

import com.ly.hello.bean.HelloProperties;
import com.ly.hello.service.HelloService;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;

/**
 * FileName:HelloServiceAutoConfiguration.class
 * Author:ly
 * Date:2022/9/21 0021
 * Description:
 */
@AutoConfiguration
@ConditionalOnMissingBean(HelloService.class)
@EnableConfigurationProperties(HelloProperties.class)//两个功能：1、自动绑定属性 2、放在ioc容器中
public class HelloServiceAutoConfiguration {


    @Bean
    public HelloService helloService() {
        return new HelloService();
    }
}
