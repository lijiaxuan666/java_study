//Spring的xml依赖注入方法
package com.itheima.ui;

import com.itheima.service.IAccountService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 模拟一个表现层，用于调用业务层
 */
public class Client {

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        //1.获取核心容器对象
        ApplicationContext ac = new ClassPathXmlApplicationContext("bean.xml");
        //2.根据id获取Bean对象
        //IAccountService as  = (IAccountService)ac.getBean("accountService");
        //as.saveAccount();

//        IAccountService as  = (IAccountService)ac.getBean("accountService2");
//        as.saveAccount();

        IAccountService as  = (IAccountService)ac.getBean("accountService3");
        as.saveAccount();

    }
}
