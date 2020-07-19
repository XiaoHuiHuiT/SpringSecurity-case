package com.jonathanlee.springsecuritydynamicauthentication.service;

import com.jonathanlee.springsecuritydynamicauthentication.bean.Menu;

import java.util.List;

/**
 * description: MenuService
 * date: 2020-07-19 16:32
 * author: 30315
 * version: 1.0
 */
public interface MenuService {
    List<Menu> getAllMenus();
}