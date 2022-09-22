package com.ly.boot.listener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;



/**
 * FileName:MyApplicationListener.class
 * Author:ly
 * Date:2022/9/22 0022
 * Description:
 */

public class MyApplicationListener implements ApplicationListener<ApplicationEvent> {
    @Override
    public void onApplicationEvent(ApplicationEvent event) {
        System.out.println("MyApplicationListener ...onApplicationEvent..");
        System.out.println(event.getClass());
    }
}
