package com.example.springboot_02_web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

@Controller
public class RequestController {
    @GetMapping("/goto")
    public String goToPage(HttpServletRequest request){
        request.setAttribute("msg","成功");
        request.setAttribute("code",200);
        return "forward:/success";
    }
    //@RequestAttribute()该注解可以获取请求域中的数据
    //因为这里是请求转发，所以是同一个请求，也可以用request.getAttribute()获取，前提是参数将请求传过来-
    //@responseBody是将controller的方法返回的对象通过适当的转换器转换为指定的格式之后，写入到response对象的body区，通常用来返回JSON数据或者是XML数据。
    @ResponseBody
    @GetMapping("/success")
    public Map success(@RequestAttribute("msg") String msg,
                       @RequestAttribute("code") Integer code,
                       HttpServletRequest request){
        Map<String,Object> map=new HashMap<>();
        map.put("msg",msg);
        map.put("code",code);
        map.put("msg2",request.getAttribute("msg"));
        map.put("code2",request.getAttribute("code"));
        return map;
    }
}
