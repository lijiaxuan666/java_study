//自己写的事务使用注解方式控制
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import first.service.IAccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class test {
    @Autowired
    private IAccountService as;

    @Test
    public void testtransfer(){
        as.transfer("aaa","bbb",100f);
    }
    /*成功经验：(配置文件方式改为注解方式)
        1.在bean.xml文件中添加context的约束
        2.添加约束后在下面指定要扫描的包
        3.在每个类前面加上@Compent("xxx")指定它的唯一标识
        4.对要注入的变量上面加上@Autowired，由于类型都不同，所以无需@Qualifier来指定名称
        5.删除每个类中原本用配置文件注入的set方法
        6.删除bean.xml文件中的注入语句
        7.对于要开启通知的，先定义一个方法来作为表达式
            @Pointcut("execution(* first.service.*.*(..)")
            public void pt1(){}
        8.在需要开启前置后置等的方法上加上通知注解，注意括号里面字符串pt1()是带括号的
        9.在bean.xml中开启spring对注解aop的支持
     */
}
