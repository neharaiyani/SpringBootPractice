package com.codingshots.aopDemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

//@Aspect // If we only have "Pointcuts", then @Aspect is Optional. (Only required for Advices, @Before, etc...)
//@Component    // why not???
public class UtilAopExpressions {

    /**
     * Pointcut Expression: "execution(public void addAccount())"
     *      : run this code BEFORE target object Method: public void addAccount()
     *      : try to match within our Project Package only
     */

    /**
     * Pointcut Declarations: @Pointcut("execution(public void addAccount())")
     *      : reusable, can be combined using logical operators
     */

    /**
     * Declared In a Separate File:
     *      : issue -> can't find referenced pointcut forDaoPackageNoGetterSetter
     *      : solution -> use Fully Qualified Classname when using these Pointcuts
     *                  (e.g., com.codingshots.aopDemo.aspect.UtilAopExpressions.forDaoPackageNoGetterSetter())
     */

    /* ********** Pointcut Declarations ********** */

    @Pointcut("execution(* com.codingshots.aopDemo.dao.*.*(..))")
    public void forDaoPackage(){}

    // getter method
    @Pointcut("execution(* com.codingshots.aopDemo.dao.*.get*(..))")
    public void getterMethod(){}

    // setter method
    @Pointcut("execution(* com.codingshots.aopDemo.dao.*.set*(..))")
    public void setterMethod(){}

    // COMBINE 2 POINTCUTs: include package AND exclude getter / setter methods
    @Pointcut("forDaoPackage() && !(getterMethod() || setterMethod())")
    public void forDaoPackageNoGetterSetter(){}
}
