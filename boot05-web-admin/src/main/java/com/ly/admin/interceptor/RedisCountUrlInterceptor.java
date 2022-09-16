package com.ly.admin.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * FileName:RedisCountUrlInterceptor.class
 * Author:ly
 * Date:2022/9/16 0016
 * Description: 拦截请求路径，redis中统计地址访问次数
 */
@Slf4j
@Component//加到容器中，redis模板才会自动注入
public class RedisCountUrlInterceptor implements HandlerInterceptor {
    @Autowired
    private RedisTemplate<Object, Object> redisTemplate;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String requestURI = request.getRequestURI();
        if (!("/acc".equals(requestURI) || "/city".equals(requestURI) || "/sql".equals(requestURI))) {
            return true;
        }

        Integer count = (Integer)redisTemplate.opsForValue().get(requestURI);
        if (Boolean.FALSE.equals(redisTemplate.hasKey(requestURI)) || null == count) {
            log.info("请求地址：{}  第一次访问！",requestURI);
            redisTemplate.opsForValue().set(requestURI,1);
            return true;
        }

        redisTemplate.opsForValue().set(requestURI,count + 1);
        log.info("请求地址：{}  访问次数：{}",requestURI,count + 1);
        return true;
    }
}
