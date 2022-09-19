package com.ly.admin.actuator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.actuate.health.AbstractHealthIndicator;
import org.springframework.boot.actuate.health.Health;
import org.springframework.stereotype.Component;

import java.io.File;

/**
 * FileName:MyComponentHealthIndicator.class
 * Author:ly
 * Date:2022/9/19 0019
 * Description:
 */

@Component
//类的后缀名  必须为HealthIndicator
public class MyComponentHealthIndicator extends AbstractHealthIndicator {
    private final Logger log = LoggerFactory.getLogger(MyComponentHealthIndicator.class);

    /**
     * 编写自己的业务健康检查方法
     * @param builder 不可变 Health 实例的生成器
     * @throws Exception 异常
     */
    @Override
    protected void doHealthCheck(Health.Builder builder) throws Exception {
        //比如测试数据库的连接，文件存不存在等等
        if (new File("D:\\JavaWork\\SpringBoot\\Note\\SpringBoot2笔记.md").exists()) {
            log.info("D:\\JavaWork\\SpringBoot\\Note\\SpringBoot2笔记.md   文件存在");
            builder.up();
        } else {
            log.info("D:\\JavaWork\\SpringBoot\\Note\\SpringBoot2笔记.md   文件不存在");
            builder.down();
        }

        builder.withDetail("fileName","SpringBoot2笔记.md")
                .withDetail("path","D:\\JavaWork\\SpringBoot\\Note\\SpringBoot2笔记.md")
                .withDetail("directory","D:\\JavaWork\\SpringBoot\\Note\\");

    }
}
