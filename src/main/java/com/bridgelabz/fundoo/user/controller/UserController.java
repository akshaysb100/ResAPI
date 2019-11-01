package com.bridgelabz.fundoo.user.controller;


import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import com.bridgelabz.fundoo.user.dto.EmailPasswordDTO;
import com.bridgelabz.fundoo.user.dto.ForgetPasswordDTO;
import com.bridgelabz.fundoo.user.dto.LoginDTO;
import com.bridgelabz.fundoo.user.dto.UpdateUserInformationDTO;
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
	
	 @PostMapping("/register")
	 public ResponseEntity<Response> Register(@Valid @RequestBody  UserDTO userDTO){
	
	     Response response =  userService.register(userDTO);
		 
	     return new ResponseEntity<Response>(response, HttpStatus.OK);
	 }
	 
	 @RequestMapping("/verrifyUser/{token}")
	 public ResponseEntity<Response> verifyUser(@PathVariable String token) throws VerificationFailedException
     {
		 Response response = userService.verifyUser(token);
	     return new ResponseEntity<Response>(response, HttpStatus.OK);
	 }
	 @PostMapping("/login")
	 public ResponseEntity<Response> login(@RequestBody  LoginDTO loginDTO) {
 
	     Response response = userService.login(loginDTO);
	     return new ResponseEntity<Response>(response, HttpStatus.UNAUTHORIZED);

	 }
	
	 @PutMapping("/forgotPassword")
	 public ResponseEntity<Response> forgetPassword(@RequestParam String email) {
		
	    Response response = userService.forgetPassword(email);
	    return new ResponseEntity<Response>(response,HttpStatus.OK);
	 }
	 
	 @PutMapping(value = "/resetPassword/{token}")
	 public ResponseEntity<Response> resetPassword(@PathVariable String token,@Valid @RequestBody ForgetPasswordDTO forgetPassword) throws VerificationFailedException{
		 
		 Response response =userService.resetPassword(token,forgetPassword);
		 return new ResponseEntity<Response>(response,HttpStatus.OK);
	 }
	 
	 @PutMapping(value = "/updateUser")
	 public ResponseEntity<Response> updateUser(@Valid @RequestBody EmailPasswordDTO emailPasswordDTO){
		 
		 Response response = userService.updateUser(emailPasswordDTO);
		 return new ResponseEntity<Response>(response,HttpStatus.OK);
	 }
	 
	 @PutMapping(value = "/resetInformation/{token}")
	 public ResponseEntity<Response> resetInformation(@PathVariable String token,@Valid @RequestBody UpdateUserInformationDTO updateUserInformationDTO) throws VerificationFailedException{
		 
		 Response response =userService.resetInformation(token, updateUserInformationDTO);
		 return new ResponseEntity<Response>(response,HttpStatus.OK);
	 }
	 
	 @DeleteMapping(value = "/deleteUser")
	 public ResponseEntity<Response> deleteUser(@RequestBody EmailPasswordDTO emailPasswordDTO){
		 
		 Response response = userService.deleteUser(emailPasswordDTO);
		 
		 return new ResponseEntity<Response>(response,HttpStatus.OK);
	 }
	 
	 @RequestMapping("/deleteVerifyUser/{token}")
	 public ResponseEntity<Response> deleteVerifyUser(@PathVariable String token)throws VerificationFailedException{
		 Response response = userService.deleteVerifyUser(token);
		 return new ResponseEntity<Response>(response,HttpStatus.OK);
	 }
}
