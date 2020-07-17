package com.jonathanlee.springsecurity_pro.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * description: SecurityWebConfiguration
 * date: 2020-07-17 20:07
 * author: 30315
 * version: 1.0
 */
@Configuration
public class SecurityWebConfiguration extends WebSecurityConfigurerAdapter {

    /**
     * 表示告诉系统,我的这个密码现在不加密
     * 一会用户在前端输入的时候
     * 就输入1234就能登陆上来
     * 这是一个过期的方案,后面我在详细的介绍密码加密的问题
     *
     * @return PasswordEncoder
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 基于内存的认证
        auth.inMemoryAuthentication()
                // 配置第一个
                .withUser("jonathanlee").password("1234").roles("admin")
                .and()
                // 配置第二个
                .withUser("xhh").password("1234").roles("user");// 到了这里,就相当于内存里面配置了两个用户
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // 开启配置
        http.authorizeRequests()
                // 访问路径以: /admin/开头的 需要具备哪些角色
                .antMatchers("/admin/**").hasRole("admin")
                // 访问路径以: /user/开头的 需要具备 hasAnyRole中的任意一个角色即可
                .antMatchers("/user/**").hasAnyRole("admin", "user")
                //.antMatchers("/user/**").access("hasAnyRole('admin','admin')")
                //.antMatchers("/user/**").access("hasRole('admin') and hasRole('user')")
                // 剩下的其它请求,都是登录之后就能访问
                .anyRequest().authenticated()
                .and()
                // 表单登陆
                .formLogin()
                // 处理登陆请求的Url
                .loginProcessingUrl("/doLogin")
                // 登陆相关的接口可以直接访问,直接过
                .permitAll()
                .and()
                // postman测试,关闭csrf攻击
                .csrf().disable();
    }
}