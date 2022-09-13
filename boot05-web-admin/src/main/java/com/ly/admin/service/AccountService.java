package com.ly.admin.service;

import com.ly.admin.bean.Account;
import com.ly.admin.mapperInterface.AccountMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * FileName:AccountService.class
 * Author:ly
 * Date:2022/9/13 0013
 * Description:
 */
@Service
public class AccountService {

    @Autowired //只要AccountMapper 有@Mapper注解，且在主程序所在的包下则会被自动扫描添加到容器中
//    private SqlSession sqlSession;
    private AccountMapper accountMapper;


    public Account getAccountById( Long id) {
//        AccountMapper accountMapper = sqlSession.getMapper(AccountMapper.class);
        return accountMapper.getAccountById(id);
    }
}
