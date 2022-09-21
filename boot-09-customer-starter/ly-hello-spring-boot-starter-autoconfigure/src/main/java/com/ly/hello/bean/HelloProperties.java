package com.ly.hello.bean;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * FileName:HelloProperties.class
 * Author:ly
 * Date:2022/9/21 0021
 * Description:
 */
@ConfigurationProperties(prefix = "ly.hello")
public class HelloProperties {

    private String prefix;
    private String suffix;


    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }
}
