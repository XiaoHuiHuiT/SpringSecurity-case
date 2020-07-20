package com.jonathanlee.springsecurityoauth2.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

/**
 * description: SecurityConfig
 * date: 2020-07-20 21:40
 * author: 30315
 * version: 1.0
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

    @Bean
    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 在内存中身份验证
        auth.inMemoryAuthentication()
                .withUser("jonathanlee").password("$2a$10$7.AsjSsxg5UmCkiC7WT2c.7JDnVqkUfexqBgPPlCyZaAeuRwTy52.").roles("admin")
                .and()
                .withUser("xhh").password("$2a$10$7.AsjSsxg5UmCkiC7WT2c.7JDnVqkUfexqBgPPlCyZaAeuRwTy52.").roles("user");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.antMatcher("/oauth/**")
                .authorizeRequests()
                .antMatchers("/oauth/**").permitAll()
                .and()
                // 关闭 csrf 攻击
                .csrf().disable();
    }
}