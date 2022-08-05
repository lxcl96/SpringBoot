package com.ly.boot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * FileName:ModelAttributeController.class
 * Author:ly
 * Date:2022/8/5
 * Description: 用于测试@Model Attribute注解
 */
@Controller
@RequestMapping("/model")
public class ModelAttributeController {

    @ModelAttribute
    public void myModel(@RequestParam String abc, Model model) {
        model.addAttribute("attributeName",abc);
    }

    @RequestMapping("/hello")
    public String sayHello() {
        return "hello";
    }
}
