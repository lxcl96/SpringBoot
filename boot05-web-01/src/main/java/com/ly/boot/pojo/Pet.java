package com.ly.boot.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * FileName:Pet.class
 * Author:ly
 * Date:2022/8/9
 * Description:
 */
@Data
@ToString
@AllArgsConstructor
@NoArgsConstructor
public class Pet {
    private String name;
    private String age;
}
