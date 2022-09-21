package com.ly.hello.service;

import com.ly.hello.bean.HelloProperties;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * FileName:HelloService.class
 * Author:ly
 * Date:2022/9/21 0021
 * Description: 有一个公共方法，和别人打招呼
 *
 *  默认不要放在容器中，因为我们要配置
 */


public class HelloService {
    @Autowired
    private HelloProperties helloProperties;


    public String sayHello(String userName) {
        return helloProperties.getPrefix() + ": " + userName + ">" + helloProperties.getSuffix();
    }

}
