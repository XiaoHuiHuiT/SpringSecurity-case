package com.jonathanlee.springsecuritydynamicauthentication.service.impl;

import com.jonathanlee.springsecuritydynamicauthentication.bean.Menu;
import com.jonathanlee.springsecuritydynamicauthentication.mapper.MenuMapper;
import com.jonathanlee.springsecuritydynamicauthentication.service.MenuService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * description: MenuServiceImpl
 * date: 2020-07-19 16:35
 * author: 30315
 * version: 1.0
 */
@Service
@Slf4j
public class MenuServiceImpl implements MenuService {

    private final MenuMapper menuMapper;

    public MenuServiceImpl(MenuMapper menuMapper) {
        this.menuMapper = menuMapper;
    }

    @Override
    public List<Menu> getAllMenus() {
        log.debug("查询所有菜单成功");
        return menuMapper.getAllMenus();
    }
}