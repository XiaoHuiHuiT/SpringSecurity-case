package com.jonathanlee.springsecurityjwt.pojo;

import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

/**
 * description: User
 * date: 2020-07-21 20:32
 * author: 30315
 * version: 1.0
 */
@Data
public class User implements UserDetails {
    private String username;
    private String password;
    private List<GrantedAuthority> authorities;
    public String getUsername() {
        return username;
    }
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }
    @Override
    public boolean isEnabled() {
        return true;
    }
}