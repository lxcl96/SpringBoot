package com.ly.admin;

import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * FileName:Junit5Test.class
 * Author:ly
 * Date:2022/9/16 0016
 * Description:
 */
//@SpringBootTest//加了此注解才能整合SpringBoot的功能
@DisplayName("测试Junit5类")
public class Junit5Test {

    @Test
    @DisplayName("测试displayName注解")
    public void testDisplayName() {
        System.out.println(1);
    }

    @Test
    @Disabled //表示该测试方法被忽略
    public void test2() {
        System.out.println(2);
    }

    @BeforeEach
    @DisplayName("测试beforeEach注解")
    public void testBeforeEach() {
        System.out.println("每一个开始前");
    }

    @AfterEach
    @DisplayName("测试afterEach注解")
    public void testAfterEach() {
        System.out.println("每一个开始后");
    }

    @BeforeAll
    @DisplayName("测试beforeAll注解")
    public static void testBeforeAll() {
        System.out.println("所有开始之前");
    }

    @AfterAll
    @DisplayName("测试afterAll注解")
    public static void testAfterAll() {
        System.out.println("所有结束之后");
    }
}
