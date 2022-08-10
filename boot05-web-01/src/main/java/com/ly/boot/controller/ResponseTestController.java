package com.ly.boot.controller;

import com.ly.boot.pojo.Person;
import com.ly.boot.pojo.Pet;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

/**
 * FileName:ResponseTestController.class
 * Author:ly
 * Date:2022/8/10
 * Description: 测试响应及其原理
 */
@Controller
public class ResponseTestController {

    @ResponseBody
    @GetMapping("/test/person")
    public Person getPerson() {
        return new Person("zhangSan",28,new Date(),new Pet("liSi","3"));
    }

}
