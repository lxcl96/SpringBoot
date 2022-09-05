package com.ly.admin.customizer;

import org.springframework.boot.web.server.WebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;

/**
 * FileName:CustomizerWebServerFactoryAttributes.class
 * Author:ly
 * Date:2022/9/5 0005
 * Description:
 */
public class CustomizerWebServerFactoryAttributes implements WebServerFactoryCustomizer<ConfigurableServletWebServerFactory> {

    @Override
    public void customize(ConfigurableServletWebServerFactory factory) {
        factory.setPort(8888);
    }
}
