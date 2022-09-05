package com.ly.admin.config;

import com.ly.admin.interceptor.UserInterceptor;
import com.ly.admin.servlet.LTServlet;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


/**
 * FileName:MyConfig.class
 * Author:ly
 * Date:2022/8/23 0023x
 * Description: 我的配置类
 */
//@ServletComponentScan(value = {"com.ly.admin.servlet","com.ly.admin.filter","com.ly.admin.listener"})
@Configuration(proxyBeanMethods = false)
public class MyConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/","/login")
                .excludePathPatterns("/css/**","/js/**","/fonts/**","/images/**");
    }


//    @Bean //ConfigurableServletWebServerFactory是ServletWebServerFactory的子接口
//    public ConfigurableServletWebServerFactory servletWebServerFactory(){
//        TomcatServletWebServerFactory webServerFactory = new TomcatServletWebServerFactory();
//        webServerFactory.setPort(8888);
//        webServerFactory.setBackgroundProcessorDelay();
//        webServerFactory.setProtocol();
//        webServerFactory.setSsl();
//        webServerFactory.setSession();
//
//        return webServerFactory;
//    }
//
}
