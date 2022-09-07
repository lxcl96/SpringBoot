package com.ly.admin;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

@SpringBootTest
class Boot05WebAdminApplicationTests {
    @Autowired
    private JdbcTemplate jdbcTemplate;


    @Test
    void contextLoads() {
        String sql = "select * from tbl_emp";
        List<Map<String, Object>> maps = jdbcTemplate.queryForList(sql);
        maps.forEach(map -> System.out.println(map));//等同于maps.forEach(System.out::println);

    }

}
