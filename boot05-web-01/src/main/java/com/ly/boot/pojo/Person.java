package com.ly.boot.pojo;

import lombok.Data;
import lombok.ToString;

import java.util.Date;

/**
 * FileName:Person.class
 * Author:ly
 * Date:2022/8/9
 * Description:
 */
@Data
@ToString
public class Person {
    private String userName;
    private Integer age;
    private Date brith;
    private Pet pet;
}



