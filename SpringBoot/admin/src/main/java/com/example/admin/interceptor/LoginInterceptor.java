package com.example.admin.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * 登录检测
 * 1.配置好拦截器需要拦截哪些请求
 * 2.把这些配置放在容器中
 */
public class LoginInterceptor implements HandlerInterceptor {
    @Override//目标方法执行之前
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session=request.getSession();
        Object loginUser = session.getAttribute("loginUser");
        if(loginUser!=null)//如果登录了就放行
            return true;
        //否则拦截,并跳转到登录界面
        //这里尽量用请求转发，因为前端统一输出，如果用session前端就要${session.msg},与其他${msg}冲突
        request.setAttribute("msg","请先登录");
        request.getRequestDispatcher("/").forward(request,response);
        return false;
    }

    @Override//目标方法执行之后
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override//页面渲染之后
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

    }
}
