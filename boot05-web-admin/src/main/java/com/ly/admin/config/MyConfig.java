package com.ly.admin.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.ly.admin.interceptor.RedisCountUrlInterceptor;
import com.ly.admin.interceptor.UserInterceptor;
import com.ly.admin.servlet.LTServlet;
import io.micrometer.core.instrument.Gauge;
import io.micrometer.core.instrument.binder.MeterBinder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcRegistrations;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.context.ServletWebServerApplicationContext;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.sql.DataSource;
import java.util.Queue;


/**
 * FileName:MyConfig.class
 * Author:ly
 * Date:2022/8/23 0023x
 * Description: 我的配置类
 */
//@ServletComponentScan(value = {"com.ly.admin.servlet","com.ly.admin.filter","com.ly.admin.listener"})
@Configuration(proxyBeanMethods = false)
public class MyConfig implements WebMvcConfigurer {

    @Autowired
    private RedisCountUrlInterceptor redisCountUrlInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new UserInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/","/login")
                .excludePathPatterns("/css/**","/js/**","/fonts/**","/images/**");

        registry.addInterceptor(redisCountUrlInterceptor)
                .addPathPatterns("/**")
                .order(Ordered.HIGHEST_PRECEDENCE)
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

    /**
     * 自定义mvc底层的 处理器适配器
     * @return WebMvcRegistrations
     */
    //@Bean
    public WebMvcRegistrations getWebMvcRegistrations() {
        return new WebMvcRegistrations() {
            @Override
            public RequestMappingHandlerAdapter getRequestMappingHandlerAdapter() {
                return WebMvcRegistrations.super.getRequestMappingHandlerAdapter();
            }
        };
    }

    /**
     * 启用@EnableWebMvc导致自动配置全都失效，所以需要自己配置资源处理器
     *
     * 意思为：当浏览器访问/aa下的所有请求时，会自动映射到类路径下 static目录下文件
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/aa/**").addResourceLocations("classpath:/static/");
    }



    @Bean
    //在metrics中添加一个queueSize的指标
    MeterBinder queueSize(Queue queue) {
        return (registry) -> Gauge.builder("queueSize", queue::size).register(registry);
    }

}
