package com.jonathanlee.springsecuritydynamicauthentication.filter;

import com.jonathanlee.springsecuritydynamicauthentication.bean.Menu;
import com.jonathanlee.springsecuritydynamicauthentication.bean.Role;
import com.jonathanlee.springsecuritydynamicauthentication.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * description: MyFilter
 * date: 2020-07-19 16:31
 * author: 30315
 * version: 1.0
 */
@Slf4j
@Component
public class MyFilter implements FilterInvocationSecurityMetadataSource {

    AntPathMatcher pathMatcher = new AntPathMatcher();

    private final MenuService menuService;

    public MyFilter(MenuService menuService) {
        this.menuService = menuService;
    }

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) o).getRequestUrl();
        List<Menu> allMenus = menuService.getAllMenus();
        log.debug("查询请求URL与角色成功,{}",allMenus);

        for (Menu menu : allMenus) {
            // 如果请求的URL和查询出来的URL相等需要看看他是否具备有对应的角色
            if(pathMatcher.match(menu.getPattern(),requestUrl)){
                List<Role> roles = menu.getRoles();
                String[] rolesStr = new String[roles.size()];

                for (int i = 0; i < roles.size(); i++) {
                    rolesStr[i] = "ROLE_" + roles.get(i).getName();
                }
                return SecurityConfig.createList(rolesStr);
            }
        }
        return SecurityConfig.createList("ROLE_login");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}