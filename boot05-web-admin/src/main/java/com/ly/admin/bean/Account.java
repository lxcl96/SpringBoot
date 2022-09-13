package com.ly.admin.bean;

import lombok.Data;
import lombok.ToString;

/**
 * FileName:Account.class
 * Author:ly
 * Date:2022/9/13 0013
 * Description:
 */

@Data
@ToString
public class Account {
    private Long id;
    private String userId;
    private Integer money;
}
