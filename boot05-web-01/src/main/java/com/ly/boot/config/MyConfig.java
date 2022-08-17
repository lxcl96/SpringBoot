package com.ly.boot.config;

import com.ly.boot.convert.MyConvert;
import com.ly.boot.convert.MyMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.accept.ParameterContentNegotiationStrategy;
import org.springframework.web.filter.HiddenHttpMethodFilter;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.util.UrlPathHelper;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

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

//    @Bean
//    public InternalResourceViewResolver internalResourceViewResolver() {
//        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
//        viewResolver.setSuffix(".jsp");
//        //放在类路径下
//        viewResolver.setPrefix("../BOOT-INF/classes/static/");
//        viewResolver.setOrder(0);
//        return viewResolver;
//    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(new MyConvert());
    }

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        //配置就把默认的那些messageConverter都覆盖掉了，所以我们不推荐使用
    }

    @Override
    //在原有的messageConverter基础上扩展，推荐
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
        converters.add(new MyMessageConverter());

    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        //开启浏览器参数解析返回媒体类型
        //configurer.favorParameter(true);
        //设置参数名【默认为format】
        //configurer.parameterName("name");
        configurer.mediaType("x-any", MediaType.parseMediaType("application/x-any"));
        /*
        ConcurrentHashMap<String, MediaType> mediaTypes = new ConcurrentHashMap<>();
        mediaTypes.put("xml",MediaType.APPLICATION_XML);
        mediaTypes.put("json",MediaType.APPLICATION_JSON);
        mediaTypes.put("x-any",MediaType.parseMediaType("x-any"));
        ParameterContentNegotiationStrategy parameterStrategy = new ParameterContentNegotiationStrategy(mediaTypes);
        configurer.strategies(Arrays.asList(parameterStrategy));
        */
    }
}
