package com.codingshots.springboot.core.restDemo;

import com.codingshots.springboot.core.dependencyInjection.Coach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DemoController {

    // define a private field for the dependency
    private Coach myCoach;

    // @Qualifier("beanId"), in case of multiple beans (for multiple DIs, use comma ',' separated @Qualifier)
    @Autowired
    public DemoController(@Qualifier("aquatic") Coach theCoach){
        myCoach = theCoach;
    }

    // define a constructor for dependency injection
//    @Autowired  // tells Spring to inject a dependency, (optional, if there's only one constructor)
//    public DemoController(Coach theCoach){
//        myCoach = theCoach;
//    }

    // a setter method (or ANY method) for dependency injection
//    @Autowired
//    public void setMyCoach(Coach myCoach) {
//        this.myCoach = myCoach;
//    }

    // get mapping endpoint to get Daily Workout
    @GetMapping("/dailyworkout")
    public String getDailyWorkout(){
        return myCoach.getDailyWorkout();
    }
}
