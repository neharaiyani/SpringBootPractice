package com.codingshots.springboot.core.dependencyInjection;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

@Component  // marks the class as a Spring Bean
public class CricketCoach implements Coach{
    // demo constructor
    public CricketCoach(){
        System.out.println("In constructor: " + getClass().getSimpleName());
    }

    // define custom init method
    @PostConstruct
    public void doMyStartupStuff(){
        System.out.println("In doMyStartupStuff(): " + getClass().getSimpleName());
    }

    // define custom destroy method
    @PreDestroy
    public void doMyCleanupStuff(){
        System.out.println("In doMyCleanupStuff(): " + getClass().getSimpleName());
    }

    @Override
    public String getDailyWorkout() {
        return "Practice fast bowling for 15 minutes, NOW!!!";
    }
}
