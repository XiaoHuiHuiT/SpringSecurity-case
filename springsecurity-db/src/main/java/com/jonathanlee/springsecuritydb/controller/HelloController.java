package com.jonathanlee.springsecuritydb.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * description: HelloController
 * date: 2020-07-18 20:23
 * author: 30315
 * version: 1.0
 */
@RestController
public class HelloController {

    @GetMapping("/hello")
    public String hello(){
        return "hello Security!";
    }

    @GetMapping("/admin/hello")
    public String admin(){
        return "hello admin!";
    }

    @GetMapping("/dba/hello")
    public String dba(){
        return "hello dba!";
    }

    @GetMapping("/user/hello")
    public String user(){
        return "hello user!";
    }

}