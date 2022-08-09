package com.ly.boot.controller;

import com.ly.boot.pojo.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * FileName:RequestController.class
 * Author:ly
 * Date:2022/8/4
 * Description:
 */
@Controller
public class RequestController {

    @RequestMapping("/requestAttribute")
    public String goToPage(HttpServletRequest request){
        request.setAttribute("k1","you are a pig");
        request.setAttribute("k2", UUID.randomUUID().toString());
        return "forward:/success";
    }

    /**
     * 获取请求域的两个方法
     *  1、使用@RequestAttribute注解，必须写上name属性
     *  2、使用原生的request请求获取
     * @param value request域k1对应的属性值
     * @param request request请求
     * @return map集合
     */
    @ResponseBody
    @RequestMapping("/success")
    public Map<String, Object> getRequestAttribute(@RequestAttribute(value = "k1",required = false) String value,
                                                  HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();
        Object hello = request.getAttribute("hello");
        Object world = request.getAttribute("world");
        Object message = request.getAttribute("message");


        map.put("k2",request.getAttribute("k2"));
        map.put("k1", value);
        map.put("hello",hello);
        map.put("world",world);
        map.put("message",message);
        return map;
    }


    /**
     * 测试复杂参数，及model和map默认会给request域存放数据 等价于request.setAttribute
     * @param map map集合
     * @param model model
     * @param request request请求
     * @param response  response响应
     * @return 返回值
     */
    @GetMapping("/params")
    public String testParam(Map<String,Object> map,
                            Model model,
                            HttpServletRequest request,
                            HttpServletResponse response) {
        //先判断有没有数据
        System.out.println(map);
        System.out.println(model.asMap());

        //存放值
        map.put("hello","world666");
        model.addAttribute("world","hello666");
        request.setAttribute("message","helloWorld");

        response.addCookie(new Cookie("c1", "v1"));
        return "forward:/success";
    }

    /**
     * 请求数据绑定JavaBean
     * @param person 要被封装的bean
     * @return person对象
     */
    @ResponseBody
    @PostMapping("/saveuser")
    public Person testPojoParam(Person person) {
        System.out.println(person);

        return person;
    }
}
