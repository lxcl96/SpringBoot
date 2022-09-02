package com.ly.admin.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * FileName:GlobalExceptionHandler.class
 * Author:ly
 * Date:2022/9/2 0002
 * Description:
 */
//@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseBody
    @ExceptionHandler(ServerException.class)
    public String resolver(Exception e) {
        return "全局异常处理器";
    }
}
