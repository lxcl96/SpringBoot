package com.ly.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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
    public Map<String, Object> getRequestAttribute(@RequestAttribute("k1") String value,
                                                  HttpServletRequest request) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("k2",request.getAttribute("k2"));
        map.put("k1", value);
        return map;
    }
}
