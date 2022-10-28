package com.example.admin.controller;

import com.example.admin.pojo.User;
import com.example.admin.pojo.UserInfo;
import com.example.admin.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Date;

@Controller
public class IndexController {

    @Autowired
    IUserService userService;

    //用/或者/login都可以访问这个页面
    @GetMapping(value = {"/","/login"})
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String main(User user, HttpSession session, Model model){
        String password=userService.findUser(user.getUsername());
        if(password==null){
            model.addAttribute("msg","该用户未注册");
            return "login";
        }
        if(password.equals(user.getPassword())){//登陆成功
            //把登录成功的用户保存起来
            session.setAttribute("loginUser",user);
            //每次刷新页面，表单都会提交一次，为防止表单重复提交可以使用重定向
            return "redirect:/main.html";
        }else{
            model.addAttribute("msg","账号密码错误");
            return "login";
        }
    }
    @GetMapping("/main.html")//首页
    public String mainPage(HttpSession session,Model model){
            return "main";
    }

    /** 跳转注册页面
     * 遇到问题：报错Request method 'POST' not supported
     *      没有在拦截器放行注册页面，导致无法跳转
     */
    @RequestMapping("/register")
    public String register(HttpSession session,Model model){
            return "registration";
    }

    /** 保存注册信息
     * 遇到问题：报错Request method 'GET' not supported
     *      由于登录界面是Get方法，跳转login页面控制台会报错，所以用重定向
     */
    @PostMapping("/saveUser")
    public String saveUser(UserInfo userInfo,Model model,
                           @RequestParam("password") String password){
        User user=new User(userInfo.getUsername(),password);
        userService.saveUser(user);
        userInfo.setInsert_time(new Date());
        userService.saveUserInfo(userInfo);
        return "redirect:/login";
    }
}
