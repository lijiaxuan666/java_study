package com.itheima.test;
//名字写错了，这个是配置文件方式
import com.itheima.domain.Account;
import com.itheima.service.IAccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 使用Junit单元测试：测试我们的配置
 */

/**
 * 当一个类添加了注解@Component,那么他就自动变成了一个bean，就不需要再Spring配置文件中显示的配置了。
 * 把这些bean收集起来通常有两种方式，Java的方式和XML的方式。
 * 当这些bean收集起来之后，当我们想要在某个测试类使用@Autowired注解来引入这些收集起来的bean时，
 * 只需要给这个测试类添加@ContextConfiguration注解来标注我们想要导入这个测试类的某些bean。
 *
 * XML文件方式：
 *   第一种：
 *       在xml文件内加入下面语句，就会自动扫描com这个包内的bean
 *       <!-- 自动扫描该包 -->
 *       <context:component-scan base-package="com" />
 *   第二种：
 *     @ContextConfiguration括号里的locations = {"classpath*:/*.xml"}
 *     就表示将class路径里的所有.xml文件都包括进来，那么刚刚创建的那么XML文件就会包括进来，那么里面自动扫描的bean就都可以拿到了，
 *     此时就可以在测试类中使用@Autowired注解来获取之前自动扫描包下的所有bean
 *
 *  classpath和classpath*区别:
 *         classpath：只会到你的class路径中查找找文件。
 *         classpath*：不仅包含class路径，还包括jar文件中（class路径）进行查找。
 *
 *  Java方式：
 *     如果使用Java的方式就会很简单了，我们就不用写XML那么繁琐的文件了，我们可以创建一个Java类来代替XML文件，
 *     只需要在这个类上面加上@Configuration注解,然后再加上@ComponentScan注解就开启了自动扫描，
 *     如果注解没有写括号里面的东西，@ComponentScan默认会扫描与配置类相同的包。
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class AccountServiceTest {

    @Autowired
    private  IAccountService as;

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
