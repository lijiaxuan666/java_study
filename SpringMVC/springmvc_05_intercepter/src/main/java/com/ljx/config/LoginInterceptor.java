package com.ljx.config;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if(request.getSession().getAttribute("userLoginInfo")!=null){
            //登录成功，则session域中就会有userLoginInfo，则放行
            System.out.println("用户已登录，放行");
            return true;
        }
        if(request.getRequestURI().contains("Login")){//RequestMapping中只要包含Login字符串就会放行
            System.out.println("用户前往登录界面，放行");
            //如果访问登录界面，也可以放行
            return true;
        }
        //其他情况将会拦截
        System.out.println("用户未登录，已被拦截，进入登录界面");
        request.getRequestDispatcher("/WEB-INF/jsp/login.jsp").forward(request,response);
        return false;
    }
}
