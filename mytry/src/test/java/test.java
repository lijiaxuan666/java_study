import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.IAccountService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:bean.xml")
public class test {
    @Autowired
    private IAccountService as;

    @Test
    public void testtransfer(){
        as.transfer("bbb","aaa",100f);
    }
    /*成功经验：
        1.先创建account账户对象
        2.创建持久层IAccountDao接口，在实现类中实现功能
        3.需要runner(要添加commons-dbutils依赖)，和connectionUtil(自定义的获取连接功能类)
        4.编写工具类connectionUtil，在其中获取资源dataSource来获取连接，使用线程ThreadLocal存储每个连接
        5.在业务层调用持久层，并且实现转账功能
        6.添加测试类，需要添加spring-test依赖
        自行增加：
        7.在bean.xml文件中加入aop约束，使用aop的环绕通知
        8.创建工具类Logger作为通知管理事务
        9.对service包下的所有类的所有方法进行增强，即让其都拥有logger的事务管理，就不用service每个方法都加入事务控制代码，使其更简洁
     */
}
