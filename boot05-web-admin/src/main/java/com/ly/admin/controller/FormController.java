package com.ly.admin.controller;


import com.ly.admin.util.FileTool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import javax.servlet.http.HttpServletResponse;
import java.io.*;


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
                             @RequestPart("files")MultipartFile[] files,
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


        return "redirect:/toFiles";
    }

    @ResponseBody
    @GetMapping("/dispaly")
    public String fileShow(@RequestParam("filename")String filename) throws IOException {
        String filePath = this.getClass().getResource("/").getFile() + "/static/upload/lxcl96/" + filename;
        File file = new File(filePath);
        if (!file.exists()) {
            return "<h1 color='red'>错误，文件"+filename+"不存在<h1>";
        }
        char[] buffer = new char[102400];
        FileReader fileReader = new FileReader(file);
        int i = fileReader.read(buffer);
        log.info("读入字符数：" + i);
        fileReader.close();
        return String.valueOf(buffer);
    }


    @GetMapping("/download")
    public ResponseEntity<byte[]> download(@RequestParam("filename")String filename, HttpServletResponse response) throws IOException {
        String filePath = this.getClass().getResource("/").getFile() + "/static/upload/lxcl96/" + filename;
        File file = new File(filePath);
        if (!file.exists()) {
            response.getWriter().write("<h1 color='red'>错误，文件"+filename+"不存在<h1>");
            return null;
        }
        byte[] buffer = new byte[1024000];
        FileInputStream fileInputStream = new FileInputStream(file);
        int i = fileInputStream.read(buffer);
        log.info("读入字节数：" + i);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Disposition","attachment;filename=" + filename);
        ResponseEntity<byte[]> responseEntity = new ResponseEntity<>(buffer,httpHeaders, HttpStatus.OK);

        return responseEntity;

    }

}
