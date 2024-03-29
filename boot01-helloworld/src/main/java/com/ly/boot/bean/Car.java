package com.ly.boot.bean;

import lombok.*;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @FileName:Car.class
 * @Author:ly
 * @Date:2022/6/30
 * @Description:
 */

/**
 * 只有容器中的组件才能使用这些注解方法所以必须加上@Component
 * 如果想要@ConfigurationProperties在配置文件中可以跳转过来到对应的setxxx()方法上
 *    1、pom添加依赖  spring-boot-configuration-processor
         <dependency>
             <groupId>org.springframework.boot</groupId>
             <artifactId>spring-boot-configuration-processor</artifactId>
             <optional>true</optional>
         </dependency>
 *    2、必须加上@Component之类的注解，表示放在ioc容器中
 */
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
@EqualsAndHashCode
//@Component
@ConfigurationProperties(prefix = "mycar")//前缀就是properties文件里的前缀
public class Car {
    private String brand;
    private Integer price;

}
