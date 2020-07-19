package com.jonathanlee.springsecuritydynamicauthentication.mapper;

import com.jonathanlee.springsecuritydynamicauthentication.bean.Menu;

import java.util.List;

/**
 * description: MenuMapper
 * date: 2020-07-19 16:37
 * author: 30315
 * version: 1.0
 */
public interface MenuMapper {

    List<Menu> getAllMenus();
}
