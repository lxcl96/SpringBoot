package com.ly.boot.controller;

import com.ly.boot.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FileName:HelloController.class
 * Author:ly
 * Date:2022/7/29
 * Description:
 */
@RestController
public class HelloController {
    @Autowired
    private Person person;
    @RequestMapping("/")
    public Person hello() {
        return person;
    }
}
