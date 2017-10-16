package service;

import java.util.List;

import entity.User;

public interface UserService {
	
	public User queryUserById(int id);
	
	public User addUser(User user);
	
	public List<User> getAll();
	
	public void deleteUser(Integer id);
	
	public void updateUser(User user);
}
