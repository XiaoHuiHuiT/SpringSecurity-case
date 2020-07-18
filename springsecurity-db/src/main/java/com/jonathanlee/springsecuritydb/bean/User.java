package com.jonathanlee.springsecuritydb.bean;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * description: User
 * date: 2020-07-18 18:16
 * author: 30315
 * version: 1.0
 */
@Data
public class User implements UserDetails {

    private Integer id;
    private String username;
    private String password;

    // 该用户是否可用
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Boolean enabled;

    // 该用户是否被锁定
    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Boolean locked;

    private List<Role> roleList;

    // 该用户拥有的角色
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roleList) {
            authorities.add(new SimpleGrantedAuthority("ROLB_" + role.getName()));
        }
        return authorities;
    }

    // 该用户账号是否过期 true：未过期   false：已过期
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    // 该用户是否锁定
    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    // 凭证是否未过期，也就是密码是否过期
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    // 该用户是否启用
    @Override
    public boolean isEnabled() {
        return enabled;
    }
}