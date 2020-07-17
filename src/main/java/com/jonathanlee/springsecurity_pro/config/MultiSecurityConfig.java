package com.jonathanlee.springsecurity_pro.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @title: MultiSecurityConfig
 * @projectName springsecurity
 * @描述: TODO
 * @作者: 小灰灰
 * @创建时间 2020-05-1920:16
 */
@Configuration
public class MultiSecurityConfig {

    /**
     * 表示告诉系统,我的这个密码现在不加密
     * 一会用户在前端输入的时候
     * 就输入1234就能登陆上来
     * 这是一个过期的方案,后面我在详细的介绍密码加密的问题
     * @return
     */
    @Bean
    PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Autowired
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // 基于内存的认证
        auth.inMemoryAuthentication()
                // 配置第一个
                .withUser("jonathanlee").password("1234").roles("admin")
                .and()
                // 配置第二个
                .withUser("xhh").password("1234").roles("user");// 到了这里,就相当于内存里面配置了两个用户
    }

    @Configuration
    // 设置优先级: 值越小越先执行
    @Order(1)
    public static class AdminSecurityConfig extends WebSecurityConfigurerAdapter {
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            // 请求路径为：admin开头的 需要具备 admin这个角色才可以访问
            http.antMatcher("/admin/**").authorizeRequests().anyRequest().hasAnyRole("admin");
        }
    }

    @Configuration
    public static class OtherSecurityConfig extends WebSecurityConfigurerAdapter{
        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests().anyRequest().authenticated()
                    .and()
                    .formLogin()
                    .loginProcessingUrl("doLogin")
                    // 登陆相关的接口可以直接访问,直接过
                    .permitAll()
                    .and()
                    .csrf().disable();
        }
    }
}
