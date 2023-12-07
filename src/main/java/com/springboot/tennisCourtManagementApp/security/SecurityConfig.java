package com.springboot.tennisCourtManagementApp.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource){
        return new JdbcUserDetailsManager(dataSource);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer -> configurer
                        .requestMatchers("/stats").hasRole("ADMIN")
//                        .requestMatchers("/leaders/**").hasRole("MANAGER")
//                        .requestMatchers("/systems/**").hasRole("ADMIN")
//                        .requestMatchers("/employees/showFormForAdd").hasRole("MANAGER")
//                        .requestMatchers("/employees/showFormForUpdate").hasRole("MANAGER")
                        .anyRequest().authenticated())
                .formLogin(form -> form
                        .loginPage("/loginPage")
                        .loginProcessingUrl("/authenticateTheUser")
                        .permitAll())
                .logout(logout -> logout.permitAll())
                .exceptionHandling(configurer -> configurer
                        .accessDeniedPage("/accessDenied"));

        return http.build();
    }
}
