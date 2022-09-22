package com.ly.boot.listener;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * FileName:MyCommandLineRunner.class
 * Author:ly
 * Date:2022/9/22 0022
 * Description:
 */
@Component
public class MyCommandLineRunner implements CommandLineRunner {
    @Override
    public void run(String... args) throws Exception {
        System.out.println("MyCommandLineRunner    ...run..");
    }
}
