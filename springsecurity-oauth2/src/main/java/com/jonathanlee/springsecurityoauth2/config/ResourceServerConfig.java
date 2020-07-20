package com.jonathanlee.springsecurityoauth2.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

/**
 * description: ResourceServerConfig
 * date: 2020-07-20 21:37
 * author: 30315
 * version: 1.0
 */
@Configuration
@EnableResourceServer
public class ResourceServerConfig extends ResourceServerConfigurerAdapter {

    @Override
    public void configure(ResourceServerSecurityConfigurer resources) throws Exception {
        // 配置 rid
        resources.resourceId("rid").stateless(true);
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        // 授权请求
        http.authorizeRequests().antMatchers("/admin/**").hasRole("admin")
                .antMatchers("/user/**").hasRole("user")
                // 其它请求只需要授权就能访问
                .anyRequest().authenticated();
    }
}