package com.boke.dao;


import com.boke.pojo.Menu;
import com.boke.pojo.Role;
import com.boke.pojo.User;
import com.boke.pojo.UserAndRole;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface UserMapper {

    User selectByPrimaryKey(Integer id);

    Set<String> findRolesByUserId(Integer id);

    Set<String> findMenuByUserId(Integer id);

    User getByName(String username);

    List<Menu> findAllMenuById(Integer id);

    List<User> findAllUser();

    long getUserCount();

    List<User> selectUserByPage(@Param(value="startPos") Integer startPos, @Param(value="pageSize") Integer pageSize);

    void saveUser(User user);

    void saveUserAndRole(@Param(value="id")Integer id, @Param(value="id1")Integer id1);

    void deteleById(Integer id);

    void deteleUandRById(Integer id);

    User findUserById(Integer id);

    long getUserParamCount(@Param(value="condition")String condition);

    List selectUserByParamPage(@Param(value="startPos")int startPos, @Param(value="pageSize")int pageSize,@Param(value="condition") String condition);

    void updateUser(@Param("id") Integer id,@Param("username") String username);

    void updateUserAndRole(@Param("id") Integer id,@Param("id1") Integer id1);

    List<UserAndRole> findAllById(Integer id);

    List<Menu> findAllMenuByRoleId(Integer id);

    void saveUserPassword(@Param("id") Integer id,@Param("newpassword") String newpassword);
}