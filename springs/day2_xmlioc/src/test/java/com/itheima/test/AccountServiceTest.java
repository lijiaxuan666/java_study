package com.itheima.test;
//名字写错了，这个是配置文件+注解方法
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.List;

/**
 * 使用Junit单元测试：测试我们的配置
 */
public class AccountServiceTest {
    ApplicationContext ac=null;
    IAccountService as=null;

    @Before
    public void init(){
        //1.获取容易
        ac = new ClassPathXmlApplicationContext("bean.xml");
        //2.得到业务层对象
        as = ac.getBean("accountService",IAccountService.class);
    }

    @Test
    public void testFindAll() {
        //3.执行方法
        List<Account> accounts = as.findAllAccount();
        for(Account account : accounts){
            System.out.println(account);
        }
    }

    @Test
    public void testFindOne() {
        //3.执行方法
        Account account = as.findAccountById(1);
        System.out.println(account);
    }

    @Test
    public void testSave() {
        Account account = new Account();
        account.setName("test");
        account.setMoney(12345f);
        //3.执行方法
        as.saveAccount(account);

    }

    @Test
    public void testUpdate() {
        //3.执行方法
        Account account = as.findAccountById(4);
        account.setMoney(23456f);
        as.updateAccount(account);
    }

    @Test
    public void testDelete() {
        //3.执行方法
        as.deleteAccount(4);
    }
}
