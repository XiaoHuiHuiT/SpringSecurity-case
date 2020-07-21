package com.jonathanlee.springbootshirostarterconfiguration.config;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.TextConfigurationRealm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * description: ShiroConfig
 * date: 2020-07-21 15:56
 * author: 30315
 * version: 1.0
 */
@Configuration
public class ShiroConfig {

    @Bean
    Realm realm(){
        TextConfigurationRealm realm = new TextConfigurationRealm();
        realm.setUserDefinitions("jonathanlee=123,user \n admin=123,admin");
        realm.setRoleDefinitions("admin=read,write \n user=read");

        return realm;
    }

    @Bean
    ShiroFilterChainDefinition shiroFilterChainDefinition(){
        DefaultShiroFilterChainDefinition definition = new DefaultShiroFilterChainDefinition();
        definition.addPathDefinition("/doLogin","anon");
        definition.addPathDefinition("/**","authc");

        return definition;
    }
}