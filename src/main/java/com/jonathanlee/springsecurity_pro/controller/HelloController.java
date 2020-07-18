package com.jonathanlee.springsecurity_pro.controller;

import com.jonathanlee.springsecurity_pro.service.MethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    private final MethodService methodService;

    public HelloController(MethodService methodService) {
        this.methodService = methodService;
    }

    @GetMapping("/hello1")
    public String hello1() {
        return methodService.admin();
    }

    @GetMapping("/hello2")
    public String admin() {
        return methodService.user();
    }

    @GetMapping("/hello3")
    public String user() {
        return methodService.hello();
    }
}