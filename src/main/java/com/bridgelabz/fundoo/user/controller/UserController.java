package com.bridgelabz.fundoo.user.controller;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.UserDTO;
import com.bridgelabz.fundoo.user.service.UserService;
import com.bridgelabz.fundoo.utility.ErrorResponse;
import com.bridgelabz.fundoo.utility.Response;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
    private UserService userService;
	
	@GetMapping("/welcome")
	public String welcome() {
		return "welcome user";
	}	
	
//	@PostMapping("/register")
//	public String register(@RequestBody @Valid UserDTO userDTO) {
//		
//		return userService.register(userDTO);
//		
//	}
	
	@PostMapping("/register")
	public ResponseEntity<UserDTO> Register(@Valid @RequestBody  UserDTO userDTO){
	
	     userService.register(userDTO);
		
	    return new ResponseEntity<UserDTO>(userDTO, HttpStatus.OK);
	}
	
	 @PostMapping("/login")
	 public ResponseEntity<Response> login(@RequestBody  LoginDTO loginDTO) {

	 Response response = userService.login(loginDTO);

	 return new ResponseEntity<Response>(response, HttpStatus.UNAUTHORIZED);

	 }
	
	 @PutMapping("/update")
	 public String forgetPassword(@RequestBody LoginDTO loginDTO) {
			
	  return userService.updateUser(loginDTO);
	 }
	 
}
