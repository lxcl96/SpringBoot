package com.ly.admin.listener;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * FileName:MyListener.class
 * Author:ly
 * Date:2022/9/2 0002
 * Description:
 */
@Slf4j
//@WebListener
public class MyListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info(this.getClass() + "监听到 ServletContext 初始化完成！");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info(this.getClass() + "监听到 ServletContext 销毁！");
    }
}
