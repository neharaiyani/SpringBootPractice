package com.codingshots.aopDemo.dao;

import com.codingshots.aopDemo.Account;
import org.springframework.stereotype.Repository;

@Repository // for component scanning
public class AccountDAOImpl implements AccountDAO{
    @Override
    public void addAccount() {
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT");
    }

    @Override
    public void addAccount(Account theAccount) {
            System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT: " + theAccount);
    }

    @Override
    public void addAccount(Account theAccount, boolean vipFlag) {
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING AN ACCOUNT with VIP FLAG: " + theAccount + ", " + vipFlag);
    }

    @Override
    public boolean doWork() {
        System.out.println(getClass() + ": doWork()");
        return false;
    }
}
