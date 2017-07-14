package com.boke.service;

import com.boke.dao.RoleMapper;
import com.boke.pojo.Role;
import com.boke.pojo.RoleAndPrem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by wangzy on 2017/7/7.
 */
@Service
public class RoleService {

    @Resource
    private RoleMapper roleMapper;

    public List<Role> findAllRole() {
        return roleMapper.findAllRole();
    }

    public Role findRolesByRolename(String rolename) {
        return roleMapper.findRolesByRolename(rolename);
    }

    public Role findRolesByUserId(Integer id) {
        return roleMapper.findRolesByUserId(id);
    }

    public RoleAndPrem findALLByPid(int c) {
        return roleMapper.findALLByPid(c);
    }

    public void saveTree(RoleAndPrem roleAndPrem) {
        roleMapper.saveTree(roleAndPrem);
    }

    public void deleteByRoleId(int roleid) {
        roleMapper.deleteByRoleId(roleid);
    }

    public void saveRole(String rolename, String roledesc, int a) {
        roleMapper.saveRole(rolename,roledesc,a);
    }

    public Role findRolesByRoleId(Integer id) {
        return roleMapper.findRolesByRoleId(id);
    }

    public void updateRole(String id, String rolename, String roledesc, int a) {
        roleMapper.updateRole( id, rolename,roledesc, a);
    }

    public void deleteRoleByRoleId(Integer id) {
        roleMapper.deleteRoleByRoleId(id);
    }

    public void deleteByRoleId2(Integer id) {
        roleMapper.deleteRoleByRoleId2(id);
    }
}
