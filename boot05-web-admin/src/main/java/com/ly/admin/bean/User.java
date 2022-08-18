package com.ly.admin.bean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * FileName:User.class
 * Author:ly
 * Date:2022/8/18 0018
 * Description:
 */
@Getter
@Setter //可以@Data代替
@ToString
public class User {
    private String userName;
    private String password;
}
