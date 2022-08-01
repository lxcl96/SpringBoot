package com.ly.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FileName:HelloController.class
 * Author:ly
 * Date:2022/8/1
 * Description:
 */
@RestController
public class HelloController {


    /**
     * 提问：是返回静态资源1.png还是aaa字符串呢？
     * @return aaa字符串
     */
    @RequestMapping("/1.png")
    public String hello() {
        return "aaa";
    }
}
