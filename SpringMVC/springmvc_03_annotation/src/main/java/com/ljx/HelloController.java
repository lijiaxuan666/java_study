package com.ljx;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/*和Controller效果相同的还有：
* @Component    组件
* @Service     service层
* @Controller  controller
* @Repository  dao层
* */

@Controller//代表这个类会被Spring接管
//被注解的这个类中的所有方法，如果返回值是String，并且有具体页面可以跳转，那么就会被视图解析器解析
public class HelloController {
    //注解方式可以在一个类中通过多个方法来实现多个Controller，
    //实现Controller接口方式只能创建多个类，并在springmvc-servlet.xml文件中将自己的类交给SpringIOC容器，注册bean
    @RequestMapping("/hello")
    public String hello(Model model){
        //封装数据
        model.addAttribute("msg","Hello SpringMVC");
        return "hello";//会被视图解析器处理
    }

    @RequestMapping("/hello2")
    public String hello2(Model model){
        //封装数据
        model.addAttribute("msg","李佳璇牛逼！");
        return "hello";//返回值就是jsp界面
    }

    //原来的格式：   http://localhost:8080/add?a=1&b=2
    //RestFul风格： http://localhost:8080/add?a/b
    @RequestMapping("/add")
    public String hello3(int a,int b,Model model){
        int res=a+b;
        model.addAttribute("msg","结果为"+res);
        return "hello";
    }
    //需要使用@PathVariable注解将值映射到URL里
    @RequestMapping("/add2/{a}/{b}")
    public String hello4(@PathVariable int a,@PathVariable int b, Model model){
        int res=a+b;
        model.addAttribute("msg","结果为"+res);
        return "hello";
    }

    @RequestMapping("/redirect")
    public String hello5(Model model){
        //转发
        return "hello";
        //重定向：需要注意的是，重定向不需要视图解析器，不然路径会被拼接为一个其他路径
        //return "redirect:/index.jsp";
    }
}
