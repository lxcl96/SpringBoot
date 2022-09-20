package com.ly.admin.actuator;

import org.springframework.boot.actuate.endpoint.annotation.Endpoint;
import org.springframework.boot.actuate.endpoint.annotation.ReadOperation;
import org.springframework.boot.actuate.endpoint.annotation.WriteOperation;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;


import java.util.Map;

import static java.util.Collections.*;

/**
 * FileName:MyServiceEndpoint.class
 * Author:ly
 * Date:2022/9/20 0020
 * Description:
 */
@Component
//@ConfigurationProperties(prefix = "myservice")
@Endpoint(id = "myService") //和info、health、metrics同级别的
public class MyServiceEndpoint {

    @ReadOperation
    public Map getDockerInfo(){
        return singletonMap("info","docker started...");
    }

    @WriteOperation
    private void restartDocker(){
        System.out.println("docker restarted....");
    }

}
