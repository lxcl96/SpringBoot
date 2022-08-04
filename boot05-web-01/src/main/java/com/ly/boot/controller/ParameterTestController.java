package com.ly.boot.controller;

import org.springframework.http.HttpCookie;
import org.springframework.http.HttpHeaders;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.*;
import java.util.Arrays;
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
        cookie.setComment("cookie create test.");
        cookie.setMaxAge(36000);//依据格林时间
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setDomain("localhost");
        response.addCookie(cookie);
        return "index"; //@RestController导致的无法跳转
    }


    /**
     * 注解@CookieValue获取请求cookie的值的2种方法：
     *  1、根据cookie名，获取对应cookie值
     *  2、根据cookie名，获取对应cookie对象
     *  注意：一定要写cookie名，就算只有一个cookie
     * @param cookieValue 通过cookie名获取对应cookie值
     * @param cookie 通过cookie名获取对应cookie对象
     * @return map集合
     */
    @RequestMapping("/cookie")
    public Map<String,Object> getCookieValue(@CookieValue("Idea-ac70e2d0") String cookieValue,
                                             @CookieValue("_ga") Cookie cookie){
        HashMap<String, Object> map = new HashMap<>();
        map.put("ideaCookie",cookieValue);
        map.put("_ga", cookie);
        return map;
    }

    /**
     * 注解@RequestBody获取post请求的请求体（即？后面的参数）
     * @param body 参数链
     * @return map集合
     */
    @RequestMapping("/requestBody")
    public Map<String,Object> getRequestBody(@RequestBody String body){
        HashMap<String, Object> map = new HashMap<>();
        map.put("body",body);
        return map;
    }

    /**
     * 获取请求链中的矩阵变量属性值（地址中 ;分割的）
     * 矩阵变量放在请求路径中的，则必须是restful风格 {xxx}
     * 注意：springBoot默认禁用掉了矩阵变量的功能：自动配置WebMvcAutoConfiguration --configurePathMatch()--UrlPathHelper.removeSemicolonContent = true(去掉url的分号;部分)
     *      手动开启：
     *          1、配置类中自己手动给ioc容器中添加一个WebMvcConfigurer类型的组件（@Bean方法）
     *          2、配置类实现WebMvcConfigurer接口，重写configurePathMatch()方法
     *
     *  语法1：http://localhost:8080/cars/sell;low=34;brand=byd,audi,yd
     *  语法2：http://localhost:8080/cars/sell;low=34;brand=byd;brand=audi;brand=yd
     *  语法3：http://localhost:8080/boss/1;age=20/2;age=10
     * @param low 矩阵变量1
     * @param brands 矩阵变量2
     * @param variables 矩阵变量集合
     * @return map集合
     */
    @RequestMapping("/cars/{path}")
    public Map<String,Object> getMatrixVariable(@MatrixVariable("low") Integer low,
                                                @MatrixVariable("brand") String brands,
                                                @MatrixVariable Map<String, String> variables,
                                                @PathVariable("path") String path) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("low",low);
        map.put("brand", brands);
        map.put("variables", variables);
        map.put("path", path);
        return map;
    }

    //http://localhost:8080/boss/1;age=20/2;age=10
    @RequestMapping("/boss/{bossId}/{empId}")
    public Map<String,Object> boss(@MatrixVariable(value = "age",pathVar = "bossId") Integer bossAge,
                                   //具有相同名字的矩阵变量，通过pathVar消除歧义
                                   @MatrixVariable(value = "age",pathVar = "empId") String empAge,
                                   @PathVariable("bossId") Integer bossId,
                                   @PathVariable("empId") Integer empId) {

        HashMap<String, Object> map = new HashMap<>();
        map.put("empId",empId);
        map.put("bossId", bossId);
        map.put("empAge", empAge);
        map.put("bossAge", bossAge);
        return map;
    }
}
