package com.ly.admin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpSession;

/**
 * FileName:DataTableController.class
 * Author:ly
 * Date:2022/8/18 0018
 * Description: 第二种方法，拦截器拦截所有的路径
 */
@Controller
public class DataTableController {
    private final Logger log = LoggerFactory.getLogger(DataTableController.class);

    @GetMapping({"/basic_table"})
    public String basic_table(Model model) {
        log.info("uri = basic_table");
        model.addAttribute("nowUri","basic_table");
        return "table/basic_table";
    }
    @GetMapping({"/dynamic_table"})
    public String dynamic_table(Model model) {
        log.info("uri = dynamic_table");
        model.addAttribute("nowUri","dynamic_table");
        return "table/dynamic_table";
    }
    @GetMapping({"/responsive_table"})
    public String responsive_table(Model model) {
        log.info("uri = responsive_table");
        model.addAttribute("nowUri","responsive_table");
        return "table/responsive_table";
    }
    @GetMapping({"/editable_table"})
    public String editable_table(Model model) {
        log.info("uri = editable_table");
        model.addAttribute("nowUri","editable_table");
        return "table/editable_table";
    }


}