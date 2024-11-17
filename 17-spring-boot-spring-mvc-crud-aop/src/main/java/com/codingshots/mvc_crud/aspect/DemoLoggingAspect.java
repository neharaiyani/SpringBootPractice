package com.codingshots.mvc_crud.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Aspect
@Component
public class DemoLoggingAspect {

    // set up Logger
    private Logger myLogger = Logger.getLogger(getClass().getName());

    // set up Pointcut declarations
    @Pointcut("execution(* com.codingshots.mvc_crud.controller.*.*(..))")
    private void forControllerPackage(){}

    @Pointcut("execution(* com.codingshots.mvc_crud.service.*.*(..))")
    private void forServicePackage(){}

    @Pointcut("execution(* com.codingshots.mvc_crud.dao.*.*(..))")
    private void forDaoPackage(){}

    // combine Pointcuts
    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    private void forAppFlow(){}

    // add @Before advice
    @Before("forAppFlow()")
    public void before(JoinPoint theJoinPoint){
        // display the method we are calling
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("=====>> in @Before: calling method: " + theMethod);

        // display the arguments to the method

        // get the arguments
        Object[] args = theJoinPoint.getArgs();

        // loop through and display arguments
        for(Object tempArg : args){
            myLogger.info("=====>> argument: " + tempArg);
        }
    }

    // add @AfterReturning advice
    @AfterReturning(
            pointcut = "forAppFlow()",
            returning = "theResult"
    )
    public void afterReturning(JoinPoint theJoinPoint, Object theResult){
        // display the method we are returning from
        String theMethod = theJoinPoint.getSignature().toShortString();
        myLogger.info("=====>> in @AfterReturning: from method: " + theMethod);

        // display the data returned
        myLogger.info("=====>> Result: " + theResult);
    }
}
