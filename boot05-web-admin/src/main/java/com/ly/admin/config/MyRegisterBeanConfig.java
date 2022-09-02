package com.ly.admin.config;

import com.ly.admin.filter.MyFilter;
import com.ly.admin.listener.MyListener;
import com.ly.admin.servlet.LTServlet;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.RegistrationBean;
import org.springframework.boot.web.servlet.ServletListenerRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FileName:MyRegisterBeanConfig.class
 * Author:ly
 * Date:2022/9/2 0002
 * Description:
 */
@Configuration //proxyBeanMethods=true 表达式代理@bean的创建方法，每次都从ioc容器中查找，保证单实例
public class MyRegisterBeanConfig {
    @Bean
    public ServletRegistrationBean<LTServlet> myServlet() {
        LTServlet ltServlet = new LTServlet();
        return new ServletRegistrationBean<LTServlet>(ltServlet,"/lao_tie","/lt");
    }

    @Bean
    public FilterRegistrationBean<MyFilter> myFilter() {
        MyFilter myFilter = new MyFilter();
        FilterRegistrationBean<MyFilter> filterRegistrationBean = new FilterRegistrationBean<>();
        filterRegistrationBean.setFilter(myFilter);
        //指定拦截某个servlet即，servlet处理啥请求，他就拦截什么请求
        //filterRegistrationBean.addServletRegistrationBeans(myServlet());
        filterRegistrationBean.addUrlPatterns("/css/*","/js/*","/images/*");
        return filterRegistrationBean;
    }

    @Bean
    public ServletListenerRegistrationBean<MyListener> myListener() {
        MyListener myListener = new MyListener();
        ServletListenerRegistrationBean<MyListener> listenerRegistrationBean = new ServletListenerRegistrationBean<>();
        listenerRegistrationBean.setListener(myListener);
        return listenerRegistrationBean;
    }
}
