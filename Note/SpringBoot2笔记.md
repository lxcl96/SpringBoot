

[TOC]



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

设置Maven配置文件settings.xml设置镜像和jdk版本

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
  <!--
  1、spring-boot-starter-* 就代表Spring官方某种场景
  2、只要引入starter，这个场景的所有常规需要的依赖都会自动引入
  3、*-spring-boot-starter为第三方提供的场景启动器
  4、所有场景启动器starter底层都会依赖spring-boot-starter，这是最基本的starter
  如：pom.xml -->
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
  <properties>
      <mysql.version>5.1.31</mysql.version>
      ...
  </properties>
  
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

+ 自动配置好web常见功能，

  > 如：字符集编码，文件上传，视图解析器等

+ 默认的包结构（当然也可以自己指定）

  > ==主程序所在的包下所有文件及其下面的所有子包里面的组件都会被默认扫描==。如果要自定义包扫描则：`@SpringBootApplication(scanBasePackages = {"com.ly","cn.ly"})`或者 `@ComponentScan(basePackages = {})`
  >
  > ```java
  > //@ComponentScan(basePackages = {})会报错，那就把下面注解分开写
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
  > +- example
  >   +- myapplication # 下面所有组件都会被扫描
  >       +- MyApplication.java # 主程序(使用@SpringBootApplication注解的)
  >       |
  >       +- customer  
  >       |   +- Customer.java
  >       |   +- CustomerController.java
  >       |   +- CustomerService.java
  >       |   +- CustomerRepository.java
  >       |
  >       +- order
  >           +- Order.java
  >           +- OrderController.java
  >           +- OrderService.java
  >           +- OrderRepository.java
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
  >
  > ***配置了所有常用功能：***
  >
  > ![image-20220727114922554](.\img\image-20220727114922554.png)
  >
  > ***如果点进去发现是红色的，或者点不进去的：***说明当前没有启动那个场景，如果需要自己在pom中设置对应场景依赖即可

+ …



### 2、容器功能

#### 2.1、组件添加

指IOC容器中添加Bean，Controller等

##### ①、@Configuration `配置类代替Spring的xml配置文件`

即用@Configuration注解的配置类代替SpringConfig.xml配置文件，一个类就是一个配置文件

> 可以使用@Scope("prototype") 或@Scope("singleton")定义多/单实例

==***Full模式与Lite模式（重点`@Configuration(proxyBeanMethods = true/false)`）***==

+ ***示例***

  ```java
  MyConfiguration bean = run.getBean(MyConfiguration.class);
  System.out.println("获得配置类本身：");
  //根据proxyBeanMethods = true/false 判断是否代理配置类创建bean方法
  User user01 = bean.user01();
  User user011 = bean.user01();
  System.out.println(user01 == user011);
  ```

  > 其实就是判断创建bean的方法，自己调用时是不是也要经过Spring的判断（默认是需要Spring判断）

+ ***最佳实战***
  
  + 配置类组件之间无依赖关系的，用Lite模式`(proxyBeanMethods=false)`。加速容器启动过程，减少判断。
  
    > 此时配置类类型：`com.ly.boot.config.MyConfiguration@286b39c2`
  
  + 配置类组件之间有依赖关系，用Full模式`(proxyBeanMethods=true)`。方法被调用会得到之前的单实例组件。
  
    > 此时配置类类型：`com.ly.boot.config.MyConfiguration$$EnhancerBySpringCGLIB$$12c8f4f1@4525d1d3`

+ ***适应场景：***
  + 组件依赖

***配置类创建组件：***

```java

/**
 * 1、@Configuration 此注解用于告诉SpringIOC该类为一个配置类（对应一个配置文件）,默认也是单实例的
 * 2、proxyBeanMethods：SpringBoot2新特点 字面意思表示“是否代理bean对象的调用方法”
       默认为true：表示使用代理bean对象的调用方法 即每次调用bean对象Configuration的user01方法都会先去IOC容器中找看看是否有，如果有就直接拿；如果没有才会重新创建。总之一句话：true，保持对象组件user01单实例
       设置为false：表示不使用代理bean对象的调用方法 即每次调用bean对象Configuration的user01方法都会创建一个新的对象组件user01（即名字还是user01，但是内存地址变化了，新创建对象替换了）
 
 * 3、由 proxyBeanMethods 引申出SpringBoot的两种配置：
 *      full：全配置  (proxyBeanMethods=true) 每次都先从IOC容器中先找，找到了就返回。找不到再创建
 *          full优点：每次都先从IOC取，没有才创建节约内存资源
 *      lite：轻量级配置 (proxyBeanMethods=false) 每次都是直接重新创建一个新的组件
 *          lite优点：springBoot启动更快，不检测组件在Ioc容器中是否已存在
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
        MyConfiguration bean = run.getBean(MyConfiguration.class);
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

​	2、`@Import`需要写在IOC容器中的组件类上（`配置类或者@Controller类等均可`），其意思就是==当SpringBoot启动时扫描到被`@Import`注解的类时，会导入其指定类（`如：User`）到IOC容器中（利用这些类的无参构造器），ioc容器中该组件的名字默认为全类名==

```java
//例如 放在配置类上
//当SpringBoot启动时扫描到配置类Configuration上@Import注解，就会根据User和NullPointerException类的无参构造器创建对象放在IOC容器中以供使用，创建出组建的名字默认就是全类名
@Import({User.class,NullPointerException.class}) //给容器中自动创建出这两个类型的组件、默认组件的名字就是全类名
@org.springframework.context.annotation.Configuration(proxyBeanMethods = false)
    
public class Configuration {...}
```

##### ④、@Conditional

​	条件装配：满足@Conditional指定条件的，则进行组件注入

![image-20220630094518376](.\img\image-20220630094518376.png)

***条件注解标注在方法上：***

`表示IOC容器满足条件时，才会执行这个方法。如：向容器中注入该bean`

```java
//以ConditionalOnBean为例，如果IOC容器中已经存在User对象且组件名为user01时，才进行注入【即满足 存在bean条件才执行】
@ConditionalOnBean(value = User.class,name = "user01")
@Bean
//用方法名作为组件对象的id，返回类型就是组件类型
public User user01() {
    User user = new User("张三", 22);
    user.setPet(getPet());
    return user;
}
```

***条件注解标注在类上：***

`表示IOC容器满足条件时，才会执行该类下的所有注解方法。如：向容器中注入该配置类下的所有Bean方法对象`

```java
@org.springframework.context.annotation.Configuration(proxyBeanMethods = false)
//如果IOC容器中 没有 名为ht且类型为Pet的对象/组件时，才会执行该配置类Configuration下的方法，自动向IOC容器中注入user01(ht被注释掉了，无法自动注入到IOC容器中)
@ConditionalOnMissingBean(name = "ht",value = Pet.class)
public class Configuration {


    @Bean
    //用方法名作为组件对象的id，返回类型就是组件类型
    public User user01() {
        User user = new User("张三", 22);
        user.setPet(getPet());
        return user;
    }

    //@Bean("ht")
    public Pet getPet() {
        return new Pet("核桃");
    }
}
```

#### 2.2、@ImportResource 原生Spring配置文件引入

​	==作用：将原始的Spring的xml配置文件引入到IOC容器中。==

​	==步骤：只需要将此注解写在随便一个配置类上即可==

如：

```xml
<!-- SpringConfig.xml  -->

<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd">
    <bean id="lisi" class="com.ly.boot.bean.User">
        <property name="name" value="李四" />
        <property name="age" value="33" />
    </bean>
</beans>
```

导入此Spring配置文件

```java
@Configuration
@ImportResource({"classpath:spring/SpringConfig.xml"})//导入SpringConfig.xml
public class AnnotationConfiguration {

    //和这个效果一样
//    @Bean("lisi")
//    public User getUser() {
//        return new User("李四",33);
//    }
}
```

测试

```java
@SpringBootApplication
public class MainApplication {

    public static void main(String[] args) {
        //运行主程序  返回IOC容器，里面包含所有的bean对象
    ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);
        
     //测试是否存在名为  lisi 的组件
     boolean lisiExists = run.containsBean("lisi");
     System.out.println("User用户lisi是否存在？" + lisiExists); //true
    }
}

```

#### 2.3、***配置绑定***

​	如何使用Java读取到properties文件中的内容。并且把它封装到JavaBean中，以供随时使用。

***普通Java方法：***

```java
//创建Properties对象
//创建File文件流读取
//遍历
```

***SpringBoot注解方法***

##### ①、@Component + @ConfiguratonProperties  适用于自己的类

==***此方法创建的组件名，默认是首字母小写（如Car类的car）***==

> 必须导入依赖才能让IDE解析，跳转到对应的setxxx()方法
>
> ```xml
> <dependency>
>     <groupId>org.springframework.boot</groupId>
>     <artifactId>spring-boot-configuration-processor</artifactId>
>     <optional>true</optional>
> </dependency>
> ```

==注意此方法：的properties配置文件必须以application开头的文件。即application*.properties==

***application.properties配置文件：***

```properties
# server.port=8888
spring.servlet.multipart.max-file-size=1MB

# 配置了spring-boot-configuration-processor 且加上@Component 就可以自动跳转到对应set方法
mycar.brand=BYD
mycar.price=10000
```

***直接在Bean上配置该注解，会自动注入属性（该类必须先注册到IOC容器中）：***

```java
/**
 * Car.java
 * 只有容器中的组件才能使用这些注解方法所以必须加上@Component
 */
@Component
//通过spring的属性自动注入，将properties文件中前缀开头的mycar.*类属性注入到IOC容器中Car对象的属性（该对象的名字默认是全类名）
@ConfigurationProperties(prefix = "mycar")//前缀就是properties文件里的前缀
public class Car {
    private String brand;
    private Integer price;
    
    ...
}
    
```

***通过属性自动注入，使用该Car对象***

```java
@RestController //@Response + @Controller
public class HelloController {
    @Autowired //通过spring的对象自动注入，将car对象注入到myCar
    private Car myCar;

    @RequestMapping("/car")
    public Car getCar() {
        return myCar;
    }
    @RequestMapping("/")
    public String helloWorld() {
        return "Hello Spring Boot!";
    }
}
```

***测试：***

springBoot自动集成了json，所以自动转化为json对象了

![image-20220630105144783](.\img\image-20220630105144783.png)



##### ②、@EnableConfigurationProperties + @ConfigurationProperties 适用于第三方类

==***此方法创建的组件名，默认是前缀-全类名（如mycar-com.ly.boot.bean.Car）***==

==注意此方法：的properties配置文件必须以application开头的文件。即application*.properties==

​	对于①中的第二种写法：

​	***第一种写法：***

​			`@Component +  @ConfigurationProperties` 均放在Car类上

​	***第二种写法：***

​			`@EnableConfigurationProperties ` 放在任意一个配置类上（使用了`@Configuration`标注的类）

​			` @ConfigurationProperties` 放在实体类Bean对象上

```java
//AnnotationConfiguration.java 配置类

@Configuration
/**
 * @EnableConfigurationProperties(Car.class)两个作用：
 *  1、开启Car配置绑定功能
 *  2、把这个Car组件自动注册到容器中
 */
@EnableConfigurationProperties(Car.class) //需要制定要开启自动注入properties的类
public class AnnotationConfiguration {

}
```

```java
/**
 * Car.java
 * 只有容器中的组件才能使用这些注解方法所以必须加上@Component,如果有@EnableConfigurationProperties就不需要了
 */
//@Component 使用@EnableConfigurationProperties代替此注解

//通过spring的属性自动注入，将properties文件中前缀开头的mycar.*类属性注入到IOC容器中Car对象的属性（该对象的名字默认是全类名）
@ConfigurationProperties(prefix = "mycar")//前缀就是properties文件里的前缀
public class Car {
    private String brand;
    private Integer price;
    
    ...
}
    
```

### 3、自动配置原理入门

***SpringBoot启动默认加载所以场景，但是最终会根据条件装配规则，按需配置！***

`@SpringBootApplication`由下面三个注解组成：

+ `@SpringBootConfiguration`
+ `@EnableAutoConfiguration`
+ `@ComponentScan`

#### 3.1、引导加载自动配置类

***@SpringBootApplication：主要由以下三部分组成***

+ `@SpringBootConfiguration`
+ `@EnableAutoConfiguration`
+ `@ComponentScan`

***
***@SpringBootConfiguration.class 配置类***

```java
//SpringBootConfiguration.class （配置类）
package org.springframework.boot;
...
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Configuration //配置类注解 说明@SpringBootApplication标注的类（主程序）其实也是一个配置类
@Indexed
public @interface SpringBootConfiguration {
    @AliasFor(
        annotation = Configuration.class
    )
    boolean proxyBeanMethods() default true;
}
```
***@ComponentScan.class 包扫描***

```java
//@ComponentScan就是包扫描，它自定义了排除过虑器 （三个中开启包扫描）

@ComponentScan(
    excludeFilters = {@Filter(
    type = FilterType.CUSTOM,
    classes = {TypeExcludeFilter.class}
), @Filter(
    type = FilterType.CUSTOM,
    classes = {AutoConfigurationExcludeFilter.class}
)}
```
***@EnableAutoConfiguration.class 最主要，处理业务逻辑***

```java
//EnableAutoConfiguration.class 三个中最重要

package org.springframework.boot.autoconfigure;
...

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Inherited
@AutoConfigurationPackage //自动配置包 见下① 
@Import({AutoConfigurationImportSelector.class}) //最重要的 见下②
public @interface EnableAutoConfiguration {
    String ENABLED_OVERRIDE_PROPERTY = "spring.boot.enableautoconfiguration";

    Class<?>[] exclude() default {};

    String[] excludeName() default {};
}
```

> ① *** @EnableAutoConfiguration 内的 @AutoConfigurationPackage***
>
> ```java
> package org.springframework.boot.autoconfigure;
> ...
> @Target({ElementType.TYPE})
> @Retention(RetentionPolicy.RUNTIME)
> @Documented
> @Inherited
> /*
> 导入包Registrar，给容器中进行批量注册bean组件.
> 批量注册的都是我们写的主程序（MainApplication.java）所在包下，及其子包下的所有被注解标注的类 即com.ly.boot下所有类
> */
> @Import({AutoConfigurationPackages.Registrar.class}) 
> public @interface AutoConfigurationPackage {
> String[] basePackages() default {};
> 
> Class<?>[] basePackageClasses() default {};
> }
> ```
>
> 注册主程序所在包(包括子包)下所有组件导入/注册到IOC容器中
>
> ![image-20220630135056922](.\img\image-20220630135056922.png)
>
> ② *** @EnableAutoConfiguration 内的 @Import({AutoConfigurationImportSelector.class})*** 
>
> ***核心方法：***
>
> ```java
> 	protected AutoConfigurationEntry getAutoConfigurationEntry(AnnotationMetadata annotationMetadata) {
> 		if (!isEnabled(annotationMetadata)) {
> 			return EMPTY_ENTRY;
> 		}
>         //获取注解@EnableAutoConfiguration属性值 exclude 和 excludeName
> 		AnnotationAttributes attributes = getAttributes(annotationMetadata);
>         //获取所有等待自动配置的 候选人共144个
> 		List<String> configurations = getCandidateConfigurations(annotationMetadata, attributes);
>         //去掉重复的类名
> 		configurations = removeDuplicates(configurations);
>         //根据属性值 exclude 和 excludeName获取排除的类名，默认为null
> 		Set<String> exclusions = getExclusions(annotationMetadata, attributes);
> 		checkExcludedClasses(configurations, exclusions);
>         //从默认144个自动配置候选人中，删除被排出的exclude 和 excludeName的值
> 		configurations.removeAll(exclusions);
>         //根据当前设置的启动器spring-boot-starter-web，筛选出需要用到的进行自动配置（24个）
> 		configurations = getConfigurationClassFilter().filter(configurations);
>         //开启自动配置导入事件Evenr
> 		fireAutoConfigurationImportEvents(configurations, exclusions);
> 		return new AutoConfigurationEntry(configurations, exclusions);
> 	}
> ```
> ***详细解析：***
>
> + `List<String> configurations = getCandidateConfigurations(annotationMetadata, attributes);`
>
>   > 1、然后调用方法加载所有候选Candidate需要导入的包：`ImportCandidates.load(AutoConfiguration.class, getBeanClassLoader())`
>   >
>   > ```java
>   > public static ImportCandidates load(Class<?> annotation, ClassLoader classLoader) {
>   >     Assert.notNull(annotation, "'annotation' must not be null");
>   >     ClassLoader classLoaderToUse = decideClassloader(classLoader);
>   >     //获取配置文件路径
>   >     String location = String.format("META-INF/spring/%s.imports", annotation.getName());
>   >     Enumeration<URL> urls = findUrlsInClasspath(classLoaderToUse, location);
>   >     ArrayList autoConfigurations = new ArrayList();
>   > 
>   >     while(urls.hasMoreElements()) {
>   >         URL url = (URL)urls.nextElement();
>   >         //把配置文件内容加到 autoConfigurations
>   >         autoConfigurations.addAll(readAutoConfigurations(url));
>   >     }
>   > 
>   >     return new ImportCandidates(autoConfigurations);
>   > }
>   > ```
>   >
>   > 2、然后调用方法，读取注解`@AutoConfiguration.class`所在包`spring-boot-autoconfigure-2.7.1.jar`下的[org.springframework.boot.autoconfigure.AutoConfiguration.imports](org.springframework.boot.autoconfigure.AutoConfiguration.imports)文件，获取144个组件名：
>   >
>   > `String location = String.format("META-INF/spring/%s.imports", annotation.getName());`
>   >
>   > 结果为：META-INF/spring/org.springframework.boot.autoconfigure.AutoConfiguration.imports
>   >
>   > ![image-20220728103235383](.\img\image-20220728103235383.png)
>   >
>   > 
>   >
>   >  

#### 3.2、按需加载上面144个配置类

***SpringBoot启动默认加载所以场景，但是最终会根据条件装配规则，按需配置！***

虽然注解`@AutoConfiguration.class`所在包`spring-boot-autoconfigure-2.7.1.jar`下的[org.springframework.boot.autoconfigure.AutoConfiguration.imports](org.springframework.boot.autoconfigure.AutoConfiguration.imports)文件由144个场景启动的组件类，但是我们不是每个都需要。所以会根据实际配置的场景按需启动。

> ***如我们在pom.xml文件中只配置了`spring-boot-starter-web`的场景启动，则只会按需加载此场景组件***
>
> 原理：
>
> 比如我们需要***amqp**的自动配置，则需要看对应目录下`xxxAutoConfiguration`类，该类上会有个条件
>
> ```java
> @AutoConfiguration
> //仅在指定的RabbitTemplate和Channel类路径存在时，才会自动注入，
> //也就是说我们导入某个场景启动器才会导入该类，则其对应组件才会被自动配置
> @ConditionalOnClass({ RabbitTemplate.class, Channel.class })
> @EnableConfigurationProperties(RabbitProperties.class)
> @Import({ RabbitAnnotationDrivenConfiguration.class, RabbitStreamConfiguration.class })
> public class RabbitAutoConfiguration {
> ```
>
> 如：我们需要***h2***的自动配置，需要满足所有的`@Conditiolal`条件才会导入
>
> ```java
> @AutoConfiguration(after = DataSourceAutoConfiguration.class)
> //需要开启web servlet
> @ConditionalOnWebApplication(type = Type.SERVLET)
> //需要WebServlet类路径存在
> @ConditionalOnClass(WebServlet.class)
> //Environment中需要有指定属性值（Environment中来源就是application.properties配置文件中自己配置的）
> @ConditionalOnProperty(prefix = "spring.h2.console", name = "enabled", havingValue = "true")
> @EnableConfigurationProperties(H2ConsoleProperties.class)
> public class H2ConsoleAutoConfiguration {
> ```
> Property查看***h2***属性，就是配置文件`application*.yml/yaml/properties`中是否有`spring.h2.console`开头的，
>
> 属性名为`enabled`
>
> ![image-20220728112815190](D:\JavaWork\SpringBoot\Note\img\image-20220728112815190.png)
>
> 如：我们pom文件中添加的`spring-boot-starter-web`启动器，默认就是tomcat
>
> ![image-20220728111222031](.\img\image-20220728111222031.png)

#### 3.3、修改默认配置

如：`spring-boot-autoconfigure-2.7.1.jar`包下web/servlet下的`DispatcherServletAutoConfiguration.java`

```java
//给容器中加入名字为multipartResolver的组件
@Bean
//如果容器中有MultipartResolver.class
@ConditionalOnBean(MultipartResolver.class)
//但是容器中没有名字为multipartResolver的组件时
@ConditionalOnMissingBean(name = DispatcherServlet.MULTIPART_RESOLVER_BEAN_NAME)
public MultipartResolver multipartResolver(MultipartResolver resolver) {//自动注入容器中的参数
   // Detect if the user has created a MultipartResolver but named it incorrectly
   return resolver;
}
```

SpringBoot默认会在底层配好所有组件，但是如果用户自己配置了，则用户的优先使用。

```java
//HttpEncodingAutoConfiguration.java
@Bean
@ConditionalOnMissingBean
public CharacterEncodingFilter characterEncodingFilter() {
   CharacterEncodingFilter filter = new OrderedCharacterEncodingFilter();
   filter.setEncoding(this.properties.getCharset().name());
   filter.setForceRequestEncoding(this.properties.shouldForce(Encoding.Type.REQUEST));
   filter.setForceResponseEncoding(this.properties.shouldForce(Encoding.Type.RESPONSE));
   return filter;
}
```

***总结：***

+ 自己配了组件，SpringBoot就用；如果没配，SpringBoot会自动配置 （`xxxAutoConfiguration`）

+ 每个自动配置按照不同的条件(`@Conditional`，也就是对应的场景starter)生效，默认都会有一个properties配置文件绑定值，需要什么属性值就去取

  > ==每个`xxxAutoConfiguration.class`\=\=》依赖`xxxProperties.class`\=\=>绑定`application*.yml/yaml/properties`==
  >
  > SpringBoot中所有配置文件都是在`application*.yml/yaml/properties`中，使用的时候通过前缀prefix来区分
  >
  > ```java
  > @AutoConfiguration
  > @EnableConfigurationProperties(ServerProperties.class)
  > @ConditionalOnWebApplication(type = ConditionalOnWebApplication.Type.SERVLET)
  > @ConditionalOnClass(CharacterEncodingFilter.class)
  > @ConditionalOnProperty(prefix = "server.servlet.encoding", value = "enabled", matchIfMissing = true)
  > public class HttpEncodingAutoConfiguration {...}
  > 
  > //对应的ServerProperties.class
  > @ConfigurationProperties(prefix = "server", ignoreUnknownFields = true)
  > public class ServerProperties {...}
  > 
  > //对应application*.yml/yaml/properties配置文件中的 server开头的配置
  > ```
  > ![image-20220728140345081](.\img\image-20220728140345081.png)

+ 生效的配置类就会给容器中自动注入相应组件，相当于自己配置

  > 如`spring-boot-starter-web`场景就会自动注入dispatcherServlet，characterEncodingFilter等

+ 定制化配置（自己修改对应组件的属性值，只需要修改配置文件即可）

  > **xxxxxAutoConfiguration ---> 组件  --->** **xxxxProperties里面拿值  ----> application.properties**





***

#### 3.4、最佳实践

+ 引入相应的场景依赖

  > springboot官方场景starter：[Developing with Spring Boot](https://docs.spring.io/spring-boot/docs/2.7.1/reference/html/using.html#using.build-systems.starters)

+ 查看自动配置了哪些类（选做）

  * 自己分析源码，引入场景的自动配置哪些生效和不生效

    > 什么xxxstarter对应`spring-boot-autoconfigure-2.7.1.jar`包下对应目录（如：tomcat-->web）

  * 配置文件`application*.yml/yaml/properties`中添加属性 `debug=true`，开启debug启动报告

    > Positive matches：开启的组件，及开启的原因（注入）
    >
    > Negative matches：未开启的组件，及未开启原因（不注入）
    >
    > Exclusions：排除的组件（不注入）
    >
    > Unconditional classes：无条件类（注入）

+ 修改配置

  + 参考官方文档，修改配置文件`application*.yml/yaml/properties`

    > 配置官方文档：[Common Application Properties (spring.io)](https://docs.spring.io/spring-boot/docs/2.7.1/reference/html/application-properties.html#appendix.application-properties)

  + 自己分析不同场景starter下，`spring-boot-autoconfigure-2.7.1.jar`包下对应源码

    > **xxxxxAutoConfiguration ---> 组件  --->** **xxxxProperties里面值  ----> application.properties**

  + 自定义加入组件

    > 配置类中`@Bean,@Import,@Component`等等

  + ***创建自定义器，xxxCustomizer***（后面研究）

  + ......

***

### 4、开发小技巧

#### 4.1、Lombok

简化JavaBean开发

***Lombok使用步骤：***

+ 引入依赖（就在父pom文件中规定好的版本）

  ```xml
  <dependency>
      <groupId>org.projectlombok</groupId>
      <artifactId>lombok</artifactId>
      <version>${lombok.version}</version>
  </dependency>
  ```

+ 安装Lombok插件（小辣椒图标，idea自带），会自动帮我们生成getter和setter方法（当然是编译时生成的）

+ POJO类上加上Lombok的`@Data,@ToString`注解即可

  ```java
  //当然仅在编译时自动生成
  
  @AllArgsConstructor//自动生成所有参数的有参构造器
  @NoArgsConstructor//自动生成无参构造器
  @ToString//自动生成toString方法
  @Data //自动生成getter，setter方法
  @ConfigurationProperties(prefix = "mycar")//前缀就是properties文件里的前缀
  public class Car {
      private String brand;
      private Integer price;
      
  }
  ```

***Lombok常用注解：***

| 注解                | 作用                                               |
| ------------------- | -------------------------------------------------- |
| @Data               | 编译时自动生成getter，setter方法                   |
| @ToString           | 编译时自动生成toString方法                         |
| @NoArgsConstructor  | 编译时自动生成无参构造器                           |
| @AllArgsConstructor | 编译时自动生成所有参数的有参构造器（其余的自己写） |
| @EqualsAndHashCode  | 编译时自动重写equal和hashcode方法                  |
| @Slf4j              | 编译时自动注入一个日志组件                         |

***

#### 4.2、dev-tools

实现服务的热更新，每次更新文件或资源不需要重启服务。

***dev-tools使用步骤：***

+ 导入相应依赖

  ```xml
  <dependencies>
      <dependency>
          <groupId>org.springframework.boot</groupId>
          <artifactId>spring-boot-devtools</artifactId>
          <optional>true</optional>
      </dependency>
  </dependencies>
  ```

+ 如果更新文件或资源直接`Ctrl+F9`或锤子图标编译，dev-tools会自动进行重启服务（简化部署）

+ 如果想要热更新，reload需要付费使用 [JRebel](https://jrebel.com/software/jrebel/)  插件

***

#### 4.3、Spring Initializer(项目初始化向导)

IDEA中新建项目/模块时使用

+ 新建工程或项目

  ![image-20220728163908985](.\img\image-20220728163908985.png)

+ 勾选需要的场景启动器

  ![image-20220728164120041](.\img\image-20220728164120041.png)

***

# 2、SpringBoot核心技术--核心功能

## 2.1、配置文件

***支持的配置文件类型：***

+ `properties文件`
+ `yaml文件`
+ `yml文件`

### ***1、yaml文件原理：***

YAML 是 "YAML Ain't Markup Language"（YAML 不是一种标记语言）的递归缩写。在开发的这种语言时，YAML 的意思其实是："Yet Another Markup Language"（仍是一种标记语言）。 

非常适合用来做以数据为中心的配置文件

### ***2、yaml文件基本语法：***

+ `K: v`，需要注意冒号: 后面有一个空格
+ `大小写敏感`
+ `使用缩进表示层级关系`
+ `缩进不允许使用tab按键，只能使用空格`（idea会自动把tab换成空格）
+ `缩进的空格数不重要，不关心，只要是相同层级的元素左对齐即可`
+ `# 表示注释`
+ `字符串无需加引号，如果非要加记住：''单引号字符串内容会被 转义（意思是单引号和\一样会转义，但实际可能不转义），""双引号表示字符串内容 不转义（意思是双引号和\不一样不会转义，但实际可能转义）`

### ***3、yaml文件数据类型：***

+ ***字面量：***单个的、不可再分的值。如：date，boolean，string，number，null

  ```yaml
  k: v
  ```

+ ***对象：***键值对的集合。如：map，hash，set，object

  ```yaml
  # 行内写法  对象是{} ,里面不需要空格
  k: {k1:v1,k2:v2,k3:v3}
  
  # 或者
  k: 
     k1: v1
     k2: v2
     k3: v3
  ```

+ ***数组：***一组按次序排列的值。如：array，list，queue

  ```yaml
  # 行内写法  数组是[]
  k: [v1,v2,v3]
  
  # 或者
  k: 
     - v1 # -表示一个元素，空格，然后是属性值
     - v2
     - v3
  ```

### ***4、yaml文件使用示例：***

分别绑定properties配置文件和yaml配置文件

***Bean对象类型：***

```java
@Data
@ToString
@Component
//@ConfigurationProperties(prefix = "properties.person")
@ConfigurationProperties(prefix = "yml.person")
public class Person {
    private String userName;
    private Boolean boss;
    private Date birth;
    private Integer age;
    private Pet pet;
    private String[] interests;
    private List<String> animal;
    private Map<String, Object> score;
    private Set<Double> salarys;
    private Map<String, List<Pet>> allPets;
}
```

```java
@Data
@ToString
public class Pet {
    private String name;
    private Double weight;
}
```

***yaml配置文件定义一个Person对象：***

```yaml
yml.person:
# 双引号"" 会将 \n 作为换行输出  
	#（\本来就是【转义】，""双引号代表【不转义】，所以最后总的是 【转义】 输出换行） 
# 单引号'' 会将 \n 作为字符串输出 
	#（\本来就是【转义】，''单引号代表【转义】，所以最后总的是 【不转义】 输出换行） 负负得正
  userName: "张三 \n 李四"
  boss: true
  # 日期写法必须为 / 区分，或者自己在 additional-spring-configuration-metadata.json文件中定义类型
  birth: 1983/12/14
  age: 39
  # 另一个对象
  pet:
    name: 小黄
    weight: 15
  interests: [抽烟,喝酒,烫头]
  animal:
    - 大象
    - 老虎
    - 狮子
  score:
    chinese: 99
    math: 100
    psychological: 13
  salarys: [20000,180000]
  allPets:
    # key 不能为中文
    dongwu:
      - {name: 小黄,weight: 15} # 行内写法 对象
      - name: 小米
        weight: 8
    zhiwu:
      - name: 富贵竹
        weight: 1.5
      - name: 绿萝
        weight: 1


debug: true
```

### ***5、yaml配置文件注意事项：***

+ ==yaml配置文件中key值不能为中文，最好是和变量名那样的规则==

+ ==可以在`resources\META-INF\additional-spring-configuration-metadata.json`文件中自定义属性的数据类型，可以用来解决自动转换格式问题==

  ```json
  {
    "properties": [
      { 
        "name": "yml.person.pet",
        "type": "com.ly.boot.bean.Pet",//指定类型，不然总是当初String类型
        "description": "Description for yml.person.pet."
      }
    ] }
  ```

+ ==所有`application*.yml/yaml/properties`配置文件都会加载，而加载顺序yml > yaml > properties（如果出现相同属性的不同只，最后加载的会覆盖掉前面）==

### ***6、配置文件提示属性功能：***

+ 添加依赖springBoot配置处理器

  ```xml
  <dependency>
      <groupId>org.springframework.boot</groupId>
      <artifactId>spring-boot-configuration-processor</artifactId>
      <optional>true</optional>
  </dependency>
  ```

+ 重新运行一次应用，即可

## 2.2、Web开发

### ***0、SpringBoot下jsp和html混用并打包为jar包***

​	<font color='red'>注意：Springboot默认是不支持jsp的，而且官方也不推荐，所以需要配置一下</font>

####    0、目录结构

![image-20220805111418466](.\img\image-20220805111418466.png)

#### 	1、引入依赖插件，目的：将webapp目录下所有资源放到META-INF/resources下

因为springBoot打好的包没有webapp目录，而且所有的静态资源只从默认的静态资源目录下找`/static, /public, /META-INF/resources, /resources`，但是webapp是和classes类路径同级别的，不是类路径下的，所有只能把webapp目录下所有资源转移到MEAT-INF/resources资源下。

> 如果不加这一步，只创建webapp目录则idea下运行没问题，但是打jar包后运行会报404，找不到资源

```xml
<build>
    <plugins>
        <!-- 1、打jar包依赖-->
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <!--打jar包需要这个版本的依赖-->
            <version>1.4.2.RELEASE</version>
        </plugin>
        <plugin>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-maven-plugin</artifactId>
            <executions>
                <execution>
                    <goals>
                        <goal>repackage</goal>
                    </goals>
                </execution>
            </executions>
        </plugin>
    </plugins>
	
    
    <resources>
       <!--2、不修改springboot的默认静态资源目录-->
        <resource>
            <directory>src/main/resources</directory>
            <includes>
                <include>**/**</include>
            </includes>
            <!-- 开启过滤，用指定的参数替换directory下的文件中的参数 -->
            <filtering>false</filtering>
        </resource>
		<!-- 3、重定向webapp下所所有目录资源存放到META-INF/resources下-->
        <resource>
            <!--指定resources插件处理哪个目录下的资源文件-->
            <directory>src/main/webapp</directory>
            <!--注意此次必须要放在此目录下才能访问到-->
            <targetPath>META-INF/resources</targetPath>
            <includes>
                <include>**/**</include>
            </includes>
        </resource>
    </resources>
</build>
```

#### 	2、创建`src/main/webapp`目录

​	因为jsp默认值只能在webapp这个目录下使用，而springboot默认是没有的，所以自己手动建一个即可。并在里面存放jsp页面！

#### 	3、配置文件`application*.yaml`中配置前缀

```yaml
spring:
    view:
      prefix: /jsp/ # 以webapp为起点
      suffix: .jsp
```

#### 	4、撰写controller

```java
@Controller
@RequestMapping("/model")
public class ModelAttributeController {

    @RequestMapping("/hello")
    public String sayHello() {
        return "hello";
    }
}
```

#### 	5、配置jstl和jsp依赖

***配置jstl和jsp依赖，否则打不开jsp页面只会下载hello.jsp***

```xml
 <!-- 添加 jsp 依赖-->
 <dependency>
     <groupId>org.apache.tomcat.embed</groupId>
     <artifactId>tomcat-embed-jasper</artifactId>
     <scope>provided</scope>
 </dependency>
 <!-- 添加 JSTL 支持 -->
 <dependency>
     <groupId>javax.servlet</groupId>
     <artifactId>jstl</artifactId>
 </dependency>

```

#### 6、测试

访问`http://localhost:8080/model/hello`

![image-20220805112953849](.\img\image-20220805112953849.png)

#### 7、打jar包的目录结构

![image-20220805113442771](.\img\image-20220805113442771.png)

***

### <font color='red'>***< 1、SpringMVC自动配置概览***</font>

官方文档：==修改SpringMVC的组件的默认规则的三种方式：===

> Spring Boot provides auto-configuration for Spring MVC that **works well with most applications.(大多场景我们都无需自定义配置)**
>
> The auto-configuration adds the following features on top of Spring’s defaults:
>
> - Inclusion of `ContentNegotiatingViewResolver` and `BeanNameViewResolver` beans.
>  + <font color='red'>内容协商视图解析器和BeanName视图解析器</font>
> - Support for serving static resources, including support for WebJars (covered [later in this document](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-spring-mvc-static-content))).
>  + <font color='red'>静态资源（包括webjars）</font>
> - Automatic registration of `Converter`, `GenericConverter`, and `Formatter` beans.
>  + <font color='red'>自动注册 `Converter，GenericConverter，Formatter `</font>
> - Support for `HttpMessageConverters` (covered [later in this document](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-spring-mvc-message-converters)).
>  + <font color='red'>支持 `HttpMessageConverters` （后来我们配合内容协商理解原理）</font>
> - Automatic registration of `MessageCodesResolver` (covered [later in this document](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-spring-message-codes)).
>  + <font color='red'>自动注册 `MessageCodesResolver` （国际化用）</font>
> - Static `index.html` support.
>  + <font color='red'>静态index.html 页支持</font>
> - Custom `Favicon` support (covered [later in this document](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-spring-mvc-favicon)).
>  + <font color='red'>自定义 `Favicon`  </font>
> - Automatic use of a `ConfigurableWebBindingInitializer` bean (covered [later in this document](https://docs.spring.io/spring-boot/docs/current/reference/html/spring-boot-features.html#boot-features-spring-mvc-web-binding-initializer)).
>  + <font color='red'>自动使用 `ConfigurableWebBindingInitializer` ，（DataBinder负责将请求数据绑定到JavaBean上）</font>
> 
>If you want to keep those Spring Boot MVC customizations and make more [MVC customizations](https://docs.spring.io/spring/docs/5.2.9.RELEASE/spring-framework-reference/web.html#mvc) (interceptors, formatters, view controllers, and other features), you can add your own `@Configuration` class of type `WebMvcConfigurer` but **without** `@EnableWebMvc`.
> 
>==**1、不用@EnableWebMvc注解。使用** `**@Configuration**` **+** `**WebMvcConfigurer**` **自定义规则**==
> 
>
> 
>If you want to provide custom instances of `RequestMappingHandlerMapping`, `RequestMappingHandlerAdapter`, or `ExceptionHandlerExceptionResolver`, and still keep the Spring Boot MVC customizations, you can declare a bean of type `WebMvcRegistrations` and use it to provide custom instances of those components.
> 
>==**2、声明** `**WebMvcRegistrations**` **改变默认底层组件**==
> 
>
> 
>If you want to take complete control of Spring MVC, you can add your own `@Configuration` annotated with `@EnableWebMvc`, or alternatively add your own `@Configuration`-annotated `DelegatingWebMvcConfiguration` as described in the Javadoc of `@EnableWebMvc`.
> 
>==**3、使用** `**@EnableWebMvc+@Configuration+DelegatingWebMvcConfiguration 全面接管SpringMVC**`==

### ***2、简单功能分析***

#### 2.1、静态资源访问

##### 1、静态资源目录

只要静态资源存放在静态目录下，均可以直接访问。

![image-20220801142247956](.\img\image-20220801142247956.png)

***原理：***静态映射 /**

***访问（默认）：*** ==当前项目根路径/ + 静态资源名==

> `http://localhost:8080/4.png`

***静态目录（类路径下）：***

+ `/static `
+ `/public`
+ `/resources`
+ `/META-INF/resources`

***静态资源直接访问的原理：***

==所有的请求包括静态资源，默认都是先被dispatcherServlet处理的，除非自己修改了默认的MVC配置==

> 如：浏览器访问`http://localhost:8080/1.png`，首先被dispatcherServlet前端控制器接收到，判断有没有对应的映射
>
> + 如果有对应的映射`@RequestMapping`，执行控制器方法，返回什么由实际而定
> + 如果没有对应的映射`@RequestMapping`，直接返回静态资源。（如果静态资源不存在，就报错！）

***官方文档：***

> By default, Spring Boot serves static content from a directory called `/static` (or `/public` or `/resources` or `/META-INF/resources`) in the classpath or from the root of the `ServletContext`. It uses the `ResourceHttpRequestHandler` from Spring MVC so that you can modify that behavior by adding your own `WebMvcConfigurer` and overriding the `addResourceHandlers` method.
>
> In a stand-alone web application, the default servlet from the container is also enabled and acts as a fallback, serving content from the root of the `ServletContext` if Spring decides not to handle it. Most of the time, this does not happen (unless you modify the default MVC configuration), because Spring can always handle requests through the `DispatcherServlet`.
>
> By default, resources are mapped on `/**`, but you can tune that with the `spring.mvc.static-path-pattern` property. For instance, relocating all resources to `/resources/**` can be achieved as follows:

##### ***2、推荐静态资源加上前缀（自定义）***

目的是用于区分动态请求和静态资源，直接放行静态资源不进行过滤和拦截。

```properties
# properties   明确指定/resources下的所有请求为静态资源处理（先被dispatcherServlet处理）
spring.mvc.static-path-pattern=/resources/**
```

```yaml
# yaml 明确指定/resources下的所有请求为静态资源处理（先被dispatcherServlet处理）
spring:
  mvc:
    static-path-pattern: "/resources/**"
```

注：自定义前缀后，所有的静态目录均不会失效 `/static, /public, /META-INF/resources, /resources`

+ 但是无法这样访问`http://localhost:8080/4.png`
+ 必须加上前缀才可以访问，即`http://localhost:8080/resources/4.png`(4个目录下的文件都可以访问到)

##### 3、修改静态资源默认的四个路径

```yaml
spring:
  mvc:
    static-path-pattern: /resources/** # 修改访问静态资源路径必须为resources 开头
  web:
    resources:
      static-locations: classpath:/templates/  # 规定只允许访问类路径下templates目录中的资源
```

注意：无论修改静态访问目录为类路径下哪个路径，`static-path-pattern: /hah/**`时

+ 可以访问满足`/hah`的，在`templates`目录下
+ 同样也可以访问满足`/hah`的，`/META-INF/resources`下目录

总之：==`/META-INF/resources`目录下文件总是能获取到的（需要先满足`static-path-pattern`）==

##### ***4、webJar的支持***

即将前端资源如：js，css等打包成jar包，写成依赖文件。

官网：[WebJars - Web Libraries in Jars](https://www.webjars.org/)

如将jQuery.js打包成jar包，访问方式：`/webjars/**`

```xml
<dependency>
    <groupId>org.webjars</groupId>
    <artifactId>cljs-jquery</artifactId>
    <version>3.5.1</version>
</dependency>
```

![image-20220802102300192](.\img\image-20220802102300192.png)

页面访问webjar：`http://localhost:8080/webjars/jquery/3.5.1/jquery.js`

> 其实还是/META-INF/resources，只不过多了两层目录 
>
> `jquery/3.5.1/jquery.js`如果是bootstrap就换成对应目录，可以看截图

#### 2.2、欢迎页支持

##### 1、将index.html放在静态目录下即可(默认是4个静态目录都行)

```yaml
spring:
#  mvc:
#    static-path-pattern: /public/** # 前缀必须禁用
  web:
    resources:
      static-locations: classpath:/templates/
```

==会受到配置的前缀影响：如果设置了前缀，则必须去掉==

***官方文档：***

> Spring Boot supports both static and templated welcome pages. It first looks for an `index.html` file in the configured static content locations. If one is not found, it then looks for an `index` template. If either is found, it is automatically used as the welcome page of the application.

##### 2、调用控制器方法，匹配路径为`/index`



#### 2.3、自定义Favicon

`favicon.ico`放在静态资源目录中，会自动应用。

```yaml
spring:
#  mvc:
#    static-path-pattern: /public/** # 前缀必须禁用
```

==会受到配置的前缀影响：如果设置了前缀，则必须去掉==

***官方文档：***

> As with other static resources, Spring Boot checks for a `favicon.ico` in the configured static content locations. If such a file is present, it is automatically used as the favicon of the application.

#### 2.4、静态资源配置原理

+ SpringBoot启动默认加载`xxxAutoConfiguration.class`自动配置类

+ SpringMVC功能的默认配置类为：`WebMvcAutoConfiguration.class`，并生效

  > ```java
  > @AutoConfiguration(after = { DispatcherServletAutoConfiguration.class, TaskExecutionAutoConfiguration.class,
  >       ValidationAutoConfiguration.class })
  > @ConditionalOnWebApplication(type = Type.SERVLET)
  > @ConditionalOnClass({ Servlet.class, DispatcherServlet.class, WebMvcConfigurer.class })
  > @ConditionalOnMissingBean(WebMvcConfigurationSupport.class)
  > @AutoConfigureOrder(Ordered.HIGHEST_PRECEDENCE + 10)
  > public class WebMvcAutoConfiguration { ... }
  > ```

+ 给容器中添加相关组件

  > ```java
  > @Bean
  > @ConditionalOnMissingBean(HiddenHttpMethodFilter.class)
  > @ConditionalOnProperty(prefix = "spring.mvc.hiddenmethod.filter", name = "enabled")
  > //给ioc容器中添加处理restful风格的过滤器
  > public OrderedHiddenHttpMethodFilter hiddenHttpMethodFilter() {
  >    return new OrderedHiddenHttpMethodFilter();
  > }
  > 
  > 
  > @Bean
  > @ConditionalOnMissingBean(FormContentFilter.class)
  > @ConditionalOnProperty(prefix = "spring.mvc.formcontent.filter", name = "enabled", matchIfMissing = true)
  > //给ioc容器添加表单内容过滤器
  > public OrderedFormContentFilter formContentFilter() {
  >     return new OrderedFormContentFilter();
  > }
  > ```

  ***配置类内部的配置类：***==将配置文件和类绑定起来，==

  ```java
  @SuppressWarnings("deprecation")
  @Configuration(proxyBeanMethods = false)
  @Import(EnableWebMvcConfiguration.class)
  //application.yaml   
  @EnableConfigurationProperties({ WebMvcProperties.class, WebProperties.class })
  @Order(0)
  public static class WebMvcAutoConfigurationAdapter implements WebMvcConfigurer, ServletContextAware {
  	....
       public WebMvcAutoConfigurationAdapter(..)   
  	....
  }
  ```

+ 配置文件的相关属性和`application*.yaml`属性进行绑定 `WebMvcProperties.class`和`WebProperties.class`

  + ***WebMvcProperties.class***

    ```java
    //和配置文件的spring.mvc前缀进行绑定
    @ConfigurationProperties(prefix = "spring.mvc")
    ```

  + ***WebProperties.class***

    ```java
    //和配置文件的spring.web前缀进行绑定
    @ConfigurationProperties("spring.web")
    ```

+ 给容器中添加需要的组件（只分析此处用到的）

  

  ```java
  public void addResourceHandlers(ResourceHandlerRegistry registry) {
     if (!this.resourceProperties.isAddMappings()) {
        logger.debug("Default resource handling disabled");
        return;
     }
     addResourceHandler(registry, "/webjars/**", "classpath:/META-INF/resources/webjars/");
     addResourceHandler(registry, this.mvcProperties.getStaticPathPattern(), (registration) -> {
        registration.addResourceLocations(this.resourceProperties.getStaticLocations());
        if (this.servletContext != null) {
           ServletContextResource resource = new ServletContextResource(this.servletContext, SERVLET_LOCATION);
           registration.addResourceLocations(resource);
        }
     });
  }
  
  ```

  

***1、`WebMvcAutoConfigurationAdapter.class`配置类只有一个有参构造器***

==如果一个配置类只有一个有参构造器，那么参数默认从容器中找==

> `EnableConfigurationProperties`两个功能：
>
> 1. 和配置文件绑定
> 2. 加入到容器中

```java
/**
     * 只有一个有参构造期,参数默认从ioc容器中取
     * @param webProperties “spring.web”开头的配置文件中的属性
     * @param mvcProperties “spring.mvc”开头的配置文件中的属性
     * @param beanFactory ioc容器
     * @param messageConvertersProvider 消息转换器，提供了@RequestBody和@ResponseBody
     * @param resourceHandlerRegistrationCustomizerProvider 资源处理器的自定义器
     * @param dispatcherServletPath 前端控制器处理陆军
     * @param servletRegistrations  给应用注册原生的servlet，listener和filter时用的
     */
    public WebMvcAutoConfigurationAdapter(WebProperties webProperties, WebMvcProperties mvcProperties,
                                          ListableBeanFactory beanFactory, ObjectProvider<HttpMessageConverters> messageConvertersProvider,
                                          ObjectProvider<WebMvcAutoConfiguration.ResourceHandlerRegistrationCustomizer> resourceHandlerRegistrationCustomizerProvider,
                                          ObjectProvider<DispatcherServletPath> dispatcherServletPath,
                                          ObjectProvider<ServletRegistrationBean<?>> servletRegistrations) {
        this.resourceProperties = webProperties.getResources();
        this.mvcProperties = mvcProperties;
        this.beanFactory = beanFactory;
        this.messageConvertersProvider = messageConvertersProvider;
        this.resourceHandlerRegistrationCustomizer = resourceHandlerRegistrationCustomizerProvider.getIfAvailable();
        this.dispatcherServletPath = dispatcherServletPath;
        this.servletRegistrations = servletRegistrations;
        this.mvcProperties.checkConfiguration();
    }
```

***2、资源处理的默认规则***

```java
@Override
public void addResourceHandlers(ResourceHandlerRegistry registry) {
   if (!this.resourceProperties.isAddMappings()) {
      logger.debug("Default resource handling disabled");
      return;
   }
   addResourceHandler(registry, "/webjars/**", "classpath:/META-INF/resources/webjars/");
   addResourceHandler(registry, this.mvcProperties.getStaticPathPattern(), (registration) -> {
      registration.addResourceLocations(this.resourceProperties.getStaticLocations());
      if (this.servletContext != null) {
         ServletContextResource resource = new ServletContextResource(this.servletContext, SERVLET_LOCATION);
         registration.addResourceLocations(resource);
      }
   });
}

private void addResourceHandler(ResourceHandlerRegistry registry, String pattern, String... locations) {
   addResourceHandler(registry, pattern, (registration) -> registration.addResourceLocations(locations));
}

private void addResourceHandler(ResourceHandlerRegistry registry, String pattern,
      Consumer<ResourceHandlerRegistration> customizer) {
   if (registry.hasMappingForPattern(pattern)) {
      return;
   }
   ResourceHandlerRegistration registration = registry.addResourceHandler(pattern);
   customizer.accept(registration);
    //设置静态资源在浏览器中缓存存放的时间
   registration.setCachePeriod(getSeconds(this.resourceProperties.getCache().getPeriod()));
 // 设置发送给浏览器的缓存标头
    registration.setCacheControl(
    	this.resourceProperties.getCache().getCachecontrol().toHttpCacheControl()
    );
   registration.setUseLastModified(this.resourceProperties.getCache().isUseLastModified());
   customizeResourceHandlerRegistration(registration);
}
```

```yaml
spring:
  mvc:
    static-path-pattern: /public/**
  web:
    resources:
      add-mappings: false # 禁用默认的静态资源目录
      cache:
        period: 11000 # 设置缓存策略：静态资源可被静态资源处理器保存的时间（单位为秒），然后浏览器就会默认把静态资源存放这么长时间缓存，不是每静态文件再从服务器获取
#      static-locations: classpath:/templates/
```



***3、按照默认的静态目录顺序查找欢迎页***

按照默认的静态目录顺序，来查找欢迎页：==只要有index.html就直接返回==

1. classpath:/META-INF/resources/
2. classpath:/resources/
3. classpath:/static/
4. classpath:/public/

```java
WelcomePageHandlerMapping(TemplateAvailabilityProviders templateAvailabilityProviders,
      ApplicationContext applicationContext, Resource welcomePage, String staticPathPattern) {
    //写死了，静态访问路径必须为/**
   if (welcomePage != null && "/**".equals(staticPathPattern)) {
      logger.info("Adding welcome page: " + welcomePage);
      setRootViewName("forward:index.html");
   }
   else if (welcomeTemplateExists(templateAvailabilityProviders, applicationContext)) {
      logger.info("Adding welcome page template: index");
      setRootViewName("index");
   }
}
```

***4、favicon.ico***

浏览器会自动发送 /favicon 请求获取到图标（所有Spring没有对应的处理机制），整个session期间不再获取。

> 如果设置了静态访问路径（前缀），则获取不到静态资源favicon.ico
>
> 浏览器发送：`http://localhost:8080/favicon` 【404】
>
> 正确请求：`http://localhost:8080/前缀/favicon`

### ***3、请求参数处理***

#### 0、请求映射规则

+ `@xxxMapping()`

  > `@RequestMapping,@GetMapping,@PostMapping,@PutMapping,@DeleteMapping`

+ Restful风格支持（使用HTTP请求方式动词来表示对资源的操作）

  > - *以前：**/getUser*  *获取用户*    */deleteUser* *删除用户*   */editUser*  *修改用户*      */saveUser* *保存用户*
  >
  > - *现在： /user*    *GET-**获取用户*    *DELETE-**删除用户*     *PUT-**修改用户*      *POST-**保存用户*
  >
  > - 核心Filter；HiddenHttpMethodFilter
  >
  >   + 用法： 表单method=post，隐藏域 设置参数*_method=put*
  >
  >   - SpringBoot中手动开启
  >
  >     > ```yaml
  >     > spring:
  >     >   mvc:
  >     >     hiddenmethod:
  >     >       filter:
  >     >         enabled: true # 开启默认的HiddenHttpMethodFilter
  >     > ```
  >
  > - 扩展：如何把_method 这个名字换成我们自己喜欢的。

***Controller控制器方法：***

```java
@RequestMapping(value = "/user",method = RequestMethod.GET)
public String getUser(){
    return "GET-张三";
}

@RequestMapping(value = "/user",method = RequestMethod.POST)
public String saveUser(){
    return "POST-张三";
}


@RequestMapping(value = "/user",method = RequestMethod.PUT)
public String putUser(){
    return "PUT-张三";
}

@RequestMapping(value = "/user",method = RequestMethod.DELETE)
public String deleteUser(){
    return "DELETE-张三";
}
```

***WebMvcAutoConfiguration.class***

```java
@Bean
@ConditionalOnMissingBean(HiddenHttpMethodFilter.class)
//默认关闭，需要在配置文件中开启
@ConditionalOnProperty(prefix = "spring.mvc.hiddenmethod.filter", name = "enabled")
public OrderedHiddenHttpMethodFilter hiddenHttpMethodFilter() {
   return new OrderedHiddenHttpMethodFilter();
}
```

***自定义隐藏域参数：***

```java
@Configuration
public class MyConfig {

    @Bean
    public HiddenHttpMethodFilter hiddenHttpMethodFilter() {
        HiddenHttpMethodFilter hiddenHttpMethodFilter = new HiddenHttpMethodFilter();
        hiddenHttpMethodFilter.setMethodParam("_prefix");
        return hiddenHttpMethodFilter;
    }
}
```

***Rest原理（表单提交要使用REST的时候）***

+ 表单提交会带上_method=PUT_
+ _请求过来被HiddenHttpMethodFilter拦截
  + 请求是否正常，并且是POST
    + 获取到_method的值。
    + 兼容以下请求；PUT.DELETE.PATCH
    + 原生request（post），包装模式requesWrapper重写了getMethod方法，返回的是传入的值。
    + 过滤器链放行的时候用wrapper。以后的方法调用getMethod是调用requesWrapper的。

***Rest使用客户端工具***
如PostMan直接发送Put、delete等方式请求，无需Filter。

> 不需要加上_method参数了，postman发送参数直接从http层就改变了请求方式



***请求映射原理：***

> 发送的的请求是怎么匹配到对应的控制器Controller方法上的?

![](.\img\image.png)

SpringMVC功能分析都从 org.springframework.web.servlet.DispatcherServlet-》doDispatch（）

```java
protected void doDispatch(HttpServletRequest request, HttpServletResponse response) throws Exception {
		HttpServletRequest processedRequest = request;
		HandlerExecutionChain mappedHandler = null;
		boolean multipartRequestParsed = false;

		WebAsyncManager asyncManager = WebAsyncUtils.getAsyncManager(request);

		try {
			ModelAndView mv = null;
			Exception dispatchException = null;

			try {
				processedRequest = checkMultipart(request);
				multipartRequestParsed = (processedRequest != request);

				// 找到当前请求使用哪个Handler（Controller的方法）处理
				mappedHandler = getHandler(processedRequest);
                
                //HandlerMapping：处理器映射。/xxx->>xxxx
```

核心方法：获取路径映射关系

```java
protected HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception {
    //包含所有的映射关系 -- 处理器映射
   if (this.handlerMappings != null) {
  	  //遍历处理器映射，根据地址和请求方法 查找对应的处理器handler
      for (HandlerMapping mapping : this.handlerMappings) {
          //找到对应的handler，并与拦截器一起包装成 一个执行链
         HandlerExecutionChain handler = mapping.getHandler(request);
         if (handler != null) {
            return handler;
         }
      }
   }
   return null;
}
```

![image-20220803110051319](.\img\image-20220803110051319.png)

上图其中：`RequestMappingInfoHandlerMapping`映射专门用于处理`@RequestMapping`注解的，里面包含我们使用`@RequestMapping`关联的控制器方法

![image-20220803110545940](.\img\image-20220803110545940.png)

> ***所有的请求映射都在HandlerMapping中。***
>
> + SpringBoot自动配置欢迎页（welcome）的 WelcomePageHandlerMapping 。访问 /能访问到index.html；
>
> + SpringBoot自动配置（`WebMvcAutoConfiguration.java`）了默认 的 RequestMappingHandlerMapping
>
> +  请求进来，挨个尝试所有的HandlerMapping看是否有请求信息。
>     ○ 如果有就找到这个请求对应的handler
>     ○ 如果没有就是下一个 HandlerMapping
>
> + ==总之，如果我们需要一些自定义的映射处理，我们也可以自己给容器中放HandlerMapping（`配置类中加自定义 HandlerMapping`）。==
>
>   > ```java
>   > @Bean
>   > //类似这样
>   > public WelcomePageHandlerMapping welcomePageHandlerMapping(ApplicationContext applicationContext,
>   >       FormattingConversionService mvcConversionService, ResourceUrlProvider mvcResourceUrlProvider) {
>   >    WelcomePageHandlerMapping welcomePageHandlerMapping = new WelcomePageHandlerMapping(
>   >          new TemplateAvailabilityProviders(applicationContext), applicationContext, getWelcomePage(),
>   >          this.mvcProperties.getStaticPathPattern());
>   >    welcomePageHandlerMapping.setInterceptors(getInterceptors(mvcConversionService, mvcResourceUrlProvider));
>   >    welcomePageHandlerMapping.setCorsConfigurations(getCorsConfigurations());
>   >    return welcomePageHandlerMapping;
>   > }
>   > ```

#### 1、普通参数与基本注解

##### 1.1、注解方式获取参数

`@PathVatiable、@RequestHeader、@ModelAttribute、@RequestParam、@MatrixVariable、@CookieValue、@RequestBody`

+ <font color='red '> `@PathVatiable` </font> 用于restful风格请求路径变量

  ```java
  /**
   * 请求链：http://localhost:8080/car/3/owner/lisi?age=18&inters=basketball&inters=game
   * 注解@PathVariable的两种 获取restful风格参请求路径变量的用法：
   *      1、根据参数名获取
   *      2、封装到一个map集合中，类型必须为Map<String,String>
   * @param id 参数id
   * @param username  参数 username
   * @param pv 参数集合
   * @return 集合
   */
  @RequestMapping("/car/{id}/owner/{username}")
  public Map<String,Object> getCar(@PathVariable("id") Integer id,
                                   @PathVariable("username") String username,
                                   @PathVariable Map<String,String> pv) {
      HashMap<String, Object> map = new HashMap<>();
      map.put("id",id);
      map.put("username",username);
      map.put("map",pv);
      return map;
  }
  ```

+ <font color='red '>`@RequestHeader`</font> 获取请求头信息（浏览器的KV值信息）

  ```java
  /**
   * 请求链：http://localhost:8080/header
   * 注解@RequestHeader获取请求头属性的3种用法
   *  1、根据请求头属性名获取
   *  2、封装到一个map集合中，类型必须为Map<String,String>
   *  3、封装到HttpHeaders类中
   * @param host 请求头属性
   * @param acceptEncoding  请求头属性
   * @param headers 请求头集合
   * @param httpHeaders 请求头封装到HttpHeaders中
   * @return map集合
   */
  @RequestMapping("/header")
  public Map<String,Object> getHeader(@RequestHeader("host") String host,
                                      @RequestHeader("Accept-Encoding") String acceptEncoding,
                                      @RequestHeader Map<String,String> headers,
                                      @RequestHeader HttpHeaders httpHeaders){
      HashMap<String, Object> map = new HashMap<>();
      map.put("host",host);
      map.put("Accept-Encoding",acceptEncoding);
      map.put("headers",headers);
      map.put("httpHeaders",httpHeaders);
      return map;
  }
  ```

+ <font color='red '>`@ModelAttribute`</font>  将值直接封装到model中，返回给view层调用

  ==首先说明一下，被`@ModelAttribute`注解的方法会在`Controller`每个方法执行之前都执行，如果有返回值，则自动将该返回值加入到ModelMap中。因此对于一个`Controller`中包含多个URL的时候，要谨慎使用。==

  待写------------------

  + ***① 标注在方法上***

    + 标注的方法无返回值

      

    + 标注的方法有返回值

  + ***②  标注在参数上***

  + ***③ 标注在`@RequestMapping`标注的方法上***

  

+ <font color='red '>`@RequestParam`</font> 获取请求参数 (?后面的)

  ```java
  /**
   * 请求链：http://localhost:8080/RequestParam?age=18&inters=basketball&inters=game
   * 注解@RequestParam获取请求参数的3种用法：
   *  1、根据参数名获取单个参数值（默认require=true则必须要有此参数，否则服务会报错）
   *  2、根据参数名获取多个参数值（默认require=true则必须要有此参数，否则服务会报错）
   *      ① 定义为 String[]，属性值保存在数组中
   *      ②定义为 String，属性值保存在String中，逗号分隔
   *  3、所有请求参数封装到一个map集合中，类型必须为Map<String,String> 【注意此方法对应复合属性只会保存一个值】
   * @param age 请求参数age
   * @param inters 请求参数inters
   * @param interesting  请求参数inters
   * @param params  所有的请求参数集合
   * @return map集合
   */
  @RequestMapping("/RequestParam")
  public Map<String,Object> getRequestMapping(@RequestParam("age") Integer age,
                                              @RequestParam("inters") String[] inters,
                                              @RequestParam("inters") String interesting,
                                              //复合属性只会保存一个值，不会用逗号,拼接
                                              @RequestParam Map<String,String> params){
      HashMap<String, Object> map = new HashMap<>();
      map.put("age",age);
      map.put("inters",inters);
      map.put("interesting",interesting);
      map.put("params",params);
      return map;
  }
  ```

+ <font color='yellow'>`@MatrixVariable `</font> 获取请求路径中的矩阵变量

  在请求路径（？前面的，不包含请求参数）中，用分号 ; 来分割一个路径的条件

  > 如：`https://localhost:8080/boss/1;age=20/2;age=30` 想要查找id为1，年龄大于20的boss，以及id为2，年龄大于30的员工
  >
  > 分号 ; 代表了一个路径中的条件

  ***先理解矩阵变量的使用场景：***

  正常情况下每个浏览器和服务器会有一个唯一的session，名字为jsession。每次发送请求时会把cookie带上发送给服务器。session也被当作cookie一起发送过去。

  1、如果禁用了cookie，那么如何获取session域中的数据？

  > url重写：`http://localhost:8080/abc;jsesssionid=xxxx `把cookie的值使用矩阵变量的方式进行传递.

  

  2、矩阵变量的使用场景

  > ​	绝大多数都是放在请求的最后，用分号 ; 分隔，传递session

​		3、矩阵变量写法

>   * `/cars/sell;low=34;brand=byd,audi,yd` （复合属性逗号分隔）
>   * `/cars/sell;low=34;brand=byd;brand=audi;brand=yd `（复合属性，单独写）
> 		* `/boss/1;age=20/2;age=10` （放在请求地址中，作为判断条件restful风格）

***用法：***

==*1、修改SpringMVC的自动配置，开启矩阵变量支持*==

因为SpringMVC自动配置默认是关闭矩阵变量，代码如下：

```java
/*
 自动配置类WebMvcAutoConfiguration.java --》 
	WebMvcAutoConfigurationAdapter.java（内部类） --》
		configurePathMatch()（所有的url路径处理，均是此处配置的）--》
			UrlPathHelper.removeSemicolonContent = true（删除url中的分号;内容）
    
*/
```

所以我们需要修改组件的属性，开启矩阵支持（分号 ;）两种方法：==[SpringMVC自动配置概览中的方法1]==

```java
// @Configuration + WebMvcConfigurer 会自动覆盖掉自动配置中的相应配置
@Configuration(proxyBeanMethods = false)
public class MyConfig implements WebMvcConfigurer {
    @Override
    //方法1：SpringMVC自动配置概览中修改组件属性的方法1
    public void configurePathMatch(PathMatchConfigurer configurer) {
        UrlPathHelper urlPathHelper = new UrlPathHelper();
        //去掉 默认去掉请求链接中分号 ; 的规则,即开启矩阵变量功能
        urlPathHelper.setRemoveSemicolonContent(false);

        configurer.setUrlPathHelper(urlPathHelper);
    }

    @Bean//直接给ioc容器中添加 WebMvcConfigurer组件
    //方法2：SpringMVC自动配置概览中修改组件属性的方法1
    public WebMvcConfigurer webMvcConfigurer() {
        //直接返回接口的匿名内部类
       return new WebMvcConfigurer() {
           @Override
           public void configurePathMatch(PathMatchConfigurer configurer) {
               UrlPathHelper urlPathHelper = new UrlPathHelper();
               //去掉 默认去掉请求链接中分号 ; 的规则,即开启矩阵变量功能
               urlPathHelper.setRemoveSemicolonContent(false);

               configurer.setUrlPathHelper(urlPathHelper);
           }
       };

    }
}
```

==***2、分号 ; 必须放在restful风格下使用 {xxx}，否则会报错***==

请求链1：`http://localhost:8080/cars/sell;low=34;brand=byd,audi,yd`

请求链2：`http://localhost:8080/cars/sell;low=34;brand=byd;brand=audi;brand=yd`

均对应controller方法：`@RequestMapping("/cars/{path}")`

![image-20220804170033466](.\img\image-20220804170033466.png)

```java
/**
 * 获取请求链中的矩阵变量属性值（地址中 ;分割的）
 * 矩阵变量放在请求路径中的，则必须是restful风格 {xxx}
 * 注意：springBoot默认禁用掉了矩阵变量的功能：自动配置WebMvcAutoConfiguration --configurePathMatch()--UrlPathHelper.removeSemicolonContent = true(去掉url的分号;部分)
 *      手动开启：
 *          1、配置类中自己手动给ioc容器中添加一个WebMvcConfigurer类型的组件（@Bean方法）
 *          2、配置类实现WebMvcConfigurer接口，重写configurePathMatch()方法
 *
 * @param low 矩阵变量1
 * @param brands 矩阵变量2
 * @param variables 矩阵变量集合
 * @return map集合
 */
@RequestMapping("/cars/{path}")
public Map<String,Object> getMatrixVariable(@MatrixVariable("low") Integer low,
                                            @MatrixVariable("brand") String brands,
                                            @MatrixVariable Map<String, String> variables,
                                            //获取restful的真实路径
                                            @PathVariable("path") String path) {

    HashMap<String, Object> map = new HashMap<>();
    map.put("low",low);
    map.put("brand", brands);
    map.put("variables", variables);
    map.put("path", path);
    return map;
}
```

请求链3：`http://localhost:8080/boss/1;age=20/2;age=10`

![image-20220804171440720](.\img\image-20220804171440720.png)

```java
@RequestMapping("/boss/{bossId}/{empId}")
public Map<String,Object> boss(@MatrixVariable(value = "age",pathVar = "bossId") Integer bossAge,
                               //具有相同名字的矩阵变量，通过pathVar消除歧义
                               @MatrixVariable(value = "age",pathVar = "empId") String empAge,
                               @PathVariable("bossId") Integer bossId,
                               @PathVariable("empId") Integer empId) {

    HashMap<String, Object> map = new HashMap<>();
    map.put("empId",empId);
    map.put("bossId", bossId);
    map.put("empAge", empAge);
    map.put("bossAge", bossAge);
    return map;
}
```

***注意：***

> + ==必须手动开启矩阵变量解析（分号 ;）==
> + ==必须使用restful的路径变量{xxx}，才能正确解析矩阵路径变量（分号 ;）==

+ <font color='red '>`@CookieValue`</font> 获取cookie信息（注意获取不到session的信息）

  ```java
  /**
       * 请求链：
       * 注解@CookieValue获取请求cookie的值的2种方法：
       *  1、根据cookie名，获取对应cookie值
       *  2、根据cookie名，获取对应cookie对象
       *  注意：一定要写cookie名，就算只有一个cookie
       * @param cookieValue 通过cookie名获取对应cookie值
       * @param cookie 通过cookie名获取对应cookie对象
       * @return map集合
       */
  @RequestMapping("/cookie")
  public Map<String,Object> getCookieValue(@CookieValue("Idea-ac70e2d0") String cookieValue,
                                           @CookieValue("_ga") Cookie cookie){
      HashMap<String, Object> map = new HashMap<>();
      map.put("ideaCookie",cookieValue);
      map.put("_ga", cookie);
      return map;
  }
  ```

  > 创建cookie，然后通过response发送给浏览器
  >
  > ```java
  > @RequestMapping("/createCookie")
  > public String createCookie(HttpServletResponse response){
  >     //cookie中不允许有空格
  >     Cookie cookie = new Cookie("_ga", "there-do-not-allow-space");
  >     cookie.setComment("cookie create test.");
  >     cookie.setMaxAge(36000);//依据格林时间
  >     cookie.setPath("/");
  >     cookie.setHttpOnly(true);
  >     cookie.setDomain("localhost");
  >     
  >     //返回给客户端
  >     response.addCookie(cookie);
  >     return "index";
  > }
  > ```

+ <font color='red '>`@RequestBody`</font>  获取请求体信息（只有post请求才有，是请求的参数即?后面的）

  ```java
  /**
   * 请求链：http://localhost:8080/requestBody?age=18&inters=basketball&inters=game
   * 注解@RequestBody获取post请求的请求体（即？后面的参数）
   * @param body 参数链
   * @return map集合
   */
  @RequestMapping("/requestBody")
  public Map<String,Object> getRequestBody(@RequestBody String body){
      HashMap<String, Object> map = new HashMap<>();
      map.put("body",body);
      return map;
  }
  ```

+ <font color='red'>`@RequestAttribute`</font> 获取request域属性值attribute

  ```java
  /**
   * 获取请求域的两个方法
   *  1、使用@RequestAttribute注解，必须写上name属性
   *  2、使用原生的request请求获取
   * @param value request域k1对应的属性值
   * @param request request请求
   * @return map集合
   */
  @ResponseBody
  @RequestMapping("/success")
  public Map<String, Object> getRequestAttribute(@RequestAttribute("k1") String value,
                                                HttpServletRequest request) {
      HashMap<String, Object> map = new HashMap<>();
      map.put("k2",request.getAttribute("k2"));
      map.put("k1", value);
      return map;
  }
  ```

##### 1.2、Servlet API

`WebRequest、ServletRequest、MultipartRequest、 HttpSession、javax.servlet.http.PushBuilder、Principal、InputStream、Reader、HttpMethod、Locale、TimeZone、ZoneId`

==***全部都是`ServletRequestMethodArgumentResolver.class`解析***==

```java
// ServletRequestMethodArgumentResolver.class
@Override
public boolean supportsParameter(MethodParameter parameter) {
   Class<?> paramType = parameter.getParameterType();
   return (WebRequest.class.isAssignableFrom(paramType) ||
         ServletRequest.class.isAssignableFrom(paramType) ||
         MultipartRequest.class.isAssignableFrom(paramType) ||
         HttpSession.class.isAssignableFrom(paramType) ||
         (pushBuilder != null && pushBuilder.isAssignableFrom(paramType)) ||
         (Principal.class.isAssignableFrom(paramType) && !parameter.hasParameterAnnotations()) ||
         InputStream.class.isAssignableFrom(paramType) ||
         Reader.class.isAssignableFrom(paramType) ||
         HttpMethod.class == paramType ||
         Locale.class == paramType ||
         TimeZone.class == paramType ||
         ZoneId.class == paramType);
}
```

##### 1.3、复杂参数Map和Model

**Map**、**Model（map、model里面的数据默认会被放在request的请求域  request.setAttribute）、**Errors/BindingResult、**RedirectAttributes（ 重定向携带数据）**、**ServletResponse（response携带数据）**、SessionStatus、UriComponentsBuilder、ServletUriComponentsBuilder



==***Map，Model，ModelMap等都相当于给request域存放数据***==

```java
/**
 * 测试复杂参数，及model和map默认会给request域存放数据 等价于request.setAttribute
 * @param map map集合
 * @param model model
 * @param request request请求
 * @param response  response响应
 * @return 返回值
 */
@GetMapping("/params")
public String testParam(Map<String,Object> map,
                        Model model, //model和map其实是同一个对象
                        HttpServletRequest request,
                        HttpServletResponse response) {
    //先判断有没有数据
    System.out.println(map);
    System.out.println(model.asMap());

    //存放值
    map.put("hello","world666");
    model.addAttribute("world","hello666");
    request.setAttribute("message","helloWorld");

    response.addCookie(new Cookie("c1", "v1"));
    return "forward:/success";
}
```

***原理：参数解析器 argumentResolvers+ 返回值处理器returnValueHandlers***

> ***1、参数解析器argumentResolvers***
>
> + 参数`Map`是被`MapMethodProcessor.class`解析，==所以model和map其实是同一个对象==
>
>   > 底层实际是`ModelMap`，所以参数可以用`ModelMap`代替
>   >
>   > ```java
>   > //ModelAndViewContainer#getModel(..);
>   > private final ModelMap defaultModel = new BindingAwareModelMap();//底层就是ModelMap
>   > ...
>   > if (useDefaultModel()) {
>   >     return this.defaultModel;//就是上面的
>   > }
>   > ```
>
> + 参数`Model`是被`ModelMethodProcessor.class`解析，==所以model和map其实是同一个对象==
>
>   > 底层实际是`ModelMap`，所以参数可以用`ModelMap`代替
>   >
>   > ```java
>   > //ModelAndViewContainer#getModel(..);
>   > private final ModelMap defaultModel = new BindingAwareModelMap();//底层就是ModelMap
>   > ...
>   > if (useDefaultModel()) {
>   >     return this.defaultModel;//就是上面的
>   > }
>   > ```
>   >
>   > ![image-20220809141309469](.\img\image-20220809141309469.png)
>
> + 参数`request`是被`ServletRequestMethodArgumentResolver`解析
>
> + 参数`response`是被`ServletResponseMethodArgumentResolver.class`解析
>
> ***2、返回值处理器returnValueHandlers***
>
> ```java
> /*
> 	1、DispatcherServlet#doDispatch(..);
> 	2、DispatcherServlet#processDispatchResult(..); //执行完拦截器后进行最后的视图渲染工作
> 	3、DispatcherServlet#render(..); 
> 	4、AbstractView#render(..);
> 	5、InternalResourceView#renderMergedOutputModel(..);//没有配置模板引擎，则默认都是内部资源视图解析器InternalResourceViewResolver处理的
> 	6、AbstractView#exposeModelAsRequestAttributes(..);//最终暴露/存放在request域中
> 
> */
> protected void exposeModelAsRequestAttributes(Map<String, Object> model,
>       HttpServletRequest request) throws Exception {
> //底层原理将model，或map的数据存放到request域中
>    model.forEach((name, value) -> {
>       if (value != null) {
>          request.setAttribute(name, value);
>       }
>       else {
>          request.removeAttribute(name);
>       }
>    });
> }
> ```
>
> ***返回值处理器：支持返回值类型的格式***
>
> ```java
> if (this.returnValueHandlers != null) {
>    invocableMethod.setHandlerMethodReturnValueHandlers(this.returnValueHandlers);
> }
> ```
>
> ![image-20220808151401368](.\img\image-20220808151401368.png)

##### 1.4、自定义对象参数（POJO类，普通JavaBean）

```html
<form action="/saveuser" method="post">
    姓名： <input name="userName" value="zhangsan"/> <br/>
    年龄： <input name="age" value="18"/> <br/>
    生日： <input name="birth" value="2019/12/10"/> <br/>
    <!--级联赋值 -->
    宠物姓名：<input name="pet.name" value="阿猫"/><br/>
    宠物年龄：<input name="pet.age" value="5"/> <br/>
<!--    宠物： <input name="pet" value="啊猫,3"/> <br/>-->
    <input type="submit" value="保存"/>
</form>
```

```java
@ResponseBody
@PostMapping("/saveuser")
public Person testPojoParam(Person person) {
    System.out.println(person);
    return person;
}
```

***原理：参数解析器 argumentResolvers***

> ***1、参数解析器支持类型判断supportsParameter()***
>
> + 参数`Person`即普通JavaBean是被`ServletModelAttributeMethodProcessor.class`解析的
>
> ```java
> /*
> 	注意参数解析器argumentResolvers中会有两个ServletModelAttributeMethodProcessor组件
> 	1、this.annotationNotRequired = false 专门用于处理@ModelAttribute注解
> 	2、this.annotationNotRequired = true，用于处理非简单参数类型
> */
> @Override
> 
> //支持的数据类型
> public boolean supportsParameter(MethodParameter parameter) {
>    return (parameter.hasParameterAnnotation(ModelAttribute.class) ||
>          (this.annotationNotRequired && !BeanUtils.isSimpleProperty(parameter.getParameterType())));
> }
> 
> //所有的简单类型如void，Enum，CharSequence，Number，Date，Temporal，URI，URL，Locale，Class
> public static boolean isSimpleValueType(Class<?> type) {
>         return Void.class != type 
>             	&& Void.TYPE != type 
>             	&& (ClassUtils.isPrimitiveOrWrapper(type) 
>                 || Enum.class.isAssignableFrom(type) 
>                 || CharSequence.class.isAssignableFrom(type) 
>                 || Number.class.isAssignableFrom(type) 
>                 || Date.class.isAssignableFrom(type) 
>                 || Temporal.class.isAssignableFrom(type) 
>                 || URI.class == type 
>                 || URL.class == type 
>                 || Locale.class == type 
>                 || Class.class == type);
>     }
> ```
>
> ***2、参数解析器解析参数resolveArgument()***
>
> + 创建Person bean实例，利用的是无参构造器
>
>   ```java
>   attribute = createAttribute(name, parameter, binderFactory, webRequest);
>   ```
>
> + 由binder工厂创建一个binder数据绑定器，进行数据绑定(利用里面超多的类型转换器进行转换)
>
>   ```java
>   WebDataBinder binder = binderFactory.createBinder(webRequest, attribute, name);
>   ```
>
> + 经过层层包装，然后循环遍历需要绑定的值集合，进行类型转化（将String转化为需要的类型）
>
>   ```java
>   //AbstractNestablePropertyAccessor#processLocalProperty(..)
>   valueToApply = this.convertForProperty(tokens.canonicalName, oldValue, originalValue, ph.toTypeDescriptor());
>   
>   //转化的设计思想和参数解析器argumentResolvers解析参数完全一样
>   //1、先从缓存取 适合的类型转化器
>   //2、取不到，就循环遍历看看是否支持将 【当前类型】转换为 【目标类型】
>   //3、找到了加入缓存中
>   ```
>
> + 将转化完成的值，通过反射绑定到Person属性上
>
>   ```java
>   ph.setValue(valueToApply);
>   ```
>
> ![image-20220809163620157](\img\image-20220809163620157.png)
>
> 注：此处的转换器convert，就是前面**< 1、SpringMVC自动配置概览***中提到的

#### 2、POJO封装过程（自定义转换器convert）

不通过级联赋值，使用新的接口赋值方法：

```html
<form action="/saveuser" method="post">
    姓名： <input name="userName" value="zhangsan"/> <br/>
    年龄： <input name="age" value="18"/> <br/>
    生日： <input name="brith" value="2019/12/10"/> <br/>
<!--    宠物姓名：<input name="pet.name" value="阿猫"/><br/>-->
<!--    宠物年龄：<input name="pet.age" value="5"/> <br/>-->
    宠物： <input name="pet" value="啊猫,3"/> <br/>
    <input type="submit" value="保存"/>
</form>
```

则此时提交到控制器handler，必须会报错（因为没有响应的转换器convert）

```java
@ResponseBody
@PostMapping("/saveuser")
public Person testPojoParam(Person person) {
    System.out.println(person);
    return person;
}
```

> ![image-20220810141518323](img\image-20220810141518323.png)

***解决方法：自定义转换器***

依旧重写convert接口进行类型转化，配置类继承WebMvcConfigurer接口，向ioc容器添加转化器。

***1、自定义转换器convert，处理自定义的数据***

```java
public class MyConvert implements Converter<String, Pet> {
    /**
     * 专门来处理 pet=阿猫,3 这样的数据封装到Person.pet中，即【名字,年龄】
     * @param source 前端传递来的字符串（名字）
     * @return 封装好的pet
     */
    @Override
    public Pet convert(String source) {
        //简单的处理
        String[] sourceArr = source.split(",");
        String name = sourceArr[0]!=null?sourceArr[0].trim():"";
        String age = sourceArr[1]!=null?sourceArr[1].trim():"";
        return new Pet(name,age);
    }
}
```

***2、将自定义的转换器convert，添加到ioc容器中***

```java
@Configuration(proxyBeanMethods = false)
public class MyConfig implements WebMvcConfigurer {
 
 	@Override
    public void addFormatters(FormatterRegistry registry) {
        //当然也可以通过自动注入
        registry.addConverter(new MyConvert());
    }
}
```

***3、直接使用即可***

***

#### 3、普通注解参数处理原理

==***HandlerMethodArgumentResolver接口的所有实现类都默认支持一种参数解析，所以实际可用的参数获取方法也是这些***==

![image-20220808161735648](\img\image-20220808161735648.png)

> 针对`@PathVariable,@RequestHeader,@RequestParam,@CookieValue,@RequestBody,@MatrixVariable`等。
>
> 以：`http://localhost:8080/car/{id}/owner/{username}`为例
>
> ***核心函数方法：`DispatcherServlet#doDispatch()`***
>
> + `mappedHandler = getHandler(processedRequest);`获取到请求对应的处理器方法`ParameterTestController#getCar(..)`
>
> + `HandlerAdapter ha = getHandlerAdapter(mappedHandler.getHandler());`获取处理器适配器Adapter，通过此适配器才可以调用控制器方法`getCar(..)`
>
>   ![](\img\image-20220808140744448.png)
>
>   > 注：继承`AbstractHandlerMethodAdapter`抽象类的，我们可以自定义Adapter来处理特定方法。
>
> + `mv = ha.handle(processedRequest, response, mappedHandler.getHandler());`通过上面获取的适配器Adapter调用控制器方法`getCar(...)`
>
> + 进入函数内部，执行`mav = invokeHandlerMethod(request, response, handlerMethod);`
>
>   > `RequestMappingHandlerAdapter`类
>
> + 设置参数解析器
>
>   ```java
>   //RequestMappingHandlerAdapter#invokeHandlerMethod()
>   if (this.argumentResolvers != null) {
>      invocableMethod.setHandlerMethodArgumentResolvers(this.argumentResolvers);
>   }
>   ```
>
>   ![image-20220808150232410](.\img\image-20220808150232410.png)
>
>   ***参数解析器HandlerMethodArgumentResolver实现的接口及解析过程：***
>
>   ```java
>   public interface HandlerMethodArgumentResolver {
>     
>      /*
>       supportsParameter()判断是否支持指定参数的解析
>       如果支持
>       resolveArgument()解析参数
>       */
>      boolean supportsParameter(MethodParameter parameter);
>         
>      @Nullable
>      Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
>            NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception;
>     
>   }
>   ```
>
>   
>
> + 封装好相应的组件，开始解析调用`invocableMethod.invokeAndHandle(webRequest, mavContainer);`
>
>   ```java
>   //真正执行控制器方法：1、获取到解析后的参数 2、执行控制器方法
>   Object returnValue = invokeForRequest(webRequest, mavContainer, providedArgs);
>   	//里面1、获取到解析后的参数
>   	Object[] args = getMethodArgumentValues(request, mavContainer, providedArgs);
>   	  
>   	//里面2、执行控制器方法
>   	return doInvoke(args);
>   ```
>
>   + ***里面1、获取到解析后的参数***
>
>     ```java
>     protected Object[] getMethodArgumentValues(NativeWebRequest request, @Nullable ModelAndViewContainer mavContainer,
>           Object... providedArgs) throws Exception {
>       //获取方法参数的包装类
>        MethodParameter[] parameters = getMethodParameters();
>        if (ObjectUtils.isEmpty(parameters)) {
>            //如果没有 直接返回
>           return EMPTY_ARGS;
>        }
>         
>        Object[] args = new Object[parameters.length];
>        for (int i = 0; i < parameters.length; i++) {
>           MethodParameter parameter = parameters[i];
>           parameter.initParameterNameDiscovery(this.parameterNameDiscoverer);
>           args[i] = findProvidedArgument(parameter, providedArgs);
>           if (args[i] != null) {
>              continue;
>           }
>                
>            /*
>            HandlerMethodArgumentResolver接口的两步骤：
>            		1、supportsParameter 是否支持
>            		2、resolveArgument 解析
>            */
>            //判断支持27中参数解析器是否支持当前参数，不支持直接异常 
>            //【里面有缓存机制】先用缓存判断是否可以解析，如果不行再从27个中遍历
>           if (!this.resolvers.supportsParameter(parameter)) {
>              throw new IllegalStateException(formatArgumentError(parameter, "No suitable resolver"));
>           }
>           try {
>               //支持，就去遍历获取解析 【方法内部见下面】
>              args[i] = this.resolvers.resolveArgument(parameter, mavContainer, request, this.dataBinderFactory);
>           }
>           catch (Exception ex) {
>              if (logger.isDebugEnabled()) {
>                 String exMsg = ex.getMessage();
>                 if (exMsg != null && !exMsg.contains(parameter.getExecutable().toGenericString())) {
>                    logger.debug(formatArgumentError(parameter, exMsg));
>                 }
>              }
>              throw ex;
>           }
>        }
>         //返回解析后的参数
>        return args;
>     }
>     ```
>
>     ```java
>     	public Object resolveArgument(MethodParameter parameter, @Nullable ModelAndViewContainer mavContainer,
>     			NativeWebRequest webRequest, @Nullable WebDataBinderFactory binderFactory) throws Exception {
>     		//获取参数解析器  同上面的this.resolvers.supportsParameter(parameter)
>     		HandlerMethodArgumentResolver resolver = getArgumentResolver(parameter);
>     		...
>                     
>             //正式解析 [普通的请求参数如@PathVariable，是被UrlPatchHelper解码请求链地址，并把参数放在request域中，直接取request域取值]
>     		return resolver.resolveArgument(parameter, mavContainer, webRequest, binderFactory);
>     	}
>     ```
>

### ***4、响应数据与内容协商***

![image-20220810150001330](img\image-20220810150001330.png)



#### 1、响应JSON实现

##### 	1、使用`JackSon + @ResponseBody`自动封装

```xml
<!-- spring-boot-starter-webh场景启动器内部配置json启动器-->
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-starter-json</artifactId>
  <version>2.7.2</version>
  <scope>compile</scope>
</dependency>
```

##### 	2、HTTPMessageConverter响应原理

即返回值Json数据的响应原理：*返回值解析器*，在进行参数解析前会把参数解析器`argumentResolvers`和返回值处理器`returnValueHandlers`配置好。

![image-20220810152418967](img\image-20220810152418967.png)

> 说明可以和参数解析器argumentResolvers，类型转换器convert一样自己配置







#### 2、









### ***5、视图解析与模板引擎***





### ***6、拦截器***





### ***7、跨域***



## 2.3、数据访问



## 2.4、单元测试



## 2.5、指标监控



## 2.6、原理解析

















































































































































