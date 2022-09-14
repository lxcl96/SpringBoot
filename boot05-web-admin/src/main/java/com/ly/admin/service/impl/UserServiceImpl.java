package com.ly.admin.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ly.admin.bean.User;
import com.ly.admin.mapperInterface.UserMapper;
import com.ly.admin.service.UserService;
import org.springframework.stereotype.Service;

/**
 * FileName:UserServiceImpl.class
 * Author:ly
 * Date:2022/9/14 0014
 * Description:
 */


/**
 * ServiceImpl直接实现IService，减少方法重复实现
 *          只需要指定两个泛型，ServiceImpl<要操作的mapper接口,该mapper接口对应得实体bean>
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    //因为UserServiceImpl实现了UserService，而UserService中有很多预定义的常用crud抽象方法，
    // 直接继承需要实现很多方法，所以mybatis又提供了IService<User>的相应继承类ServiceImpl
}
