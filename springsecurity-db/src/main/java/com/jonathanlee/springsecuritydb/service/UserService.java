package com.jonathanlee.springsecuritydb.service;

import com.jonathanlee.springsecuritydb.bean.User;
import com.jonathanlee.springsecuritydb.mapper.UserMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * description: UserService
 * date: 2020-07-18 18:35
 * author: 30315
 * version: 1.0
 */
@Service
@Slf4j
public class UserService implements UserDetailsService {

    private final UserMapper userMapper;

    public UserService(UserMapper userMapper) {
        this.userMapper = userMapper;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userMapper.loadUserByUsername(username);
        // 如果查询的用户对象为空，就是登录失败，用户不存在
        if (user == null) {
            log.debug("登录失败");
            throw new UsernameNotFoundException("用户不存在");
        }
        // 到了这里就代表登录成功，然后根据用户的Id查询用户的角色
        user.setRoles(userMapper.getRolesByUserId(user.getId()));
        log.debug("登录成功 {}",user);
        return user;
    }
}