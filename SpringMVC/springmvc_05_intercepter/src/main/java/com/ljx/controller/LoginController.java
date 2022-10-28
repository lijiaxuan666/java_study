package com.ljx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/user")
public class LoginController {
    @RequestMapping("/goLogin")
    public String goLogin(){
        return "login";
    }
    @RequestMapping("/Login")
    public String login(HttpSession session,String username, String password){

        if(username.equals("ljx666")&&password.equals("1234")){
            session.setAttribute("userLoginInfo",username);
            return "main";
        }else
            return "login";
    }
    @RequestMapping("/main")
    public String main(){
        return "main";
    }
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        //session.invalidate();销毁session，不推荐使用，一般只有浏览器关闭时，session才会销毁，如果不断销毁服务器不断创建，就会有问题
        session.removeAttribute("userLoginInfo");//移除节点
        return "login";
    }
}
