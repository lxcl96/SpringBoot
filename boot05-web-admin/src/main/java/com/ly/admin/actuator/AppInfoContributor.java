package com.ly.admin.actuator;

import org.springframework.boot.actuate.info.Info;
import org.springframework.boot.actuate.info.InfoContributor;
import org.springframework.stereotype.Component;

/**
 * FileName:AppInfoContributor.class
 * Author:ly
 * Date:2022/9/19 0019
 * Description:
 */
@Component
public class AppInfoContributor implements InfoContributor {
    @Override
    public void contribute(Info.Builder builder) {
        builder.withDetail("appName","boot-admin")
                .withDetail("aaaa","aaaaa")
                .withDetail("bbb",1111)
                .withDetail("tag",true);
    }
}
