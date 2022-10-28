package first.dao;

import first.domain.Account;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import first.utils.ConnectionUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.SQLException;

@Component("accountDao")
public class AccountDaoImpl implements IAccountDao{
    @Autowired
    private QueryRunner runner;
    @Autowired
    private ConnectionUtil connectionUtil;

    public void setRunner(QueryRunner runner) {
        this.runner = runner;
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
