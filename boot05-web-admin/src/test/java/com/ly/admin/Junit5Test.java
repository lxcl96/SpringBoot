package com.ly.admin;

import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.NullSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.connection.SortParameters;
import org.springframework.http.HttpStatus;

import java.time.Duration;
import java.util.stream.Stream;

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

    @Test
    @DisplayName("测试简单断言")
    void testSimpleAssertions() {
        int sum = sum(1, 3);
        Assertions.assertEquals(5,sum,"要求必须是5");
    }
    int sum(int i,int j) {
        return i + j;
    }


    @Test
    @DisplayName("测试数组断言")
    void testArrayAssertions() {
        Assertions.assertArrayEquals(new int[]{1,2},new int[]{2,1},"数组元素不一样");

    }


    @Test
    @DisplayName("测试组合断言")
    void testAllAssertions() {
        Assertions.assertAll("第一组条件" ,
                () -> Assertions.assertTrue(true,"不为true") ,
                () -> Assertions.assertNull(null,"不为null"));
    }


    @Test
    @DisplayName("异常断言")
    void testExceptionAssertions(){
        //类似于异常捕获，如果不出现才会报错，出线了指定异常就不会报错
        Assertions.assertThrows(ArithmeticException.class,
                () -> {int i = 10/ 1;},
                "业务逻辑居然正确运行了！");


        System.out.println("end");
    }

    @Test
    @DisplayName("测试超时断言")
    void testTimeOutAssertions() {
        Assertions.assertTimeout(Duration.ofSeconds(1),
                () -> {Thread.sleep(3000);},
                "超过1秒钟");
    }


    @Test
    @DisplayName("测试快速失败")
    void testFail() {
        Assertions.fail("测试提前结束");
        System.out.println("end");
    }


    @Test
    @DisplayName("测试前置条件")
    void testAssumptions() {
        Assumptions.assumeTrue(false,"为true才能正常执行，当前为false，开始退出。");
        System.out.println("end");
    }



    @NullSource//null值
    @ValueSource(ints = {1,2,3,4,5})//八大基础类型和String，class类型
    @MethodSource(value = {"StringProvider"})//方法返回值参数，注意必须为stream形式
    @EnumSource(HttpStatus.class)//枚举类中所有类型均测试
    @ParameterizedTest()
    @DisplayName("参数化测试")
    void testParameterizedTest(Object obj) {
        System.out.println("obj=" + obj + ", class=" + obj.getClass());
    }


    static Stream<String> StringProvider(){
        return Stream.of("apple","banana");
    }
}
