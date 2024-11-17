package com.codingshots.springboot.core.dependencyInjection;

// not using @Component on purpose (configured in Java Config class "SportConfig.java")
public class SwimCoach implements Coach{
    public SwimCoach(){
        System.out.println("In constructor: " + getClass().getSimpleName());
    }
    @Override
    public String getDailyWorkout() {
        return "Swim 1000 meters as warm up";
    }
}
