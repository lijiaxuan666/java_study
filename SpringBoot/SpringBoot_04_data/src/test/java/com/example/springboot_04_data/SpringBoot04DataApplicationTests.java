package com.example.springboot_04_data;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;

@SpringBootTest
class SpringBoot04DataApplicationTests {

    @Autowired
    JdbcTemplate jdbcTemplate;
    @Test
    void contextLoads() {
        String name;
        String sql="select name from stu where id = 1";
        //这里一直显示sql语句有问题，其实是url需要加上时区
        name=jdbcTemplate.queryForObject(sql,String.class);
        System.out.println(name);
    }

}
