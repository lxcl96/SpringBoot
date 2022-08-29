package com.ly.admin;

import java.io.File;

/**
 * FileName:FileTest.class
 * Author:ly
 * Date:2022/8/29 0029
 * Description:
 */
public class FileTest {

    public static void main(String[] args) {
        String dir = "C:\\Users\\admin\\Desktop";
        File dirFile = new File(dir);
        if (!dirFile.exists()) {
            System.out.println("目录为空");
        }
        File[] files = dirFile.listFiles();
        for (File file : files) {
            System.out.println(file.getName() + "===>" + Math.floorDiv(file.length(),1024) + "KB");
        }
    }
}
