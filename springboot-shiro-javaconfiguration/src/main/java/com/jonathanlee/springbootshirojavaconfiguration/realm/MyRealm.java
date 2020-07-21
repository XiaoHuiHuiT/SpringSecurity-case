package com.jonathanlee.springbootshirojavaconfiguration.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthenticatingRealm;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

/**
 * description: MyRealm
 * date: 2020-07-21 14:53
 * author: 30315
 * version: 1.0
 */
public class MyRealm extends AuthorizingRealm {

    // 身份验证信息
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();

        if("jonathanlee".equals(username)){
            return new SimpleAuthenticationInfo(username,"123",getName());
        }

        return null;
    }

    // 授权信息
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

}