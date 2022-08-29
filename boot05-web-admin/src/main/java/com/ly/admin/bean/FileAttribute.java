package com.ly.admin.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * FileName:FileAttribute.class
 * Author:ly
 * Date:2022/8/29 0029
 * Description:
 */
@Data
@AllArgsConstructor
public class FileAttribute {
    private String name;
    private long size;
}
