package com.example.springboot_02_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.Arrays;

@Controller
public class IndexController {
    @RequestMapping("index")
    public String index(Model model){
        model.addAttribute("msg","Hello,SpringBoot");
        model.addAttribute("users", Arrays.asList("zhangsan", "lisi"));
        return "test";
    }
}
