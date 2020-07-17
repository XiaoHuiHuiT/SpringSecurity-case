package com.jonathanlee.springsecurity_pro.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

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

    /*
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
     */

    /*
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
                // 比如说你访问一个,但是这个借口需要登录之后才可以访问,那么Security会自动的给你跳转到登录页面去,前后端分离的话登录页面就是一个接口而已
                // .loginPage("/login")
                // usernameParameter就相当于你前端表单上面的name属性的值 默认为username
                .usernameParameter("uname")
                // passwordParameter就相当于你前端表单上面的name属性的值 默认为password
                .passwordParameter("pwd")
                // 如果是前后端不分的登录的话,登录成功之后服务端会自动给你跳转到项目首页去,这个时候你就可以使用successForwardUrl给你来一个服务端跳转给你跳转到某个页面去
                // .successForwardUrl("")
                // 如果是前后端分离的情况下,你只需要告诉前端登录成功还是失败就行了,接下来的就是前端的处理了你不用管,你就可以使用 successHandler; successHandler登录成功的处理器
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        // Authentication 保存了你刚刚登录成功的用户信息
                        resp.setContentType("application/json;charset=utf-8");

                        PrintWriter out = resp.getWriter();
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 200);
                        map.put("msg", authentication.getPrincipal());
                        out.write(new ObjectMapper().writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                })
                // failureForwardUrl 登录失败服务端给你跳转到某个页面去
                // .failureForwardUrl("登录失败服务端给你跳转到某个页面")
                // failureHandler 登录失败的处理器
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                        // 登录失败的原因 AuthenticationException 异常
                        resp.setContentType("application/json;charset=utf-8");

                        PrintWriter out = resp.getWriter();
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 401);

                        if (e instanceof LockedException) {
                            map.put("msg", "账号被锁定,请联系管理员!");
                        } else if (e instanceof BadCredentialsException) {
                            map.put("msg", "用户名或密码错误!");
                        } else if (e instanceof DisabledException) {
                            map.put("msg", "账号被禁用,请联系管理员!");
                        } else if (e instanceof AccountExpiredException) {
                            map.put("msg", "账号过期,请联系管理员!");
                        } else if (e instanceof CredentialsExpiredException) {
                            map.put("msg", "密码过期,请联系管理员!");
                        } else {
                            map.put("msg", "登录失败!");
                        }

                        out.write(new ObjectMapper().writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                })

                // 登陆相关的接口可以直接访问,直接过
                .permitAll()
                .and()
                // postman测试,关闭csrf攻击
                .csrf().disable();
    }
    */

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
                // 比如说你访问一个,但是这个借口需要登录之后才可以访问,那么Security会自动的给你跳转到登录页面去,前后端分离的话登录页面就是一个接口而已
                // .loginPage("/login")
                // usernameParameter就相当于你前端表单上面的name属性的值 默认为username
                .usernameParameter("uname")
                // passwordParameter就相当于你前端表单上面的name属性的值 默认为password
                .passwordParameter("pwd")
                // 如果是前后端不分的登录的话,登录成功之后服务端会自动给你跳转到项目首页去,这个时候你就可以使用successForwardUrl给你来一个服务端跳转给你跳转到某个页面去
                // .successForwardUrl("")
                // 如果是前后端分离的情况下,你只需要告诉前端登录成功还是失败就行了,接下来的就是前端的处理了你不用管,你就可以使用 successHandler; successHandler登录成功的处理器
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        // Authentication 保存了你刚刚登录成功的用户信息
                        resp.setContentType("application/json;charset=utf-8");

                        PrintWriter out = resp.getWriter();
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 200);
                        map.put("msg", authentication.getPrincipal());
                        out.write(new ObjectMapper().writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                })
                // failureForwardUrl 登录失败服务端给你跳转到某个页面去
                // .failureForwardUrl("登录失败服务端给你跳转到某个页面")
                // failureHandler 登录失败的处理器
                .failureHandler(new AuthenticationFailureHandler() {
                    @Override
                    public void onAuthenticationFailure(HttpServletRequest req, HttpServletResponse resp, AuthenticationException e) throws IOException, ServletException {
                        // 登录失败的原因 AuthenticationException 异常
                        resp.setContentType("application/json;charset=utf-8");

                        PrintWriter out = resp.getWriter();
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 401);

                        if (e instanceof LockedException) {
                            map.put("msg", "账号被锁定,请联系管理员!");
                        } else if (e instanceof BadCredentialsException) {
                            map.put("msg", "用户名或密码错误!");
                        } else if (e instanceof DisabledException) {
                            map.put("msg", "账号被禁用,请联系管理员!");
                        } else if (e instanceof AccountExpiredException) {
                            map.put("msg", "账号过期,请联系管理员!");
                        } else if (e instanceof CredentialsExpiredException) {
                            map.put("msg", "密码过期,请联系管理员!");
                        } else {
                            map.put("msg", "登录失败!");
                        }

                        out.write(new ObjectMapper().writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                })

                // 登陆相关的接口可以直接访问,直接过
                .permitAll()
                .and()
                .logout()
                // 处理注销登录的地址
                .logoutUrl("/logout")
                // 注销成功之后的处理器
                .logoutSuccessHandler(new LogoutSuccessHandler() {
                    @Override
                    public void onLogoutSuccess(HttpServletRequest req, HttpServletResponse resp, Authentication authentication) throws IOException, ServletException {
                        // Authentication 保存了你刚刚登录成功的用户信息
                        resp.setContentType("application/json;charset=utf-8");

                        PrintWriter out = resp.getWriter();
                        Map<String, Object> map = new HashMap<>();
                        map.put("status", 200);
                        map.put("msg", "注销登录成功!");
                        out.write(new ObjectMapper().writeValueAsString(map));
                        out.flush();
                        out.close();
                    }
                })
                // .logoutUrl("登录失败服务端给你跳转到某个页面去")
                .and()
                // postman测试,关闭csrf攻击
                .csrf().disable();
    }
}