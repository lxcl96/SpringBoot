package com.ly.boot.config;

import com.ly.boot.bean.Pet;
import com.ly.boot.bean.User;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @FileName:Configuration.class
 * @Author:ly
 * @Date:2022/6/29
 * @Description: 使用@Configuration注解代替spring的xml文件
 */

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
 *  7、@Import注解
 *      当SpringBoot启动时扫描到配置类Configuration上@Import注解，就会根据User和NullPointerException类的无参构造器创建对象放在IOC容器中以供使用，创建出组建的名字默认就是全类名
 */
@Import({User.class,NullPointerException.class}) //给容器中自动创建出这两个类型的组件、默认组件的名字就是全类名
@Configuration(proxyBeanMethods = false)
@ConditionalOnMissingBean(name = "ht",value = Pet.class)
public class MyConfiguration { //该类本身也是一个组件，在IOC中可以取到


    //以ConditionalOnBean为例，如果IOC容器中已经存在User对象且组件名为user01时，才进行注入【即满足 存在bean条件才执行】
    //@ConditionalOnMissingBean(value = User.class,name = "user01")
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
