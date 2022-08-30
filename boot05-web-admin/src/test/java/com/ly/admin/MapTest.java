package com.ly.admin;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * FileName:MapTest.class
 * Author:ly
 * Date:2022/8/30 0030
 * Description:
 */
public class MapTest {
    public static void main(String[] args) {
        //new MultiValue<>(new LinkedHashMap<>(6, 1));
        List<String> strings = new ArrayList<String>();
        strings.add("哈哈1");
        strings.add("哈哈2");
        strings.add("哈哈3");
        Fanx<String> stringFanx = new Fanx<>(strings);

    }
}
