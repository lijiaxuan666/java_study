package dao;

import domain.Account;

public interface IAccountDao {
    Account findByName(String name);
    void update(Account account);
}
