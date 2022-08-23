package com.ly.admin.interceptor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * FileName:UserInterceptor.class
 * Author:ly
 * Date:2022/8/23 0023
 * Description: 后端页面访问，用户权限校验
 */
public class UserInterceptor implements HandlerInterceptor {
    private final Logger log = LoggerFactory.getLogger(UserInterceptor.class);
    /**
     * 控制器方法执行前执行
     * @param request request
     * @param response response
     * @param handler handler控制器方法，即
     * @return true，表示放行；false，表示拦截
     * @throws Exception 异常
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("拦截路径：" + request.getRequestURL());
        HttpSession session = request.getSession();
        if (session.getAttribute("loginUser") == null) {
            session.setAttribute("msg","请先登录");
            //重定向比较好
            response.sendRedirect("/");
            return false;
        }
        return true;
    }

    //控制器方法执行后执行
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        HandlerInterceptor.super.postHandle(request, response, handler, modelAndView);
    }

    //视图渲染render后执行
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        HandlerInterceptor.super.afterCompletion(request, response, handler, ex);
    }

}
