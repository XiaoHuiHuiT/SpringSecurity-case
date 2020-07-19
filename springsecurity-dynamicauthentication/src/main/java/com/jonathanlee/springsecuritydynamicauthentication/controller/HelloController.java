package com.jonathanlee.springsecuritydynamicauthentication.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: HelloController
 * date: 2020-07-19 14:56
 * author: 30315
 * version: 1.0
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "Hello Security!";
    }

    @GetMapping("/admin/hello")
    public String admin(){
        return "Hello admin!";
    }

    @GetMapping("/dba/hello")
    public String dba(){
        return "Hello dba!";
    }

    @GetMapping("/user/hello")
    public String user(){
        return "Hello user!";
    }

}