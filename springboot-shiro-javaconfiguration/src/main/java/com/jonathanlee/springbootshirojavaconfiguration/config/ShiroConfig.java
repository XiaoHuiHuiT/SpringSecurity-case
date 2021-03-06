package com.jonathanlee.springbootshirojavaconfiguration.config;

import com.jonathanlee.springbootshirojavaconfiguration.realm.MyRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * description: ShiroConfig
 * date: 2020-07-21 14:59
 * author: 30315
 * version: 1.0
 */
@Configuration
public class ShiroConfig {

    @Bean
    MyRealm myRealm(){
        return new MyRealm();
    }

    @Bean
    SecurityManager securityManager(){
        DefaultWebSecurityManager manager = new DefaultWebSecurityManager();
        manager.setRealm(myRealm());
        return manager;
    }

    @Bean
    ShiroFilterFactoryBean shiroFilterFactoryBean(){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        bean.setSecurityManager(securityManager());
        // 登录URL
        bean.setLoginUrl("/login");
        // 登录成功URL
        bean.setSuccessUrl("/index");

        Map<String, String> map = new LinkedHashMap<>();
        map.put("/doLogin","anon");
        map.put("/**","authc");

        bean.setFilterChainDefinitionMap(map);
        return bean;
    }

}
