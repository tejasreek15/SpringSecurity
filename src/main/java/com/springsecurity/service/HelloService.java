package com.springsecurity.service;


import org.springframework.stereotype.Service;

@Service
public class HelloService {

    public String hello(){
        return "hello";
    }

    public String welcome(){
        return "welcome";
    }
}
