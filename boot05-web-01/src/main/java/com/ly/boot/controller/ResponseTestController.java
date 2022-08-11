package com.ly.boot.controller;

import com.ly.boot.pojo.Person;
import com.ly.boot.pojo.Pet;
import org.springframework.core.io.FileSystemResource;
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

    @ResponseBody//此注解找到对应的返回值处理器RequestResponseBodyMethodProcessor -->然后调用对应的消息转换器messageConverter
    @GetMapping("/file")
    public FileSystemResource getFile() {
        //自己尝试下一个返回Resource类型的，看看是谁在处理并补充完整
        return new FileSystemResource("D:\\company\\auto_service\\iauto-4.5.2.all.42992.20200511\\taskmanagement\\config\\ice.conf");
    }

}
