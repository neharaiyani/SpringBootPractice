package com.codingshots.aopDemo.aspect;

import com.codingshots.aopDemo.Account;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.List;

@Aspect
@Component  // discover this Aspect using Component Scanning by Spring
@Order(2)
public class MyDemoLoggingAspect {

    /* ********** Advice Declarations ********** */

    /**
     * JoinPoint: 3 types: field, constructor, method ---> Spring AOP only supports "method" level JoinPoint
     *      : JoinPoint has metadata about "method call"
     */

    /* Add a new "Advice" for "@Around" on the findAccounts() method */
    // Combination of @Before and @After, with more fine-grained control, can handle exceptions as well
    // ProceedingJoinPoint: handle to the “target method”, can be used to execute the target method
    @Around("execution(* com.codingshots.aopDemo.service.*.getFortune(..))")
    public Object aroundGetFortuneAdvice(ProceedingJoinPoint theProceedingJoinPoint) throws Throwable{
        // print out which method we are advising on
        String method = theProceedingJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @Around on method: " + method);

        // get begin timestamp
        long begin = System.currentTimeMillis();

        // now, let's execute the method (now, this method may throw an exception based on the value of tripwire)
        Object result = null;

        try {
            result = theProceedingJoinPoint.proceed();
        } catch (Exception exc){
            // log the exception
            System.out.println(exc.getMessage());

            // give user a custom message (Handle The Exception)
//            result = "Major accident! But no worries, your private AOP helicopter is on the way!";

            // rethrow the exception
            throw exc;
        }

        // get end timestamp
        long end = System.currentTimeMillis();

        // compute duration and display it
        long duration = end - begin;
        System.out.println("\n===>>> Duration: " + duration / 1000.0 + " seconds");

        return result;
    }

    /* Add a new "Advice" for "@After" on the findAccounts() method */
    // Does not have access to exception, for that, use @AfterThrowing
    @After("execution(* com.codingshots.aopDemo.dao.AccountDAO.findAccounts(..))")
    public void afterFinallyFindAccountsAdvice(JoinPoint theJoinPoint){
        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @After (finally) on method: " + method);
    }

    /* Add a new "Advice" for "@AfterThrowing" on the findAccounts() method */
    // "throwing" and method param name should match
    // can only read exceptions, for handling, use @Around advice
    @AfterThrowing(
            pointcut = "execution(* com.codingshots.aopDemo.dao.AccountDAO.findAccounts(..))",
            throwing = "theExc"
    )
    public void afterThrowingFindAccountsAdvice(JoinPoint theJoinPoint, Throwable theExc){
        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterThrowing on method: " + method);

        // log the exception
        System.out.println("\n=====>>> The Exception: " + theExc);
    }

    /* Add a new "Advice" for "@AfterReturning" on the findAccounts() method */
    // "returning" and method param name should match
    @AfterReturning(
            pointcut = "execution(* com.codingshots.aopDemo.dao.AccountDAO.findAccounts(..))",   // used Pointcut Expression
            returning = "result"
    )
    public void afterReturningFindAccountsAdvice(JoinPoint theJoinPoint, List<Account> result){
        // print out which method we are advising on
        String method = theJoinPoint.getSignature().toShortString();
        System.out.println("\n=====>>> Executing @AfterReturning on method: " + method);

        // print out the results of the method call
        System.out.println("\n=====>>> Result: " + result);

        // let's post-process the data ... let's modify it :)

        // convert the account name to uppercase
        convertAccountNamesToUpperCase(result);

        System.out.println("\n=====>>> Modified Result: " + result);
    }

    private void convertAccountNamesToUpperCase(List<Account> result) {
        // loop through accounts
        for(Account tempAccount : result){
            // get uppercase version of name
            String upperName = tempAccount.getName().toUpperCase();

            // update the name on the account
            tempAccount.setName(upperName);
        }
    }

    //    @Before("forDaoPackage()")
    @Before("com.codingshots.aopDemo.aspect.UtilAopExpressions.forDaoPackageNoGetterSetter()")
    public void beforeAddAccountAdvice(JoinPoint theJoinPoint){
        // add our custom code
        System.out.println("\n=====> Executing @Before Advice on method");

        // display the method signature
        MethodSignature methodSignature = (MethodSignature) theJoinPoint.getSignature();
        System.out.println("Method: " + methodSignature);

        // display method arguments (Array of Objects)

        // get args
        Object[] args = theJoinPoint.getArgs();

        // loop through args (gives actual "values")
        for(Object tempArg : args){
            System.out.println(tempArg);

            if(tempArg instanceof Account){
                // downcast Object, and print Account specific stuff
                Account theAccount = (Account) tempArg;

                System.out.println("Account Name: " + theAccount.getName());
                System.out.println("Account Level: " + theAccount.getLevel());
            }
        }
    }
}
