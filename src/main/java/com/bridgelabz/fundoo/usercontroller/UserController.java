package com.bridgelabz.fundoo.usercontroller;

import java.util.Arrays;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.userdto.LoginDTO;
import com.bridgelabz.fundoo.userdto.UserDTO;
import com.bridgelabz.fundoo.usermodel.User;
import com.bridgelabz.fundoo.userservice.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
    private UserService userService;
	
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome user";
	}
	
	@RequestMapping("/add")
	public List<UserDTO> getAll(){
		return (Arrays.asList(new UserDTO("Akshay", "Bavalekar", "akshaybavalekar100@gmail.com", "aks100","aks100")));
	}
	
	
	@PostMapping("/register")
	public String register(@RequestBody UserDTO userDTO) {
		
		return userService.register(userDTO);
		
	}
	
	
	@PostMapping("/login")
	public String login(@RequestBody LoginDTO loginDTO) 
	{
		return userService.login(loginDTO);
		
	}
	
	
}
