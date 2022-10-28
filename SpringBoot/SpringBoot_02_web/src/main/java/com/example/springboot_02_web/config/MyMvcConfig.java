package com.example.springboot_02_web.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.PathMatchConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.util.UrlPathHelper;

//如果要扩展SpringMVC，可以自己写个配置类继承WebMvcConfigurer接口，重写里面的方法
/*
* @Configuration表明它是一个配置类:
*   配置类里面使用@Bean标注在方法上给容器注册组件，默认也是单实例的
*   外部无论对配置类中的这个组件注册方法调用多少次，获取的都是之前注册容器的单实例
*   配置类本身也是组件
*   它里面有个属性proxyBeanMethods默认为true，是代理bean的方法:
*       Full(proxyBeanMethods = true)那么代理对象调用方法，SpringBoot总会检查这个组件是否在容器中有（即保持组件单实例）
*       Lite(proxyBeanMethods = false)每次调用，都会创建新的对象，用来解决组件依赖
*   如果我们只是注册组件而不依赖的话，推荐修改为false，这样加载更快，因为如果为true，每次调用都会检测
*
* @Import({})参数是个数组
*   该注解可以给容器中自动创建出数组中指定类型的组件
*
* @Conditional注解是根注解，其下派生了许多注解
*   条件装配：满足Conditional指定的条件，则进行组件注入（即里面的值为true）
*
* @ImportResource("classpath:xxxx.xml")注解用于导入Spring的配置文件，让配置文件里面的内容生效
*   Spring Boot里面没有Spring的配置文件，我们自己编写的配置文件，也不能自动识别，此时可以用该注解将其加载到spring容器中
*   注意！这个注解是放在主入口函数的类上，而不是测试类上
*
* 不能使用@EnableWebMvc这个注解!!!
*   因为他导入的DelegatingWebMvcConfiguration这个类，他的父类是WebMvcConfigurationSupport
*   而WebMvcAutoConfiguration这个类自动装配前会判断是否有WebMvcConfigurationSupport这个类，
*   如果有，自动装配就会失效（官方文档加粗提示）
*
* @ConfigurationProperties(prefix = "xxx")配置绑定
*   若要加载某配置文件（.properties 或 .yaml），可以在该类上写上该注解，prefix属性是用来绑定类里面的属性与配置文件中指定前缀的同名属性
*   它将配置文件中的每一个属性值都映射到这个文件中
*   注意：**还需要加上Component注解，将其加入spring容器中，因为只有在容器中的组件，才能有springboot的功能**
*
* @EnableConfigurationProperties(xxx.class)里面的参数是指定的类
*   如果是第三方提供的类，无法添加Component注解，可以在自己的配置类上添加该注解
*   他有两个功能：
*       1.开启xxx这个类的配置绑定功能
*       2.把xxx这个组件自动注册到spring容器中
*/
@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Override
    public void configurePathMatch(PathMatchConfigurer configurer){
        // 是否存在尾\来进行匹配  /user和/user/等效的，同样可以进行匹配
        //configurer.setUseTrailingSlashMatch(true);
        UrlPathHelper urlPathHelper=new UrlPathHelper();
        urlPathHelper.setRemoveSemicolonContent(false);
        configurer.setUrlPathHelper(urlPathHelper);
    }
}
