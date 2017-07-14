package com.boke.dao;


import com.boke.pojo.Menu;
import com.boke.pojo.RoleAndPrem;

import java.util.List;
import java.util.Set;

public interface MenuMapper {

    int insert(Menu record);

    List<Menu> findAllMenu();

    List<RoleAndPrem> findAllMenuByRole(Integer id);
}