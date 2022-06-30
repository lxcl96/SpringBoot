package com.ly.boot.config;

import com.ly.boot.bean.Car;
import com.ly.boot.bean.User;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @FileName:AnnotationConfiguration.class
 * @Author:ly
 * @Date:2022/6/30
 * @Description:
 */
@Configuration
@ImportResource({"classpath:spring/SpringConfig.xml"})
/**
 * 两个作用：
 *  1、开启Car配置绑定功能
 *  2、把这个Car组件自动注册到容器中
 */
@EnableConfigurationProperties(Car.class)//需要制定要开启自动注入properties的类
public class AnnotationConfiguration {

    //和这个效果一样
//    @Bean("lisi")
//    public User getUser() {
//        return new User("李四",33);
//    }
}
