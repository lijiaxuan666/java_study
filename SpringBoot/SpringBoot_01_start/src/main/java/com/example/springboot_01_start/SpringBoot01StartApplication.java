package com.example.springboot_01_start;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//这是程序的主入口，不能删也不能改   @SpringBootApplication：标注这个类是一个springboot应用
//项目要在这个的同级目录下
@SpringBootApplication
public class SpringBoot01StartApplication {

    public static void main(String[] args) {
        //将springboot应用启动
        //它有返回值，返回的是ConfigurableApplicationContext类型
        //可以用他的getBeanDefinitionNames()方法查看容器内的组件
        SpringApplication.run(SpringBoot01StartApplication.class, args);
    }

}
