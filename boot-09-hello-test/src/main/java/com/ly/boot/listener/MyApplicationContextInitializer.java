package com.ly.boot.listener;

import org.springframework.context.ApplicationContextInitializer;
import org.springframework.context.ConfigurableApplicationContext;


/**
 * FileName:MyApplicationContextInitializer.class
 * Author:ly
 * Date:2022/9/22 0022
 * Description:
 */

public class MyApplicationContextInitializer implements ApplicationContextInitializer<ConfigurableApplicationContext> {
    @Override
    public void initialize(ConfigurableApplicationContext applicationContext) {
        System.out.println("MyApplicationContextInitializer   ...initialize...");
    }
}
