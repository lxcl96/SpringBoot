package com.ly.admin.mapperInterface;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ly.admin.bean.User;

/**
 * FileName:UserMapper.class
 * Author:ly
 * Date:2022/9/14 0014
 * Description: 使用mybatis-plus操作数据库
 */


/*
    继承mybatis-plus提供的基类mapper，简化
    Mapper 继承该接口后，无需编写 mapper.xml 文件，即可获得CRUD功能

*/
public interface UserMapper extends BaseMapper<User> {


}
