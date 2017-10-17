package com.kellan.dao;

import java.util.List;

import com.kellan.entity.User;

public interface UserDao {
	
	/**
	 * 根据id查询用户
	 * @author Kellan
	 * @param id
	 * @return
	 */
	public User queryUserById(int id);

	/**
	 * 添加用户
	 * @author Kellan
	 * @param user
	 */
	public void addUser(User user);
	
	/**
	 * 查询所有
	 * @author Kellan
	 * @return
	 */
	public List<User> queryAll();
	
	/**
	 * 删除用户
	 * @author Kellan
	 * @param id
	 */
	public void deleteUser(Integer id);
	
	
	public void updateUser(User user);
}
