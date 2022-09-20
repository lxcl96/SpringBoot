package com.ly.boot.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

/**
 * FileName:Worker.class
 * Author:ly
 * Date:2022/9/20 0020
 * Description:
 */
@Data
@Component
@Profile("test")
@ConfigurationProperties(prefix = "person")
public class Worker implements Person{
    private String name;
    private int age;
}
