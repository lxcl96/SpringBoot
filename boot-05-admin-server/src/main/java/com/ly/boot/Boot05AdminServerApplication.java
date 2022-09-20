package com.ly.boot;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@EnableAdminServer
@SpringBootApplication
public class Boot05AdminServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(Boot05AdminServerApplication.class, args);
    }

}
