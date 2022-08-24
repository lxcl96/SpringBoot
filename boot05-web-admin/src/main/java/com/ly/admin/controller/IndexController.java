package com.ly.admin.controller;

import com.ly.admin.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpSession;

/**
 * FileName:IndexController.class
 * Author:ly
 * Date:2022/8/18 0018
 * Description: 根目录首页功能
 */
@Controller
public class IndexController {
    private final Logger log = LoggerFactory.getLogger(IndexController.class);

    @GetMapping({"/","/login"})
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String login(User user, HttpSession session, Model model) {
        log.info("有用户登陆，用户信息：" + user);
        if (StringUtils.hasLength(user.getUserName())&&StringUtils.hasLength(user.getPassword())) {
            session.setAttribute("loginUser", user);
        } else {
            model.addAttribute("msg","用户名或密码为空！");
            return "login";
        }
        //重定向解决表单重复提交问题，因为不能直接访问template下文件
        return "redirect:/index.html";
    }

    @GetMapping("/index.html")
    public String index(HttpSession session,Model model) {
        //有了拦截器，此处可以省略了
//        if (session.getAttribute("loginUser") == null) {
//            model.addAttribute("msg","请先登录！");
//            return "login";
//        }
        return "index";
    }

    @GetMapping({"/form_layouts"})
    public String FormLayout(Model model) {
        log.info("uri = dynamic_table");
        model.addAttribute("nowUri","form_layouts");
        return "form/form_layouts";
    }

}
