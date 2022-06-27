package com.ly.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @FileName:MainApp.class
 * @Author:ly
 * @Date:2022/6/27
 * @Description:
 */

/**
 *  1、@SpringBootApplication 该注解用于告诉springboot 这程序是一个springboot应用
 *  2、使用该注解的类MainApplication 成为主程序类，是所有程序的启动入口
 */
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        //运行主程序
        SpringApplication.run(MainApplication.class,args);
    }
}
