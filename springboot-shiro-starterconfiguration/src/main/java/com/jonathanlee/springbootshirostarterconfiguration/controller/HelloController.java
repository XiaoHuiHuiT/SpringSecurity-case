package com.jonathanlee.springbootshirostarterconfiguration.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: HelloController
 * date: 2020-07-21 16:04
 * author: 30315
 * version: 1.0
 */
@RestController
@Slf4j
public class HelloController {
    
    @GetMapping("/hello")
    public String hello(){
        return "hello Shiro!";
    }
    
    @PostMapping("/doLogin")
    public void doLogin(String username,String password){
        Subject subject = SecurityUtils.getSubject();
        try {
            subject.login(new UsernamePasswordToken(username,password));
            log.debug("登录成功");
        }catch (AuthenticationException e){
            e.printStackTrace();
            log.debug("登录失败,{}",e.getMessage());
        }
    }

    @GetMapping("/login")
    public String login(){
        return "Please log!";
    }
    
}
