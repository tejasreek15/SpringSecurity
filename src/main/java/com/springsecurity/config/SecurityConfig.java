package com.springsecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    private static final String[] BYPASS_AUTH_LIST = {
            "/api/user/saveUserInfo",
            "/api/hello",
    };

    @Bean
    //authentication
    public UserDetailsService userDetailsService(){
//        UserDetails user = User.withUsername("user")
//                .password(passwordEncoder.encode("user@123"))
//                .roles("USER")
//                .build();
//        UserDetails admin = User.withUsername("admin")
//                .password(passwordEncoder.encode("admin@123"))
//                .roles("ADMIN")
//                .build();
        return new UserInfoUserDetailsService();
    }

    @Bean
    //authorization
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
           return http.csrf().disable()
                   .authorizeHttpRequests()
                   .requestMatchers(BYPASS_AUTH_LIST).permitAll()
                   .and()
                   .authorizeHttpRequests()
                   .requestMatchers("/api/welcome").authenticated()
                   .and().formLogin()
                   .and().build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }


    @Bean
    public AuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
        daoAuthenticationProvider.setUserDetailsService(userDetailsService());
        daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
        return daoAuthenticationProvider;
    }

}
