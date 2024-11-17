package com.codingshots.springboot.core.customConfig;

import com.codingshots.springboot.core.dependencyInjection.Coach;
import com.codingshots.springboot.core.dependencyInjection.SwimCoach;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SportConfig {

    // define bean method
    @Bean("aquatic")   // bean Id defaults to the method name
    public Coach swimCoach(){
        return new SwimCoach();
    }
}
