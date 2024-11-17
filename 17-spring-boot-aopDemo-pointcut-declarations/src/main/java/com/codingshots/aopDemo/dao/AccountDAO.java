package com.codingshots.aopDemo.dao;

import com.codingshots.aopDemo.Account;

import java.util.List;

public interface AccountDAO {

    // add a new method: findAccounts()
    List<Account> findAccounts();

    List<Account> findAccounts(boolean tripWire);

    void addAccount();

    void addAccount(Account theAccount);

    void addAccount(Account theAccount, boolean vipFlag);

    boolean doWork();

    // so thar we can call these methods using reference of AccountDAO interface
    public String getName();

    public void setName(String name);

    public String getServiceCode();

    public void setServiceCode(String serviceCode);
}
