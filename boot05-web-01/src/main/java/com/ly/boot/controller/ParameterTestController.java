package com.ly.boot.controller;

import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import java.util.HashMap;
import java.util.Map;

/**
 * FileName:ParameterTestController.class
 * Author:ly
 * Date:2022/8/3
 * Description:
 */

@RestController
public class ParameterTestController {

    /**
     * 注解@PathVariable的两种 获取restful风格参数值的用法：
     *      1、根据参数名获取
     *      2、封装到一个map集合中，类型必须为Map<String,String>
     * @param id 参数id
     * @param username  参数 username
     * @param pv 参数集合
     * @return 集合
     */
    @RequestMapping("/car/{id}/owner/{username}")
    public Map<String,Object> getCar(@PathVariable("id") Integer id,
                                     @PathVariable("username") String username,
                                     @PathVariable Map<String,String> pv) {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",id);
        map.put("username",username);
        map.put("map",pv);
        return map;
    }

    /**
     * 注解@RequestHeader获取请求头属性的3种用法
     *  1、根据请求头属性名获取
     *  2、封装到一个map集合中，类型必须为Map<String,String>
     *  3、封装到HttpHeaders类中
     * @param host 请求头属性
     * @param acceptEncoding  请求头属性
     * @param headers 请求头集合
     * @param httpHeaders 请求头封装到HttpHeaders中
     * @return map集合
     */
    @RequestMapping("/header")
    public Map<String,Object> getHeader(@RequestHeader("host") String host,
                                        @RequestHeader("Accept-Encoding") String acceptEncoding,
                                        @RequestHeader Map<String,String> headers,
                                        @RequestHeader HttpHeaders httpHeaders){
        HashMap<String, Object> map = new HashMap<>();
        map.put("host",host);
        map.put("Accept-Encoding",acceptEncoding);
        map.put("headers",headers);
        map.put("httpHeaders",httpHeaders);
        return map;
    }

    /**
     * 注解@RequestParam获取请求参数的3种用法：
     *  1、根据参数名获取单个参数值（默认require=true则必须要有此参数，否则服务会报错）
     *  2、根据参数名获取多个参数值（默认require=true则必须要有此参数，否则服务会报错）
     *      ① 定义为 String[]，属性值保存在数组中
     *      ②定义为 String，属性值保存在String中，逗号分隔
     *  3、所有请求参数封装到一个map集合中，类型必须为Map<String,String> 【注意此方法对应复合属性只会保存一个值】
     * @param age 请求参数age
     * @param inters 请求参数inters
     * @param interesting  请求参数inters
     * @param params  所有的请求参数集合
     * @return map集合
     */
    @RequestMapping("/RequestParam")
    public Map<String,Object> getRequestMapping(@RequestParam("age") Integer age,
                                                @RequestParam("inters") String[] inters,
                                                @RequestParam("inters") String interesting,
                                                //复合属性只会保存一个值，不会用逗号,拼接
                                                @RequestParam Map<String,String> params){
        HashMap<String, Object> map = new HashMap<>();
        map.put("age",age);
        map.put("inters",inters);
        map.put("interesting",interesting);
        map.put("params",params);
        return map;
    }


    @RequestMapping("/createCookie")
    public String createCookie(HttpServletResponse response){
        Cookie cookie = new Cookie("_ga", "there-do-not-allow-space");
        response.addCookie(cookie);
        return "index";
    }
    @RequestMapping("/cookie")
    public Map<String,Object> getCookieValue(@CookieValue("_ga") String cookieValue){
        HashMap<String, Object> map = new HashMap<>();
        map.put("_ga",cookieValue);
//        map.put("inters",inters);
//        map.put("interesting",interesting);
//        map.put("params",params);
        return map;
    }
}
