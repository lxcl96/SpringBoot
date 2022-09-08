package com.ly.admin;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;
import java.util.List;
import java.util.Map;


@Slf4j
@SpringBootTest
class Boot05WebAdminApplicationTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private DataSource dataSource;

    @Test
    void contextLoads() {
        String sql = "select * from tbl_emp";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        log.info("当前数据源类型为：" + dataSource.getClass());
        maps.forEach(map -> System.out.println(map));//等同于maps.forEach(System.out::println);

    }

}
