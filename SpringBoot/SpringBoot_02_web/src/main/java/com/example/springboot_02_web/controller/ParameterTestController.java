package com.example.springboot_02_web.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class ParameterTestController {
    //@PathVariable()他是获取路径变量上的值，根据路径变量上的值自动变化。它还可以将所有的k，v自动放入Map集合中，但都必须为String类型
    //@RequestHeader()获取请求头中的值，它也有一个Map可以获取所有请求头
    //@RequestParam()获取请求参数，它也有一个Map可以获取所有请求参数
    //@CookieValue()获取cookie的值，也可以获取整个cookie
    @GetMapping("/car/{id}/owner/{username}")
    public Map<String,Object> getCar(@PathVariable("id") Integer id,
                                     @PathVariable("username") String name,
                                     @PathVariable Map<String,String> pv,
                                     @RequestHeader Map<String,String> headers){
        Map<String,Object> map=new HashMap<>();
        map.put("id",id);
        map.put("username",name);
        map.put("pv",pv);
        map.put("headers",headers);
        return map;
    }

    //@RequestBody()获取请求体，只有post方法才有请求体，一般用来获取表单内容
    @PostMapping("/save")
    public Map<String,Object> postmethod(@RequestBody String body){
        Map<String,Object> map=new HashMap<>();
        map.put("body",body);
        return map;
    }


    //@MatrixVariable获取矩阵变量，一般在cookie被禁用时使用，写在url中，以分号分割，
    //1、语法： 请求路径：/cars/sell;low=34;brand=byd,audi,yd
    //2、SpringBoot默认是禁用了矩阵变量的功能,需要自己开启
    //      手动开启：原理。对于路径的处理。UrlPathHelper进行解析。
    //              removeSemicolonContent（移除分号内容）支持矩阵变量的
    //3、矩阵变量必须有url路径变量才能被解析
    @GetMapping("/cars/{path}")
    public Map carsSell(@MatrixVariable("low") Integer low,
                        @MatrixVariable("brand") List<String> brand,
                        @PathVariable("path") String path){
        Map<String,Object> map = new HashMap<>();
        map.put("low",low);
        map.put("brand",brand);
        map.put("path",path);//可以看到输出为sell，也就是说，分号之后的是不会被保存在路径中
        return map;
    }

    // /boss/1;age=20/2;age=10
    //如果有重名，可以指定是哪个路径下的变量
    @GetMapping("/boss/{bossId}/{empId}")
    public Map boss(@MatrixVariable(value = "age",pathVar = "bossId") Integer bossAge,
                    @MatrixVariable(value = "age",pathVar = "empId") Integer empAge){
        Map<String,Object> map = new HashMap<>();
        map.put("bossAge",bossAge);
        map.put("empAge",empAge);
        return map;
    }
}
