package com.bridgelabz.fundoo.userdto;

import javax.validation.constraints.Size;

import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.fundoo.userservice.UserService;
import com.bridgelabz.fundoo.uservalidation.UserValidation;

import lombok.NonNull;

public class UserDTO {
 
	@Autowired
	UserService userService;
	
	@NonNull
	@Size(min = 3, message = "name cannot be less than 3 characters")
    private String firstName;
	
	private String lastName;
	
	private String email;
	
	private String password;
	
	private String reTypePassword;

	public UserDTO(String firstName,String lastname,String email,String password,String reTypePassword) {
	    
		this.firstName = firstName; 
		this.lastName = lastname;
		this.email = email;
		this.password = password;
		this.reTypePassword = reTypePassword;
		
	}

	public String getFirstName() {
	
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getReTypePassword() {
		return reTypePassword;
	}

	public void setReTypePassword(String reTypePassword) {
		this.reTypePassword = reTypePassword;
	}

	
	
	
	
}
