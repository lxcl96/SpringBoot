package com.ly.boot.convert;

import com.ly.boot.pojo.Pet;
import org.springframework.core.convert.converter.Converter;

/**
 * FileName:MyConvert.class
 * Author:ly
 * Date:2022/8/10
 * Description:
 */
public class MyConvert implements Converter<String, Pet> {
    /**
     * 专门来处理 pet=阿猫,3 这样的数据封装到Person.pet中，即【名字,年龄】
     * @param source 前端传递来的字符串（名字）
     * @return 封装好的pet
     */
    @Override
    public Pet convert(String source) {
        String[] sourceArr = source.split(",");
        String name = sourceArr[0]!=null?sourceArr[0].trim():"";
        String age = sourceArr[1]!=null?sourceArr[1].trim():"";
        return new Pet(name,age);
    }
}
