package com.codingshots.aopDemo.aspect;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Order(3)
public class MyApiAnalyticsAspect {

    @Before("com.codingshots.aopDemo.aspect.UtilAopExpressions.forDaoPackageNoGetterSetter()")
    public void performApiAnalytics(){
        // add our custom code
        System.out.println("\n=====> Performing API Analytics");
    }
}
