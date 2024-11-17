package com.codingshots.aopDemo.dao;

import com.codingshots.aopDemo.Account;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository // for component scanning
public class AccountDAOImpl implements AccountDAO{

    private String name;
    private String serviceCode;

    @Override
    public List<Account> findAccounts() {
        // don't throw an exception
        return findAccounts(false);
    }

    @Override
    public List<Account> findAccounts(boolean tripWire) {
        // for academic purposes ... simulate an exception
        if(tripWire){
            throw new RuntimeException("No soup for you!!!");
        }

        List<Account> myAccounts = new ArrayList<>();

        // create sample accounts
        Account account1 = new Account("John", "Silver");
        Account account2 = new Account("Mary", "Platinum");
        Account account3 = new Account("Luca", "Gold");

        // add them to our accounts list
        myAccounts.add(account1);
        myAccounts.add(account2);
        myAccounts.add(account3);

        return myAccounts;
    }

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

    /* ********** getter / setter ********** */

    public String getName() {
        System.out.println(getClass() + ": getName");
        return name;
    }

    public void setName(String name) {
        System.out.println(getClass() + ": setName");
        this.name = name;
    }

    public String getServiceCode() {
        System.out.println(getClass() + ": getServiceCode");
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        System.out.println(getClass() + ": setServiceCode");
        this.serviceCode = serviceCode;
    }
}
