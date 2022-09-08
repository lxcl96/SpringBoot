package com.ly.admin.controller;

import com.ly.admin.bean.FileAttribute;
import com.ly.admin.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * FileName:IndexController.class
 * Author:ly
 * Date:2022/8/18 0018
 * Description: 根目录首页功能
 */
@Controller
public class IndexController {
    private final Logger log = LoggerFactory.getLogger(IndexController.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @ResponseBody
    @GetMapping({"/sql"})
    public Object sql() {
        String sql = "select * from tbl_emp";

        return jdbcTemplate.queryForList(sql);
    }


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


    @GetMapping({"/toFiles"})
    public String toFiles(Model model) {
        log.info("读取本地文件属性，封装到集合");
        String dir = this.getClass().getResource("/").getFile() + "/static/upload/lxcl96";
        File dirFile = new File(dir);
        if (!dirFile.exists()) {
            return "form/file";
        }
        ArrayList<FileAttribute> list = new ArrayList<>();
        File[] files = dirFile.listFiles();
        for (File file : files) {
           list.add(new FileAttribute(file.getName(),(file.length()/1024) + 1));
        }
        model.addAttribute("serverFiles",list);
        log.info(list.toString());
        return "form/file";
    }

}
