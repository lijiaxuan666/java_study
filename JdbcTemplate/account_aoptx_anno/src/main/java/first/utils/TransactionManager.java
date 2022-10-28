package first.utils;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;
@Component("transactionManager")
public class TransactionManager {
    @Autowired
    private ConnectionUtil connectionUtil;

    @Pointcut("execution(* first.service.*.*(..)")
    public void pt1(){}

    //@Before("pt1()")
    public void begin(){
        try {
            connectionUtil.creatConnection().setAutoCommit(false);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //@AfterReturning("pt1()")
    public void commit(){
        try {
            connectionUtil.creatConnection().commit();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //@AfterThrowing("pt1()")
    public void rollback(){
        try {
            connectionUtil.creatConnection().rollback();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
    //@After("pt1()")
    public void realse(){
        try {
            connectionUtil.creatConnection().close();
            connectionUtil.removeConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    /*  在运行时候，由于是顺序执行，按照上面方法写的话，执行顺序应该是：
    *       前置-->最终-->后置/异常
    *   这样就导致事务还没提交就已经关闭，这样就会出现异常并且转账也没成功
    *
    *   所以为了解决这个问题，应该使用环绕通知:
    *       记得去掉上面的方法的注解
    *
    *   但是不知道为什么，我的程序用上面方法并没有出现问题，可以运行成功
    * */
    @Around("pt1()")
    public Object aroundAdvice(ProceedingJoinPoint pjp)  {
        Object rtValue=null;
        try{
            Object []args=pjp.getArgs();
            this.begin();
            rtValue=pjp.proceed(args);
            this.commit();
            return rtValue;
        }catch (Throwable e){
            this.rollback();
            throw new RuntimeException(e);
        }finally {
            this.realse();
        }
    }
}
