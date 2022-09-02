package com.ly.admin.filter;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

/**
 * FileName:MyFilter.class
 * Author:ly
 * Date:2022/9/2 0002
 * Description:
 */
@Slf4j
//@WebFilter(urlPatterns = {"/css/*","/js/*"})
public class MyFilter implements Filter {
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        log.info(this.getClass() + "初始化完成！");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        log.info(this.getClass() + "拦截到！" + req.getRequestURL());
        chain.doFilter(request,response);
    }

    @Override
    public void destroy() {
        log.info(this.getClass() + "销毁中！");
    }
}
