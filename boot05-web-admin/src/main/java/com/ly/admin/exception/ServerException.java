package com.ly.admin.exception;

import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * FileName:ServerException.class
 * Author:ly
 * Date:2022/9/2 0002
 * Description:
 */
@ResponseStatus(reason = "my exception test!")
public class ServerException extends Exception{
}
