package com.ly.admin.aop;

import org.aopalliance.aop.Advice;
import org.springframework.aop.Pointcut;
import org.springframework.aop.PointcutAdvisor;
import org.springframework.aop.aspectj.AbstractAspectJAdvice;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.lang.Nullable;

/**
 * FileName:DruidAndSpringBootAdvisor.class
 * Author:ly
 * Date:2022/9/9 0009
 * Description:
 */

public class DruidAndSpringBootAdvisor implements PointcutAdvisor, Ordered {

    private  Advice advice;

    private  Pointcut pointcut;

    @Nullable
    private Integer order;


    public void setAdvice(Advice advice) {
        this.advice = advice;
    }

    public void setPointcut(Pointcut pointcut) {
        this.pointcut = pointcut;
    }

    public void setOrder(@Nullable Integer order) {
        this.order = order;
    }

    @Override
    public Pointcut getPointcut() {
        return this.pointcut;
    }

    @Override
    public Advice getAdvice() {
        return this.advice;
    }

    @Override
    public boolean isPerInstance() {
        return true;
    }

    @Override
    public int getOrder() {
        return this.order;
    }
}
