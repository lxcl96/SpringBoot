package com.ly.admin.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.alibaba.druid.support.http.WebStatFilter;
import com.alibaba.druid.support.spring.stat.DruidStatInterceptor;
import com.ly.admin.aop.DruidAndSpringBootAdvisor;
import org.aopalliance.aop.Advice;
//import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJPointcutAdvisor;
import org.springframework.aop.support.JdkRegexpMethodPointcut;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.Scope;
import org.springframework.core.Ordered;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;

/**
 * FileName:MyDataSource.class
 * Author:ly
 * Date:2022/9/7 0007
 * Description:
 */
@Deprecated
//@Configuration
//@EnableAspectJAutoProxy(proxyTargetClass = true)
public class MyDataSource {

    /**
     * 注解@ConfigurationProperties 的意思是，从配置文件中读取druid开头的所有属性，然后借助aop原理
     *  当函数调用完返回一个Bean组件后，借助后置处理器再将值赋值给返回bean的属性
     *  总之：被调用的方法返回之后，才会调用该注解给返回值赋值
     *
     *  DruidDataSource中System.getProperties()是读取系统参数而不是ioc容器的environment
     *  所以如果要是使用这种方法，添加jvm参数即可：
     *      -Ddruid.url=jdbc:mysql:///ssm_crdu -Ddruid.username=root -Ddruid.password=123456 -Ddruid.driverClassName=com.mysql.jdbc.Driver
     * @return datasource
     */
    @Bean
    @ConfigurationProperties(prefix = "druid")
    public DruidDataSource dataSource() throws SQLException {
        /**
         * 配置数据库属性有很多方法：
         *  1、@ConfigurationProperties + 配置文件
         *  2、添加jvm参数方法
         *  3、手动set方法
         *  4、DruidDataSourceFactory.createDataSource(配置文件)
         */
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setFilters("stat,wall");
//        dataSource.setUrl();
//        dataSource.setDriverClassName();
//        dataSource.setUsername();
//        dataSource.setPassword();
        return dataSource;
    }


    /**
     * 打开Druid监控统计功能和内置监控页，并设置拦截路径和 用户名与密码
     * 监控页面为：
     *  http://ip:port/druid/index.html
     *  http://ip:port/项目名/druid/index.html
     * @return Druid servlet
     */
    @Bean
    public ServletRegistrationBean<StatViewServlet> DruidStatView() {
        HashMap<String, String> map = new HashMap<>();
        map.put("loginUsername","ly");
        map.put("loginPassword","ly");
        ServletRegistrationBean<StatViewServlet> servletRegistrationBean = new ServletRegistrationBean<>(new StatViewServlet(), "/druid/*");
        servletRegistrationBean.setInitParameters(map);

        return servletRegistrationBean;
    }

    @Bean
    public FilterRegistrationBean<WebStatFilter> DruidWebStatFilter() {
        HashMap<String, String> map = new HashMap<>();
        map.put("exclusions","*.js,*.gif,*.jpg,*.png,*.css,*.ico,/druid/*");

        FilterRegistrationBean<WebStatFilter> filterRegistrationBean = new FilterRegistrationBean<WebStatFilter>();
        filterRegistrationBean.setFilter(new WebStatFilter());
        filterRegistrationBean.addUrlPatterns("/*");
        filterRegistrationBean.setInitParameters(map);

        return filterRegistrationBean;
    }


    @Bean
    public DruidStatInterceptor druidStatInterceptor() {
        return new DruidStatInterceptor();
    }

    @Bean
    @Scope("prototype")
    public JdkRegexpMethodPointcut druidStatPointcut() {

        JdkRegexpMethodPointcut regexpMethodPointcut = new JdkRegexpMethodPointcut();
        regexpMethodPointcut.setPatterns("com.ly.admin.*");

        return regexpMethodPointcut;
    }

    @Bean
    public DruidAndSpringBootAdvisor druidAndSpringBootAdvisor(DruidStatInterceptor druidStatInterceptor,
                                                               JdkRegexpMethodPointcut jdkRegexpMethodPointcut){
        DruidAndSpringBootAdvisor bootAdvisor = new DruidAndSpringBootAdvisor();
        bootAdvisor.setAdvice(druidStatInterceptor);
        bootAdvisor.setPointcut(jdkRegexpMethodPointcut);
        bootAdvisor.setOrder(Ordered.HIGHEST_PRECEDENCE);
        return  bootAdvisor;
    }

}
