package com.ly.boot.controller;

import com.ly.boot.bean.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * FileName:HelloCOntroller.class
 * Author:ly
 * Date:2022/9/20 0020
 * Description:
 */
@RestController
public class HelloController {
    @Autowired
    private Person person;

    @GetMapping("/")
    public Person hello() {
        return person;
    }
}
