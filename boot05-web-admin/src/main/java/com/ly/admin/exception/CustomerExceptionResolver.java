package com.ly.admin.exception;

import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * FileName:CustomerExceptionResolver.class
 * Author:ly
 * Date:2022/9/2 0002
 * Description:
 */
//@Component
public class CustomerExceptionResolver implements HandlerExceptionResolver , Ordered {
    @Override
    public ModelAndView resolveException(HttpServletRequest request,
                                         HttpServletResponse response,
                                         Object handler, Exception ex) {
        //如果不解决（返回null或空的ModelAndView），就是默认的/error请求
        if (ex instanceof RuntimeException) { //自动换成了RuntimeException
            return new ModelAndView("login");
        }
        /* 模仿官方写法
            response.sendError(511,"我喜欢的状态码");
            return new ModelAndView();
        */
        return  null;
    }

    @Override
    public int getOrder() {
        return Ordered.HIGHEST_PRECEDENCE;
    }
}
