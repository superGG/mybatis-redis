package service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import dao.UserDao;
import entity.User;
import service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Resource
	private UserDao userDao;

	public User queryUserById(int id) {
		return userDao.queryUserById(id);
	}

	public User addUser(User user) {
		userDao.addUser(user);
		return user;
	}

	public List<User> getAll() {
		return userDao.queryAll();
	}

	public void deleteUser(Integer id) {
		userDao.deleteUser(id);
	}

	public void updateUser(User user) {
		userDao.updateUser(user);
	}

}
