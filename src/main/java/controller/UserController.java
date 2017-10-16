package controller;

import javax.annotation.Resource;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import entity.User;
import service.UserService;

@Controller
@RequestMapping(value ="/user")
public class UserController {
	
	@Resource
	private UserService userService;

	@RequestMapping(value="/getUserById")
	public String getUserById(){
		User user = userService.queryUserById(1);
		
		return "hello word";
	}
	
	
}
