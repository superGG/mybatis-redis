package com.kellan.controller;

import java.io.Serializable;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.kellan.entity.User;
import com.kellan.service.UserService;

@Controller
@RequestMapping(value ="/user")
public class UserController {
	
	@Resource
	private UserService userService;
	
	@Resource
    private RedisTemplate<Serializable, Serializable> redisTemplate;

	@RequestMapping(value="/getUserById")
	@ResponseBody
	public User getUserById(Integer id){
		User user = userService.queryUserById(id);
		return user;
	}
	
	@RequestMapping(value="/addUser")
	@ResponseBody
	public User addUser(User user) {
		User user1 = userService.addUser(user);
		return user1;
	}
	
	@RequestMapping(value="/getAllUser")
	@ResponseBody
	public List<User> getAllUser() {
		return userService.getAll();
	}
	
	@RequestMapping(value="/deleteUser")
	@ResponseBody
	public String deleteUser(Integer userId) {
		userService.deleteUser(userId);
		return "success";
	}
	
	@RequestMapping(value="/updateUser")
	@ResponseBody
	public String updateUser(User user){
		userService.updateUser(user);
		return "success";
	}
	
	
}
