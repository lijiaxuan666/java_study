package com.example.springboot_01_start;

import com.example.springboot_01_start.pojo.People;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

//单元测试
@SpringBootTest
class SpringBoot01StartApplicationTests {

    @Autowired
    private People people;

    @Test
    void contextLoads() {
        System.out.println(people);
    }

}
