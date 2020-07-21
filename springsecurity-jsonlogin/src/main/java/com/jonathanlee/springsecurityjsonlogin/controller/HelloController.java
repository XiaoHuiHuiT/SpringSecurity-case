package com.jonathanlee.springsecurityjsonlogin.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: HelloController
 * date: 2020-07-21 19:15
 * author: 30315
 * version: 1.0
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello Security!";
    }

}