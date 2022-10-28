package com.example.springboot_01_start.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

//SpringBoot自动装配:原理！
@RestController
public class HelloController {
    @RequestMapping("/hello")
    public String hello(){
        return "hello,world";
    }
}
