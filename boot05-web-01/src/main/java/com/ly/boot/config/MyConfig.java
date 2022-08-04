package com.ly.boot.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

/**
 * FileName:MyConfig.class
 * Author:ly
 * Date:2022/8/2
 * Description:
 */
@Configuration(proxyBeanMethods = false)
public class MyConfig implements WebMvcConfigurer {

//    @Bean
//    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
//        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
//        hiddenHttpMethodFilter.setMethodParam("_prefix");
//        return hiddenHttpMethodFilter;
//    }

    @Override
    //SpringMVC自动配置概览中修改组件属性的方法1
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        //去掉 默认去掉请求链接中分号 ; 的规则,即开启矩阵变量功能
        urlPathHelper.setRemoveSemicolonContent(false);

        configurer.setUrlPathHelper(urlPathHelper);
    }

    //@Bean//直接给ioc容器中添加 WebMvcConfigurer组件
    //SpringMVC自动配置概览中修改组件属性的方法1
    public WebMvcConfigurer webMvcConfigurer() {
        //直接返回接口的匿名内部类
       return new WebMvcConfigurer() {
           @Override
           public void configurePathMatch(PathMatchConfigurer configurer) {
               UrlPathHelper urlPathHelper = new UrlPathHelper();
               //去掉 默认去掉请求链接中分号 ; 的规则,即开启矩阵变量功能
               urlPathHelper.setRemoveSemicolonContent(false);

               configurer.setUrlPathHelper(urlPathHelper);
           }
       };

    }
}
