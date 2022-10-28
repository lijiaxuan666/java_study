package first.dao;

import first.domain.Account;

public interface IAccountDao {
    Account findByName(String name);
    void update(Account account);
}
