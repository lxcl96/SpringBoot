package com.ly.admin.mapperInterface;

import com.ly.admin.bean.Account;
import org.apache.ibatis.annotations.Mapper;

/**
 * FileName:AccountMapper.class
 * Author:ly
 * Date:2022/9/13 0013
 * Description:
 */
@Mapper
public interface AccountMapper {

    /**
     * 根据账户id，获取账户信息
     * @param id id
     * @return 账户类
     */
    public Account getAccountById(Long id);
}
