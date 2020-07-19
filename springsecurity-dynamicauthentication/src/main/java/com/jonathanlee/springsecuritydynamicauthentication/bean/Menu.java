package com.jonathanlee.springsecuritydynamicauthentication.bean;

import lombok.Data;

import java.util.List;

/**
 * description: Menu
 * date: 2020-07-19 14:32
 * author: 30315
 * version: 1.0
 */
@Data
public class Menu {

    private Integer id;
    private String pattern;

    private List<Role> roles;

}