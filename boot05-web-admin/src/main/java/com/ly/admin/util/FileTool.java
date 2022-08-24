package com.ly.admin.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * FileName:FileSaveTool.class
 * Author:ly
 * Date:2022/8/24 0024
 * Description: 文件保存攻击
 */
public class FileTool {
    private static final Logger log = LoggerFactory.getLogger(FileTool.class);
    /**
     * 文件保存
     * @param fileName 文件名
     * @param bytes 文件字节内容
     * @param savePath 类路径下，文件保存位置
     */
    public void fileSave(String fileName, byte[] bytes, String savePath, Boolean override) throws IOException {
        String fileStr = this.getClass().getResource("/").getPath() + File.separator + savePath +  File.separator + fileName;
        File file = new File(fileStr);

        //java目录和文件是分开创建的
        if (!file.getParentFile().exists()) {
            file.getParentFile().mkdirs();
        }
        if (file.exists() && !override) {
            log.info(fileStr + "已经存在，且不重写！");
            return;
        } else if (!file.exists()) {
            file.createNewFile();
        }

        FileOutputStream out = new FileOutputStream(file);
        out.write(bytes);
        out.flush();
        out.close();

    }
}
