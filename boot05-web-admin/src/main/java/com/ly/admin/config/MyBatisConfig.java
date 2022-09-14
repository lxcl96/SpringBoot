package com.ly.admin.config;

import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FileName:MyBatisConfig.class
 * Author:ly
 * Date:2022/9/14 0014
 * Description:
 */

@Configuration
public class MyBatisConfig {
    /**
     * 配置类中给ioc容器添加分页拦截器插件 （类似于mybatis的pageHelper）
     * @return 分页拦截器
     */
    @Bean
    public MybatisPlusInterceptor paginationInterceptor() {
        MybatisPlusInterceptor mybatisPlusInterceptor = new MybatisPlusInterceptor();
        PaginationInnerInterceptor paginationInnerInterceptor = new PaginationInnerInterceptor();
        //请求超过实际页数，true返回首页，false继续请求
        paginationInnerInterceptor.setOverflow(true);
        //设置最大单页限制数量，默认500条， -1不限制
        paginationInnerInterceptor.setMaxLimit(200L);
        mybatisPlusInterceptor.addInnerInterceptor(paginationInnerInterceptor);

        return mybatisPlusInterceptor;
    }
}
