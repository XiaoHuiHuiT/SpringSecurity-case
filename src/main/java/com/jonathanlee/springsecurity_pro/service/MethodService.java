package com.jonathanlee.springsecurity_pro.service;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

/**
 * @title: MethodService
 * @projectName springsecurity
 * @描述:
 * @作者: 小灰灰
 * @创建时间 2020-05-1921:59
 */
@Service
public class MethodService {

    @PreAuthorize("hasRole('admin')")
    public String admin() {
        return "hello admin";
    }

    @Secured("ROLE_user")
    public String user() {
        return "hello user";
    }

    @PreAuthorize("hasAnyRole('admin','user')")
    public String hello() {
        return "hello hello";
    }
}