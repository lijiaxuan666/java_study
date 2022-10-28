package com.example.admin.config;

import com.example.admin.interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 1.编写一个拦截器实现HandlerInterceptor接口
 * 2.拦截器注册到容器中
 * 3.指定拦截规则（如果/**拦截所有，静态资源也会被拦截，应添加放行）
 */
@Configuration
public class MyMvcConfigurer implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //将自己写的拦截器添加注册
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/**")//这个是添加需要拦截哪些,"/**"这个会拦截所有请求，包括静态资源
                .excludePathPatterns("/","/login","/register","/saveUser","/css/**","/fonts/**","/js/**","/images/**");
                //这个是添加不拦截哪些，登录界面应该是任何情况下都能访问的，静态资源也不应该拦截，所以不拦截
    }
}
