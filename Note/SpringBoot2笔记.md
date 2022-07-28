

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

+ 























































































































































