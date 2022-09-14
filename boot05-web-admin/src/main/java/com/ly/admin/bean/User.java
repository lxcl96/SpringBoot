package com.ly.admin.bean;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * FileName:User.class
 * Author:ly
 * Date:2022/8/18 0018
 * Description:
 */
@Data
@ToString
@TableName("user")
public class User {
    @TableField(exist = false)
    private String userName;
    @TableField(exist = false)
    private String password;



    //以下是数据库字段
    private Long id;
    private String name;
    private Integer age;
    private String email;


}
