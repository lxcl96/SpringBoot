package com.ly.boot.controller;

import com.ly.hello.service.HelloService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FileName:HelloServiceTest.class
 * Author:ly
 * Date:2022/9/21 0021
 * Description:
 */
@RestController
public class HelloServiceTest {
    @Autowired
    private HelloService helloService;


    @GetMapping("/")
    public String hello() {
        return helloService.sayHello("ly");
    }
}
