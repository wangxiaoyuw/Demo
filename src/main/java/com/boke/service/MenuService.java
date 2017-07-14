package com.boke.service;

import com.boke.dao.MenuMapper;
import com.boke.pojo.Menu;
import com.boke.pojo.RoleAndPrem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangzy on 2017/7/7.
 */
@Service
public class MenuService {

    @Resource
    private MenuMapper menuMapper;

    public List<Menu> findAllMenu() {
        return menuMapper.findAllMenu();
    }


    public List<RoleAndPrem> findAllMenuByRole(Integer id) {
        return menuMapper.findAllMenuByRole(id);
    }
}
