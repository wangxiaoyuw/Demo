package com.boke.service;


import com.boke.dao.UserMapper;
import com.boke.pojo.Menu;
import com.boke.pojo.User;
import com.boke.pojo.UserAndRole;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

@Service
public  class IUserService {

	@Resource
	private UserMapper userMapper;

	public User getUserById(int userId) {
		// TODO Auto-generated method stub
		return this.userMapper.selectByPrimaryKey(userId);
	}

	/**
	 * 通过name
	 * @param username
	 * @return
	 */
	public User getByName(String username){
		return userMapper.getByName(username);
	}


	/**
	 *通过user id查询角色
	 * @param id
	 * @return
	 */
	public Set<String> findRolesByUserId(Integer id) {
		return userMapper.findRolesByUserId(id);
	}

	/**
	 * 通过user id查询拥有的权限，权限验证
	 * @param id
	 * @return
	 */
	public Set<String> findMenuByUserId(Integer id) {
		return userMapper.findMenuByUserId(id);
	}

	/**
	 * 通过user id查询拥有的权限，权限展示
	 * @param id
	 * @return
	 */
	public List<Menu> findAllMenuById(Integer id){
		return userMapper.findAllMenuById(id);
	}

	/**
	 * 查询用户列表
	 * @return
	 */
    public List<User> findAllUser() {
		return userMapper.findAllUser();
    }

	/**
	 * 查询  用户总数
	 */
	public long getUserCount() {
		return userMapper.getUserCount();
	}

	/**
	 * 查询每页数据
	 * @param startPos
	 * @param pageSize
	 * @return
	 */
	public List selectUserByPage(int startPos, int pageSize) {
		return userMapper.selectUserByPage(startPos,pageSize);
	}

	/**
	 * 保存user
	 * @param user
	 */
	public void saveUser(User user) {
		userMapper.saveUser(user);
	}


	public void saveUserAndRole(Integer id, Integer id1) {
		userMapper.saveUserAndRole(id, id1);
	}

	public void deteleById(Integer id) {
		userMapper.deteleById(id);
	}

	public void deteleById1(Integer id) {
		userMapper.deteleById1(id);
	}

	public User findUserById(Integer id) {
		return userMapper.findUserById(id);
	}


	/**
	 * 查询  参数总数
	 */
	public long getUserParamCount(String condition) {
		return userMapper.getUserParamCount(condition);
	}

	public List selectUserByParamPage(int startPos, int pageSize, String condition) {
		return userMapper.selectUserByParamPage(startPos, pageSize,condition);
	}

	public void updateUser(Integer id,String username) {
		userMapper.updateUser(id,username);
	}

	public void updateUserAndRole(Integer id,Integer id1) {
		userMapper.updateUserAndRole(id,id1);
	}

	public List<UserAndRole> findAllById(Integer id) {
		return userMapper.findAllById(id);
	}

	public List<Menu> findAllMenuByRoleId(Integer id) {
		return userMapper.findAllMenuByRoleId(id);
	}

	public void saveUserPassword(Integer id,String p) {
		userMapper.saveUserPassword(id,p);
	}
}
