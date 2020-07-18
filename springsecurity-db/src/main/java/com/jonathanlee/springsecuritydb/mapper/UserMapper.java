package com.jonathanlee.springsecuritydb.mapper;

import com.jonathanlee.springsecuritydb.bean.Role;
import com.jonathanlee.springsecuritydb.bean.User;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * description: UserMapper
 * date: 2020-07-18 18:35
 * author: 30315
 * version: 1.0
 */
@Mapper
public interface UserMapper {
    User loadUserByUsername(String username);

    List<Role> getRolesByUserId(Integer id);
}