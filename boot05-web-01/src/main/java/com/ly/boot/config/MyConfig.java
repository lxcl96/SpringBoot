package com.ly.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;

/**
 * FileName:MyConfig.class
 * Author:ly
 * Date:2022/8/2
 * Description:
 */
//@Configuration(proxyBeanMethods = false)
public class MyConfig {

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        hiddenHttpMethodFilter.setMethodParam("_prefix");
        return hiddenHttpMethodFilter;
    }
}
