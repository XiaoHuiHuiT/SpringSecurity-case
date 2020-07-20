package com.jonathanlee.springsecuritydynamicauthentication.config;

import com.jonathanlee.springsecuritydynamicauthentication.filter.MyAccessDecisionManager;
import com.jonathanlee.springsecuritydynamicauthentication.filter.MyFilter;
import com.jonathanlee.springsecuritydynamicauthentication.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;

/**
 * description: SecurityConfig
 * date: 2020-07-19 15:36
 * author: 30315
 * version: 1.0
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserService userService;

    private final MyFilter myFilter;

    private final MyAccessDecisionManager myAccessDecisionManager;

    public SecurityConfig(UserService userService, MyFilter myFilter, MyAccessDecisionManager myAccessDecisionManager) {
        this.userService = userService;
        this.myFilter = myFilter;
        this.myAccessDecisionManager = myAccessDecisionManager;
    }

    @Bean
    PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                    @Override
                    public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                        o.setAccessDecisionManager(myAccessDecisionManager);
                        o.setSecurityMetadataSource(myFilter);
                        return o;
                    }
                })
                .and()
                .formLogin()
                .permitAll()
                .and()
                .csrf().disable();
    }
}