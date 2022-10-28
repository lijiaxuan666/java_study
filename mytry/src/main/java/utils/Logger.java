package utils;

import org.aspectj.lang.ProceedingJoinPoint;

public class Logger {
    TransactionManager transactionManager;

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    /*
     *   Spring框架为我们提供了一个接口：ProceedingJoinPoint。该接口有一个方法proceed()，此方法就相当于明确调用切入点方法。
     *   该接口可以作为环绕通知的方法参数，在程序执行时，spring框架会为我们提供该接口的实现类供我们使用。
     *
     *    spring中的环绕通知：
     *       它是spring框架为我们提供的一种可以在代码中手动控制增强方法何时执行的方式。
     *
     *    参数spring会自动传进来，只需要写这就行
     */
    public Object aroundPringLog(ProceedingJoinPoint pjp){
        Object rtValue=null;
        try{
            Object[] args= pjp.getArgs();//得到方法执行所需的参数
            transactionManager.begin();
            rtValue=pjp.proceed(args);//其返回值时Object类型
            transactionManager.commit();
            return rtValue;
        }catch (Throwable t){
            transactionManager.rollback();
            throw new RuntimeException("转账失败");
        }finally {
            transactionManager.realse();
        }
    }
}
