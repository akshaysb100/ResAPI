package com.bridgelabz.fundoo.user.controller;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bridgelabz.fundoo.exception.VerificationFailedException;
import com.bridgelabz.fundoo.user.dto.ForgetPasswordDTO;
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
	 public ResponseEntity<Response> Register(@Valid @RequestBody  UserDTO userDTO){
	
	     Response response =  userService.register(userDTO);
		 
	     return new ResponseEntity<Response>(response, HttpStatus.OK);
	 }
	
	 @PostMapping("/login")
	 public ResponseEntity<Response> login(@RequestBody  LoginDTO loginDTO) {
 
	     Response response = userService.login(loginDTO);

	     return new ResponseEntity<Response>(response, HttpStatus.UNAUTHORIZED);

	 }
	
	 @PutMapping("/updatePasswords")
	 public ResponseEntity<Response> forgetPassword(@RequestBody ForgetPasswordDTO forgetPassword) {
		
		System.out.println("up password");
//	    return userService.updateUser(loginDTO);
	    Response response = userService.forgetPassword(forgetPassword);
	    return new ResponseEntity<Response>(response,HttpStatus.OK);
	 }
	 
	 @RequestMapping("/verrifyUser/{token}")
	 public ResponseEntity<Response> verifyUser(@PathVariable String token) throws VerificationFailedException
     {
		 Response response = userService.verifyUser(token);
	     return new ResponseEntity<Response>(response, HttpStatus.OK);
	 }
	 
	 @RequestMapping(value = "/verrifyMail/{token}", method = RequestMethod.PUT)
	 public ResponseEntity<Response> verifyMail(@PathVariable String token,@RequestBody ForgetPasswordDTO forgetPassword) throws VerificationFailedException{
		 
		 System.out.println("token :"+token);
		 Response response =userService.verifyMail(token,forgetPassword);
		 return new ResponseEntity<Response>(response,HttpStatus.OK);
	 }
	 
}
