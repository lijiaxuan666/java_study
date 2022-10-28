package com.ljx;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class GetParameter {

    @RequestMapping("/e/h1")
    public String login(String name, Model model){
        model.addAttribute("msg",name);
        return "hello";
    }
}
