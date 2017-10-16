package controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import entity.User;
import service.UserService;

@Controller
@RequestMapping(value ="/user")
public class UserController {
	
	@Resource
	private UserService userService;

	@RequestMapping(value="/getUserById")
	@ResponseBody
	public User getUserById(Integer id){
		User user = userService.queryUserById(id);
		return user;
	}
	
	
}
