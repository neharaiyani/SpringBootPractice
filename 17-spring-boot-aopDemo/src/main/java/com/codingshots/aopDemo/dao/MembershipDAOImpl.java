package com.codingshots.aopDemo.dao;

import org.springframework.stereotype.Repository;

@Repository // for component scanning
public class MembershipDAOImpl implements MembershipDAO{
    @Override
    public boolean addAccount() {
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING A MEMBERSHIP ACCOUNT");
        return true;
    }

    @Override
    public void addSillyMember() {
        System.out.println(getClass() + ": DOING MY DB WORK: ADDING A SILLY MEMBER");
    }

    @Override
    public void goToSleep() {
        System.out.println(getClass() + ": I'm going to Sleep() now..!");
    }
}
