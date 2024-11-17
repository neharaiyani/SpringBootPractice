package com.codingshots.aopDemo.dao;

import com.codingshots.aopDemo.Account;

public interface AccountDAO {

    void addAccount();

    void addAccount(Account theAccount);

    void addAccount(Account theAccount, boolean vipFlag);

    boolean doWork();
}
