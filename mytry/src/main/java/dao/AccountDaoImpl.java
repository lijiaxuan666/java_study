package dao;

import domain.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import utils.ConnectionUtil;

import java.sql.SQLException;

public class AccountDaoImpl implements IAccountDao{
    private QueryRunner runner;
    private ConnectionUtil connectionUtil;

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
    }

    public void setConnectionUtil(ConnectionUtil connectionUtil) {
        this.connectionUtil = connectionUtil;
    }

    @Override
    public Account findByName(String name) {
        try {
            return runner.query(connectionUtil.creatConnection(),"select * from account where name = ?", new BeanHandler<Account>(Account.class),name);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Account account) {
        try {
            runner.update(connectionUtil.creatConnection(),"update account set name = ?,money = ? where id = ?",account.getName(),account.getMoney(),account.getId());
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
