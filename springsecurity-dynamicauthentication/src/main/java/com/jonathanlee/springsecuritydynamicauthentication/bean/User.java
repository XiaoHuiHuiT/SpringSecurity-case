package com.jonathanlee.springsecuritydynamicauthentication.bean;

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
 * date: 2020-07-19 14:32
 * author: 30315
 * version: 1.0
 */
@Data
public class User implements UserDetails {

    private Integer id;
    private String username;
    private String password;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Boolean enabled;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    private Boolean locked;

    private List<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role : roles) {
            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
        }
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
