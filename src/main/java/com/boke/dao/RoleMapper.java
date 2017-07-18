package com.boke.dao;


import com.boke.pojo.Role;
import com.boke.pojo.RoleAndPrem;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {

    int insert(Role record);

    List<Role> findAllRole();

    Role findRolesByRolename(@Param("rolename") String rolename);

    Role findRolesByUserId(Integer id);

    RoleAndPrem findALLByPid(int c);

    void saveTree(RoleAndPrem roleAndPrem);

    void deleteByRoleId(int roleid);

    void saveRole(@Param("rolename") String rolename, @Param("roledesc") String roledesc, @Param("a") int a);

    Role findRolesByRoleId(Integer id);

    void updateRole(@Param("id")String id,@Param("rolename") String rolename, @Param("roledesc") String roledesc,@Param("a") int a);

    void deleteRoleByRoleId(Integer id);

    void deleteRoleandUserByRoleId(Integer id);
}