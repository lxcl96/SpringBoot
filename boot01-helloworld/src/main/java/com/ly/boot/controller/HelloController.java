package com.ly.boot.controller;

import com.ly.boot.bean.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @FileName:HelloController.class
 * @Author:ly
 * @Date:2022/6/27
 * @Description:
 */
@RestController //@Response + @Controller
public class HelloController {
    @Autowired
    private Car myCar;

    @RequestMapping("/car")
    /**
     * public Car getCar(@Autowired Car myCar) {
     * @Autowired在控制器参数中失效，因为返回bean为dispatcherServlet新建的，然后把请求中的对应值赋值
     * 因为没有这些参数，所有输出一值为null，但是car!=null
     */
    public Car getCar() {
        System.out.println(myCar + "   hashcode=" + myCar.hashCode());
        return myCar;
    }
    @RequestMapping("/")
    public String helloWorld() {
        return "Hello Spring Boot!";
    }
}
