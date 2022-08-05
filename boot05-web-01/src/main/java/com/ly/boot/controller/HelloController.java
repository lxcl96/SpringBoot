package com.ly.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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


//    @RequestMapping("/index")
//    public String index() {
//        return "index";
//    }

    @RequestMapping(value = "/user",method = RequestMethod.GET)
    public String getUser(){
        return "GET-张三";
    }

    @RequestMapping(value = "/user",method = RequestMethod.POST)
    public String saveUser(){
        return "POST-张三";
    }


    @RequestMapping(value = "/user",method = RequestMethod.PUT)
    public String putUser(){
        return "PUT-张三";
    }

    @RequestMapping(value = "/user",method = RequestMethod.DELETE)
    public String deleteUser(){
        return "DELETE-张三";
    }

}
