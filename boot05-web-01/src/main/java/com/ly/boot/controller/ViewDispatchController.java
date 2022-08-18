package com.ly.boot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * FileName:ViewDispatchController.class
 * Author:ly
 * Date:2022/8/18
 * Description:
 */
@Controller
public class ViewDispatchController {
    private final Logger logger = LoggerFactory.getLogger(ViewDispatchController.class);

    @GetMapping("/success")
    public String successful(Model model){
        String format = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss SSS").format(new Date());
        logger.info(format);
        //model、map、modelMap之类的数据会被自动放到请求域中
        model.addAttribute("now",format);

        return "success";
    }
}
