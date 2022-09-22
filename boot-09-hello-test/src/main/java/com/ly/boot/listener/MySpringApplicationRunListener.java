package com.ly.boot.listener;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringApplicationRunListener;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;


/**
 * FileName:MySpringApplicationRunListener.class
 * Author:ly
 * Date:2022/9/22 0022
 * Description:
 */

public class MySpringApplicationRunListener implements SpringApplicationRunListener {

    private SpringApplication springApplication;
    //需要一个有参构造器，且必须是这两个参数
    public MySpringApplicationRunListener(SpringApplication application, String[] args) {
        this.springApplication=application;
        System.out.println("MySpringApplicationRunListener  ...instance...");
    }

    @Override
    public void starting() {
        SpringApplicationRunListener.super.starting();
        System.out.println("MySpringApplicationRunListener  ... starting ..." );
    }

    @Override
    public void environmentPrepared(ConfigurableEnvironment environment) {
        SpringApplicationRunListener.super.environmentPrepared(environment);
        System.out.println("MySpringApplicationRunListener  ... environmentPrepared ..." );
    }

    @Override
    public void contextPrepared(ConfigurableApplicationContext context) {
        SpringApplicationRunListener.super.contextPrepared(context);
        System.out.println("MySpringApplicationRunListener  ... contextPrepared ..." );
    }

    @Override
    public void contextLoaded(ConfigurableApplicationContext context) {
        SpringApplicationRunListener.super.contextLoaded(context);
        System.out.println("MySpringApplicationRunListener  ... contextLoaded ..." );
    }

    @Override
    public void started(ConfigurableApplicationContext context) {
        SpringApplicationRunListener.super.started(context);
        System.out.println("MySpringApplicationRunListener  ... started ..." );
    }

    @Override
    public void running(ConfigurableApplicationContext context) {
        SpringApplicationRunListener.super.running(context);
        System.out.println("MySpringApplicationRunListener  ... running ..." );
    }

    @Override
    public void failed(ConfigurableApplicationContext context, Throwable exception) {
        SpringApplicationRunListener.super.failed(context, exception);
        System.out.println("MySpringApplicationRunListener  ... failed ..." );
    }
}
