package com.codingshots.mvc_security.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class DemoSecurityConfig {

    // add support for JDBC ... no more hardcoded users :-)
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        // plain password: test123
        // bcrypt password: fun123
//        return new JdbcUserDetailsManager(dataSource);

        // for custom database schema
        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        // define query to retrieve a user by username (user_id is passed by login form)
        jdbcUserDetailsManager
                .setUsersByUsernameQuery("select user_id, pw, active from members where user_id = ?");

        // define query to retrieve the authorities by username
        jdbcUserDetailsManager
                .setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id = ?");

        return jdbcUserDetailsManager;
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
        http.authorizeHttpRequests(configurer ->
                configurer
                        .requestMatchers("/").hasRole("EMPLOYEE")              // Match on root path: /
                        .requestMatchers("/leaders/**").hasRole("MANAGER")     // Match on path: /leaders And all sub-directories (**)
                        .requestMatchers("/systems/**").hasRole("ADMIN")       // Match on path: /systems And all sub-directories (**)
                        .anyRequest().authenticated()   // Any request to the app must be authenticated (ie logged in)
            )
                .formLogin(form ->  // We are customizing the form login process
                    form
                            .loginPage("/showMyLoginPage")                  // Show our custom form at the request mapping, create a controller for this request mapping
                            .loginProcessingUrl("/authenticateTheUser")     // Login form should POST data to this URL for processing (check user id and password), No Controller Request Mapping required for this. We get this for free :-)
                            .permitAll()                                    // Allow everyone to see login page. No need to be logged in.
                )

                .logout(logout ->
                        logout.permitAll()    // Add logout support for default URL /logout
                )
                .exceptionHandling(configurer ->
                        configurer.accessDeniedPage("/access-denied")
                );

        return http.build();
    }

    /*
    @Bean
    public InMemoryUserDetailsManager userDetailsManager(){
        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("EMPLOYEE", "MANAGER", "ADMIN")
                .build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }
     */
}
