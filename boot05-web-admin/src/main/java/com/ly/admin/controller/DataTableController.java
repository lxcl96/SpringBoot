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
 * Description:
 */
@Controller
public class DataTableController {
    private final Logger log = LoggerFactory.getLogger(DataTableController.class);

    @GetMapping({"/basic_table"})
    public String basic_table(HttpSession session, Model model) {
        log.info("uri = basic_table");
        if (session.getAttribute("loginUser") == null) {
            model.addAttribute("msg","请先登录！");
            return "login";
        }
        model.addAttribute("nowUri","basic_table");
        return "table/basic_table";
    }
    @GetMapping({"/dynamic_table"})
    public String dynamic_table(HttpSession session, Model model) {
        log.info("uri = dynamic_table");
        if (session.getAttribute("loginUser") == null) {
            model.addAttribute("msg","请先登录！");
            return "login";
        }
        model.addAttribute("nowUri","dynamic_table");
        return "table/dynamic_table";
    }
    @GetMapping({"/responsive_table"})
    public String responsive_table(HttpSession session, Model model) {
        log.info("uri = responsive_table");
        if (session.getAttribute("loginUser") == null) {
            model.addAttribute("msg","请先登录！");
            return "login";
        }
        model.addAttribute("nowUri","responsive_table");
        return "table/responsive_table";
    }
    @GetMapping({"/editable_table"})
    public String editable_table(HttpSession session, Model model) {
        log.info("uri = editable_table");
        if (session.getAttribute("loginUser") == null) {
            model.addAttribute("msg","请先登录！");
            return "login";
        }
        model.addAttribute("nowUri","editable_table");
        return "table/editable_table";
    }


}
