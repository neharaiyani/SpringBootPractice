package com.codingshots.aopDemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(1)   // lower no., higher priority
public class MyCloudLogAsyncAspect {

    @Before("com.codingshots.aopDemo.aspect.UtilAopExpressions.forDaoPackageNoGetterSetter()")
    public void logToCloudAsync(){
        // add our custom code
        System.out.println("\n=====> Logging to Cloud in Async Fashion");
    }
}
