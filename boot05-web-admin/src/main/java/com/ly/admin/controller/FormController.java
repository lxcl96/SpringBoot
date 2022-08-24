package com.ly.admin.controller;


import com.ly.admin.util.FileTool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;


/**
 * FileName:FormController.class
 * Author:ly
 * Date:2022/8/24 0024
 * Description: 文件上传
 */
@Controller
public class FormController {
    private final Logger log = LoggerFactory.getLogger(FormController.class);

    /**
     * 单文件上传和多文件上传，参数必须和前端传过来的一样
     *  form标签必须加上enctype属性，enctype="multipart/form-data"，否则传递的只是文件名
     * @param image 单文件  [参数注解不写也可以]
     * @param files 多文件  [参数注解不写也可以]
     * @param username str  [参数注解不写也可以]
     * @param email str  [参数注解不写也可以]
     * @return 原页面
     */
    @PostMapping("/fileUpload")
    public String fileUpload(@RequestPart("image") MultipartFile image,
                             @RequestPart("image")MultipartFile[] files,
                             @RequestParam("username") String username,
                             @RequestParam("email") String email) throws IOException {
        log.info("前端传递参数 username：[ " + username + ", email:" + email  + " ]");
        log.info("前端传递单文件：[ " + image.getOriginalFilename()  + " ]");

        if (image.isEmpty() || files.length < 1) {
            log.info("头像和文件必须同时上传！");
            return "redirect:/form_layouts";
        }

        FileTool fileTool = new FileTool();
        StringBuilder mulName = new StringBuilder();
        for (MultipartFile file : files) {
            mulName.append(file.getOriginalFilename() + "  ");
        }
        log.info("前端传递多文件：[ " + mulName.toString() + " ]");

        fileTool.fileSave(image.getOriginalFilename(),image.getBytes(),
                "static/upload/" + username,true);
        log.info("单文件 ["+ image.getOriginalFilename() + "] 保存成功！");

        for (MultipartFile file : files) {
            fileTool.fileSave(file.getOriginalFilename(),file.getBytes(),
                    "static/upload/" + username,true);
        }
        log.info("多文件 ["+ mulName.toString() + "] 保存成功！");


        return "redirect:/form_layouts";
    }


}
