package com.ly.boot.convert;

import com.ly.boot.pojo.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;

/**
 * FileName:MyMessageConverter.class
 * Author:ly
 * Date:2022/8/17
 * Description:自定义messageConvert，只用于处理Person类型，目前只关心写逻辑
 *             最终返回格式为：属性值1;属性值2;
 */
public class MyMessageConverter implements HttpMessageConverter<Person> {
    private static Logger logger = LoggerFactory.getLogger(MyMessageConverter.class);

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        //暂时只关心写逻辑【发送给客户端】，不关心读逻辑【服务器获取客户端发送的】
        return false;
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        return clazz.isAssignableFrom(Person.class);
    }

    /**
     * 最重要，因为服务器要统计所有messageConverter都能写出哪些媒体类型mediaType
     * @return 自定义消息类型 [application/x-any]
     */
    @Override
    public List<MediaType> getSupportedMediaTypes() {
        //借助工具方法，由字符串，解析出自定义媒体类型
        return MediaType.parseMediaTypes("application/x-any");
    }

    @Override
    public List<MediaType> getSupportedMediaTypes(Class<?> clazz) {
        return HttpMessageConverter.super.getSupportedMediaTypes(clazz);
    }

    @Override
    public Person read(Class<? extends Person> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        //暂时只关心写逻辑【发送给客户端】，不关心读逻辑【服务器获取客户端发送的】
        return null;
    }

    @Override
    public void write(Person person, MediaType contentType, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        //自定义协议数据的写出【返回给客户端】
        String resq = String.format("%s;%d;%s",person.getUserName(),person.getAge(),person.getBrith());
        logger.info(resq);

        OutputStream outputStream = outputMessage.getBody();
        outputStream.write(resq.getBytes());

    }
}
