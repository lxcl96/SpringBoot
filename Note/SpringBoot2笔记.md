

# 0、前提准备

## 学习要求：

+ 熟悉Spring基础
+ 熟悉Maven使用

## 环境要求：

+ JDK1.8及以上
+ Maven3.3及以上

## 官方资料：

+ 笔记 ：[第一季：SpringBoot2核心技术-基础入门 · 语雀 (yuque.com)](https://www.yuque.com/atguigu/springboot/rmxq85)

+ 原代码：[SpringBoot2核心技术与响应式编程: SpringBoot2核心技术与响应式编程 (gitee.com)](https://gitee.com/leifengyang/springboot2)

  

# 1、SpringBoot核心技术--基础入门

## 01 Spring5的重大升级

在引入响应式编程后，服务开发就可以分为两种

+ servlet技术栈

+ webflux构建异步数据流方式，实现响应式编程

  <img src="img/响应式开发和servlet开发.png" style="zoom:80%;" />

## 02 Spring5内部源码设计

基于Java8新特性，如：接口的默认实现，重新设计源码架构



## 03 为什么使用SpringBoot

它可以帮我们快速的创建出生产级别的Spring应用，使得Spring开发变得简单。



## 04 SpringBoot优点

1. 创建出独立的Spring应用
2. 内嵌web服务器
3. 自动starter依赖，简化构建配置
4. 自动配置Spring及第三方功能
5. 提供生产级别的监控、健康检查及外部化配置
6. 无代码生成，无需填写XML

## 05 Spring缺点

+ 版本更新迭代特别快
+ 封装太深，内部原理复杂，不容易精通

## 06 SpringBoot2入门

### 1、系统要求

+ Java 8 & 兼容Java14
+ Maven 3.3+
+ IDEA 2019.1.2
+ Tomcat 9.0+

#### 1.1、Maven设置

设置Maven配置文杰settings.xml设置镜像和jdk版本

```xml
<!--settings.xml -->
<mirrors>
      <mirror>
        <id>nexus-aliyun</id>
        <mirrorOf>central</mirrorOf>
        <name>Nexus aliyun</name>
        <url>http://maven.aliyun.com/nexus/content/groups/public</url>
      </mirror>
  </mirrors>
 
  <profiles>
         <profile>
              <id>jdk-1.8</id>
              <activation>
                <activeByDefault>true</activeByDefault>
                <jdk>1.8</jdk>
              </activation>
              <properties>
                <maven.compiler.source>1.8</maven.compiler.source>
                <maven.compiler.target>1.8</maven.compiler.target>
                <maven.compiler.compilerVersion>1.8</maven.compiler.compilerVersion>
              </properties>
         </profile>
  </profiles>
```

### 2、HelloWorld

​	需求：浏览发送/hello请求，响应 Hello，Spring Boot 2 

#### 2.1、创建Maven工程

#### 2.2、pom.xml中引入依赖Springboot

```xml
<!-- pom.xml-->


<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com.ly</groupId>
    <artifactId>boot01-helloworld</artifactId>
    <version>1.0-SNAPSHOT</version>
    <packaging>jar</packaging>

    <properties>
        <maven.compiler.source>8</maven.compiler.source>
        <maven.compiler.target>8</maven.compiler.target>
    </properties>

    <!-- 1、使用springboot父标签-->
    <parent>
        <groupId>org.springframework.boot</groupId>
        <artifactId>spring-boot-starter-parent</artifactId>
        <version>2.7.1</version>
    </parent>

    <!-- 2、添加springboot场景依赖  如这是个web工程依赖于web-->
    <dependencies>
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>
    </dependencies>

    <!-- 对于web程序直接打成jar包，在服务器上执行即可-->
    <build>
        <plugins>
            <plugin>
                <groupId>org.springframework.boot</groupId>
                <artifactId>spring-boot-maven-plugin</artifactId>
            </plugin>
        </plugins>
    </build>

</project>
```

#### 2.3、创建SpringBoot主程序

```java
package com.ly.boot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @FileName:MainApp.class
 * @Author:ly
 * @Date:2022/6/27
 * @Description:
 */

/**
 *  1、@SpringBootApplication 该注解用于告诉springboot 这程序是一个springboot应用
 *  2、使用该注解的类MainApplication 成为主程序类，是所有程序的启动入口
 */
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        //运行主程序
        SpringApplication.run(MainApplication.class,args);
    }
}
```

#### 2.4、编写业务逻辑（如：控制器方法）

```java
package com.ly.boot.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @FileName:HelloController.class
 * @Author:ly
 * @Date:2022/6/27
 * @Description:
 */
@RestController //@Response + @Controller
public class HelloController {
    @RequestMapping("/")
    public String helloWorld() {
        return "Hello Spring Boot!";
    }
}
```

#### 2.5、直接运行主程序main方法即可测试

主程序：类上有`@SpringBootApplication`注解

#### 2.6、SpringBoot简化配置文件 

==获取配置文件优先顺序：==

![image-20220627164554967](img\springboot配置文件加载顺序.png)

官方文档：[Common Application Properties (spring.io)](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#appendix.application-properties)

​	SpringBoot配置文件`application.properties`用于配置其他所有框架的配置：tomcat、springmvc、mybatis、logback等等。放在Maven工程的`resources`目录下

```properties
# 修改服务端口号
server.port=8888
...
```

#### 2.7、SpringBoot简化部署

对于web程序直接打成jar包，在服务器上执行即可。Maven需要导入`spring-boot-maven-plugin`插件

```xml
 <!-- pom.xml-->
 <build>
     <plugins>
         <plugin>
             <groupId>org.springframework.boot</groupId>
             <artifactId>spring-boot-maven-plugin</artifactId>
         </plugin>
     </plugins>
</build>
```

```sh
# 简单运行 jar包 关闭dos窗口就停止了
java -jar xxx.jar
```

注意：取消dos窗口中“属性”-“快速编辑模式”

## 07 了解自动配置原理

### 1、SpringBoot特点

#### 1.1、依赖管理

+ 父项目做依赖管理

  ```xml
  <!-- pom.xml 中依赖 spring-boot-starter-parent -->
  <parent>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-starter-parent</artifactId>
      <version>2.7.1</version>
  </parent>
  <!--spring-boot-starter-parent的父类 spring-boot-dependencies 中几乎定义好开发常用的各种依赖版本-->
  <parent>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-dependencies</artifactId>
      <version>2.7.1</version>
  </parent>
  ```

+ 开发导入starter场景启动器

  官方说明：[Developing with Spring Boot](https://docs.spring.io/spring-boot/docs/current/reference/html/using.html#using.build-systems.starters)

  ```xml
  1、spring-boot-starter-* 就代表Spring官方某种场景
  2、只要引入starter，这个场景的所有常规需要的依赖都会自动引入
  3、*-spring-boot-starter为第三方提供的场景启动器
  4、所有场景启动器starter底层都会依赖spring-boot-starter，这是最基本的starter
  如：pom.xml
  <dependencies>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-starter-web</artifactId>
      </dependency>
  </dependencies>
  ```

+ 无需关注版本号，自动版本仲裁

  需要当前项目依赖的`starter`场景启动器中有定义好依赖才可以不用写版本号（如mysql），如果`starter`中没有声明依赖的话需要自己手动声明依赖dependencies/dependency（如mybatis）。

+ 可以修改版本号

  ```xml
  <!-- 通过查看源码中依赖版本号定义取值方式，可以自定义版本号 即spring-boot-dependencies-2.7.1.pom.xml中的properties标签中定义-->
  
  <!-- 使用的是xml定义属性，${}取值
  如： <mysql.version>8.0.29</mysql.version>
  	 ${mysql.version}
  -->
  
  # 自己的pom.xml复写属性mysql.version，实现自定义版本
  <profiles>
      <mysql.version>5.1.31</mysql.version>
      ...
  </profiles>
  
  <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
  </dependency>
  
  
  #方式二：直接加上版本号即可，不需要自定义属性
  <dependency>
      <groupId>mysql</groupId>
      <artifactId>mysql-connector-java</artifactId>
      <version>5.1.31</version>
  </dependency>
  
  ```

  

#### 1.2、自动配置

+ 自动配置好tomcat

  > 需要引入tomcat依赖 `父pom中引入了spring-boot-starter-tomcat`
  >
  > 配置tomcat

+ 自动配置好springmvc

  > 引入SpringMVC全套组件
  >
  > 自动配置好springmvc常用功能。如：拦截器，注解驱动，默认servlet-handler等

+ 自动配置好web常见功能，如：字符集编码.文件上传，视图解析器等

+ 默认的包结构（当然也可以自己指定）

  > 主程序所在的包及其下面的所有子包里面的组件都会被默认扫描。如果要自定义包扫描则：`@SpringBootApplication(scanBasePackages = {"com.ly","cn.ly"})`或者 `@ComponentScan(basePackages = {})`
  >
  > ```java
  > @SpringBootApplication
  > //等同于  看此注解内部详情
  > @SpringBootConfiguration
  > @EnableAutoConfiguration
  > @ComponentScan
  > ```
  >
  > ```sh
  > # 包结构
  > com
  >  +- example
  >      +- myapplication
  >          +- MyApplication.java # 主程序(使用@SpringBootApplication注解的)
  >          |
  >          +- customer
  >          |   +- Customer.java
  >          |   +- CustomerController.java
  >          |   +- CustomerService.java
  >          |   +- CustomerRepository.java
  >          |
  >          +- order
  >              +- Order.java
  >              +- OrderController.java
  >              +- OrderService.java
  >              +- OrderRepository.java
  > ```

+ 各种配置拥有默认值

  > 官方配置默认值文档：[Common Application Properties (spring.io)](https://docs.spring.io/spring-boot/docs/current/reference/html/application-properties.html#appendix.application-properties)
  >
  > `默认配置最终都是映射到对应类的方法上，而配置文件的值最终都会在IOC容器中的该类上体现`
  >
  > 如：文件上传 spring.servlet.multipart.max-file-size=1MB

+ 按需加载所有自动配置项

  > pom文件中引入了哪些场景starter，那些场景starter才会自动配置
  >
  > 所有自动配置均在`spring-boot-autoconfigure`包中配置
  >
  > ![image-20220629143743248](img\image-20220629143743248.png)

+ …



### 2、容器功能

#### 2.1、组件添加

指IOC容器中添加Bean，Controller等

##### ①、@Configuration `配置类代替Spring的xml配置文件`

+ 基本使用
+ *Full模式与Lite模式（重点）*
  + 示例
  + 最佳实战
    + 配置类组件之间无依赖关系的，用Lite模式。加速容器启动过程，减少判断。
    + 配置类组件之间有依赖关系，用Full模式。方法被调用会得到之前的单实例组件。

==即用@Configuration注解的配置类代替SpringConfig.xml配置文件，一个类就是一个配置文件==

==注意：此方法默认是单实例singleton，每次获取都是同一个对象==

==可以使用@Scope("prototype") 或@Scope("singleton")定义多/单实例，此注解只能用于类或方法上！==

***配置类创建组件：***

```java

/**
 * 1、@Configuration 此注解用于告诉SpringIOC该类为一个配置类（对应一个配置文件）,默认也是单实例的
 * 2、proxyBeanMethods：SpringBoot2新特点 字面意思表示“是否代理bean对象的调用方法”
 *      默认为true：表示使用代理bean对象的调用方法 即每次调用bean对象Configuration的user01方法都会先去IOC容器中找看看是否有，如果有就直接拿；如果没有才会重新创建。总之一句话：true，保持对象组件user01单实例
 *      设置为false：表示不使用代理bean对象的调用方法 即每次调用bean对象Configuration的user01方法都会创建一个新的对象组件user01
 * 3、由 proxyBeanMethods 引申出SpringBoot的两种配置：
 *      full：全配置  (proxyBeanMethods=true) 每次都先从IOC容器中先找，找到了就返回。找不到再创建
 *          full优点：每次都先从IOC取，没有才创建节约内存资源
 *      lite：轻量级配置 (proxyBeanMethods=false) 每次都是直接重新创建一个新的组件
 *          lite优点：springBoot启动更快，不坚持组件在Ioc容器中是否已存在
 *
 *  4、proxyBeanMethods属性用于解决：组件依赖(true依赖，false不依赖)
 *      如User类中有一个Pet pet对象。
 *      配置类中user01想要直接从IOC中取已经存在的Pet ht 就必须 设置proxyBeanMethods=true
 *
 *  5、推荐如果容器中只是用于注册组件，别的组件也不依赖这个组件：则此组件应该设置为false 即lite模式
 *  6、推荐如果容器注册组件时且别的组件也依赖这个组件：则此组件应该设置为true即full模式
 */
@org.springframework.context.annotation.Configuration(proxyBeanMethods = false)
public class Configuration { //该类本身也是一个组件，在IOC中可以取到

    //@Scope("prototype")
    @Bean
    //用方法名作为组件对象的id，返回类型就是组件类型
    public User user01() {
        User user = new User("张三", 22);
        //proxyBeanMethods=true时，才能正确的从IOC中拿走已存在的ht组件
        //proxyBeanMethods=false时，取得时重新创建的不是IOC容器的那个
        //**这种就叫做组件依赖 即【组件user01依赖于组件ht】
        user.setPet(getPet());
        return user;
    }


    @Bean("ht")
    public Pet getPet() {
        return new Pet("核桃");
    }
}

```

***对应Spring的xml配置文件创建组件：***

```xml
<bean id="user01" class="com.ly.boot.bean.User">
    <property name="name" value="张三" />
    <property name="age" value="22" />
</bean>
```

***SpringBoot从IOC容器中获取组件：***

```java
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        //运行主程序  返回IOC容器，里面包含所有的bean对象
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);
        //获取IOC容器中所有已经创建好的bean
        String[] names = run.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
		
        //默认是单实例singleton，每次获取都是同一个对象
        //从IOC容器中通过id值获取我们创建的组件
        User user = run.getBean("user01", User.class);
        User user1 = run.getBean("user01", User.class);
        System.out.println(user==user1);//true
        
        //,默认也是单实例的
        Configuration bean = run.getBean(Configuration.class);
        System.out.println("获得配置类本身：");
        System.out.println(bean);
        
        
        //如果@Configuration(proxyBeanMethods = true) 最后结果就是true  ,每次都先从IOC容器中先找，找到了就返回。找不到再创建
        //如果@Configuration(proxyBeanMethods = false) 最后结果就是false,每次都是直接重新创建一个新的组件
        User user01 = bean.user01();
        User user011 = bean.user01();
        System.out.println(user01 == user011);
    }
}
```



##### ②、@Bean、@Component、@Controller、@Service、@Repository注解的均可被扫描添加

##### ③、@ComponentScan、@Import

​	1、`@ComponentScan` 和之前一样，指定的包会被扫描

​	2、`@Import`需要写在IOC容器中的组件类上（`配置类或者@Controller类等均可`），其意思就是==当SpringBoot启动时扫描到被`@Import`注解的类时，会导入其指定类（`如：User`）到IOC容器中（利用这些类的无参构造器）==

```java
//例如 放在配置类上
//当SpringBoot启动时扫描到配置类Configuration上@Import注解，就会根据User和NullPointerException类的无参构造器创建对象放在IOC容器中以供使用，创建出组建的名字默认就是全类名
@Import({User.class,NullPointerException.class}) //给容器中自动创建出这两个类型的组件、默认组件的名字就是全类名
@org.springframework.context.annotation.Configuration(proxyBeanMethods = false)
    
public class Configuration {...}
```

​		



























































