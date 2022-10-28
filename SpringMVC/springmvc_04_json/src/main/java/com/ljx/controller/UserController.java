package com.ljx.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ljx.dao.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

//@RestController这个注解表示这个类里所有方法都不会走视图解析器，会直接返回字符串，下面方法就不用加注解了
@Controller  //（和RestController只能存在一个）
public class UserController {
    //最好是在配置文件中统一配置解决乱码问题，这样所有接口都不会出现乱码问题
    //同时RequestMapping中提供了produces属性用来修改字符集，但这种方式每个需要解决乱码问题的接口都需要配置，比较麻烦

    @RequestMapping("/j1")
    @ResponseBody //加上这个注解他就不会走视图解析器，会直接返回一个字符串
    public String json1(){
        User user=new User("李佳璇",19,"男");
        return user.toString();//自己写的toString方法
    }

    @RequestMapping("/j2")
    @ResponseBody
    public String json2() throws JsonProcessingException {
        User user=new User("李佳璇",19,"男");
        ObjectMapper mapper=new ObjectMapper();
        String str=mapper.writeValueAsString(user);
        return str;//json字符串
    }

    @RequestMapping("/j3")
    @ResponseBody
    public String json3() throws JsonProcessingException {
        List<User> list=new ArrayList<>();
        list.add(new User("李佳璇",19,"男"));
        list.add(new User("李昊",18,"男"));
        list.add(new User("侯卓倩",19,"女"));

        return new ObjectMapper().writeValueAsString(list);//json字符串
    }
}
