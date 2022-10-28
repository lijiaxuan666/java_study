package com.ljx.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/t1")
    public String test1(){
        System.out.println("-------");
        return "OK";
    }
}
