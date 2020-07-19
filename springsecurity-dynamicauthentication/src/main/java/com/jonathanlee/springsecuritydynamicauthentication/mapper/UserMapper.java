package com.jonathanlee.springsecuritydynamicauthentication.mapper;

import com.jonathanlee.springsecuritydynamicauthentication.bean.Role;
import com.jonathanlee.springsecuritydynamicauthentication.bean.User;

import java.util.List;

/**
 * description: UserMapper
 * date: 2020-07-19 15:14
 * author: 30315
 * version: 1.0
 */
public interface UserMapper {
    User loadUserByUsername(String username);

    List<Role> getRolesByUserId(Integer id);
}