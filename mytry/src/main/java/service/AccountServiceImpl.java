package service;

import dao.IAccountDao;
import domain.Account;
import utils.TransactionManager;

public class AccountServiceImpl implements IAccountService{
    TransactionManager transactionManager;
    IAccountDao accountDao;

    public void setAccountDao(IAccountDao accountDao) {
        this.accountDao = accountDao;
    }

    public void setTransactionManager(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public Account findByName(String name) {
        return accountDao.findByName(name);
    }

    @Override
    public void update(Account account) {
        accountDao.update(account);
    }

    @Override
    public void transfer(String source, String target, float money) {
        //使用环绕增强使这个方法拥有事务控制能力，所以此处无需再进行事务控制，使代码更简洁
        Account user1=findByName(source);
        Account user2=findByName(target);
        user1.setMoney(user1.getMoney()-money);
        user2.setMoney(user2.getMoney()+money);
        update(user1);
        update(user2);
        System.out.println("转账成功");
        /*try {
            transactionManager.begin();
            Account user1=findByName(source);
            Account user2=findByName(target);
            user1.setMoney(user1.getMoney()-money);
            user2.setMoney(user2.getMoney()+money);
            update(user1);
            update(user2);
            transactionManager.commit();
            System.out.println("转账成功");
        }catch (Exception e){
            transactionManager.rollback();
            throw new RuntimeException("转账失败");
        }finally {
            transactionManager.realse();
        }*/
    }
}
