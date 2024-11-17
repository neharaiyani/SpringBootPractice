package com.codingshots.aopDemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component  // discover this Aspect using Component Scanning by Spring
public class MyDemoLoggingAspect {

    // this is where we add all of our related advices for logging

    // let's start with a @Before advice

    /**
     * Pointcut Expression: "execution(public void addAccount())"
     *      : run this code BEFORE target object Method: public void addAccount()
     *      : try to match within our Project Package only
     */

    // Match on "Method Name"
//    @Before("execution(public void addAccount())")  // match addAccount() in "any" class
//    @Before("execution(public void com.codingshots.aopDemo.dao.AccountDAO.addAccount())")  // match addAccount() in "AccountDAO" class / interface only
//    @Before("execution(public void add*())")  // match method starting with "add" in "any" class
//    @Before("execution(void add*())")  // match method with return type "void" only, starting with "add" in "any" class

    // Match on "Method Parameters"
//    @Before("execution(* add*())")  // match method with "any" return type, no arguments
//    @Before("execution(* add*(com.codingshots.aopDemo.Account))")  // match method with "Account" param type
//    @Before("execution(* add*(com.codingshots.aopDemo.Account, ..))")  // match method with "Account" AND more param types
//    @Before("execution(* add*(..))")  // match method on "any" params of any numbers

    // Match on Method in a "Package"
    @Before("execution(* com.codingshots.aopDemo.dao.*.*(..))")  // match any method in given "package"
    public void beforeAddAccountAdvice(){
        // add our custom code
//        System.out.println("\n=====> Executing @Before Advice on method: public void addAccount()");
//        System.out.println("\n=====> Executing @Before Advice on method: public void add*()");
//        System.out.println("\n=====> Executing @Before Advice on method: public * add*()");
//        System.out.println("\n=====> Executing @Before Advice on method: public * add*(com.codingshots.aopDemo.Account, ..)");
//        System.out.println("\n=====> Executing @Before Advice on method: public * add*(..)");
        System.out.println("\n=====> Executing @Before Advice on method: public * com.codingshots.aopDemo.dao.*.*(..)");
    }
}
