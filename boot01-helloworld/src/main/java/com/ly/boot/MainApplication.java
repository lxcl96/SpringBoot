package com.ly.boot;

import com.ly.boot.bean.Pet;
import com.ly.boot.bean.User;
import com.ly.boot.config.Configuration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

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
        //运行主程序  返回IOC容器，里面包含所有的bean对象
        ConfigurableApplicationContext run = SpringApplication.run(MainApplication.class, args);
        //获取IOC容器中所有已经创建好的bean
        String[] names = run.getBeanDefinitionNames();
        for (String name : names) {
            System.out.println(name);
        }
//
//        //默认是单实例singleton，每次获取都是同一个对象
//        //从IOC容器中通过id值获取我们创建的组件
//        User user = run.getBean("user01", User.class);
//        User user1 = run.getBean("user01", User.class);
//        System.out.println(user == user1);
//
//        //bean运行类型为：Spring增强的代理类 com.ly.boot.config.Configuration$$EnhancerBySpringCGLIB$$c5827ff4@3ff57625
//        Configuration bean = run.getBean(Configuration.class);
//        System.out.println("获得配置类本身：");
//        System.out.println(bean);
//
//        //如果@Configuration(proxyBeanMethods = true) 最后结果就是true  ,每次都先从IOC容器中先找，找到了就返回。找不到再创建
//        //如果@Configuration(proxyBeanMethods = false) 最后结果就是false,每次都是直接重新创建一个新的组件
//
//        User user01 = bean.user01();
//        User user011 = bean.user01();
//        System.out.println(user01 == user011);
//
//
//        /*  单实例模式
//            组件依赖
//                proxyBeanMethods=true时，用户的宠物就是IOC容器中的宠物  输出结果为true
//                proxyBeanMethods=false时，用户的宠物不是IOC容器中的宠物 输出结果为false
//
//         */
//        User userDependency = run.getBean("user01", User.class);
//        Pet petDependency = run.getBean("ht", Pet.class);
//        //proxyBeanMethods=true-->true
//        //proxyBeanMethods=false-->false
//        System.out.println("用户的组件宠物是不是IOC容器中的组件宠物：" + (userDependency.getPet() == petDependency));

        //测试是否存在名为  lisi 的组件
        boolean lisiExists = run.containsBean("lisi");
        System.out.println("User用户lisi是否存在？" + lisiExists); //true
    }
}
