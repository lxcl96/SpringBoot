package com.ly.admin.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ly.admin.bean.User;
import com.ly.admin.exception.ServerException;
import com.ly.admin.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * FileName:DataTableController.class
 * Author:ly
 * Date:2022/8/18 0018
 * Description: 第二种方法，拦截器拦截所有的路径
 */
@Controller
public class DataTableController {
    private final Logger log = LoggerFactory.getLogger(DataTableController.class);

    @Autowired
    private UserService userService;

    //@ExceptionHandler(/*ArithmeticException.class*/)
    @ResponseBody
    public String exceptionResolver(HttpServletRequest request,Exception e) {
        Object attribute = request.getAttribute("org.springframework.boot.web.servlet.error.DefaultErrorAttributes.ERROR");
        return "<h1>发生异常，" + attribute.toString() + "</h1>";
    }

    //@ResponseStatus(code = HttpStatus.OK,reason = "服务器内部错误")
    @GetMapping({"/basic_table"})
    public String basic_table(Model model) throws ServerException {
        //int i = 10 / 0;
//        throw new ServerException();
        log.info("uri = basic_table");
        model.addAttribute("nowUri","basic_table");
        return "table/basic_table";
    }


    @GetMapping({"/dynamic_table"})
    public String dynamic_table(@RequestParam(name = "pn",defaultValue = "1") Long pn, Model model) {

        /**
         *  分页查询
         *      参数1为：page分页
         *      参数2为：wrapper查询条件
         */
        Page<User> page = userService.page(new Page<>(pn, 2L), null);

        page.hasPrevious();
        page.hasNext();


        log.info("uri = dynamic_table\n");

        model.addAttribute("nowUri","dynamic_table");
        model.addAttribute("page",page);
        return "table/dynamic_table";
    }

    @GetMapping({"/user/delete/{id}"})
    public String dropUser(@PathVariable(name = "id") Long id,
                           @RequestParam(name = "pn",defaultValue = "1") Long pn,
                           RedirectAttributes ra) {
        userService.removeById(id);
        //RedirectAttributes 重定向携带参数
        ra.addAttribute("pn",pn);

        return "redirect:/dynamic_table";
    }



    @GetMapping({"/responsive_table"})
    public String responsive_table(Model model) {
        if (model != null) {
            throw new ArithmeticException("1/0");
        }
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
