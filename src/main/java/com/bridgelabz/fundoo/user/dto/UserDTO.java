package com.bridgelabz.fundoo.user.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Autowired;

import com.bridgelabz.fundoo.user.service.UserService;

public class UserDTO  {
 
	@Autowired
	UserService userService;
	
	@NotEmpty(message = "first name must not be empty")
    private String firstName;
	
	@NotNull
	@Size(min = 3, message = "last name cannot be less than 3 characters")
	private String lastName;
	
	@NotNull
	@Pattern(regexp=".+@.+\\..+", message="Wrong email!")
	private String email;
	
	@NotNull(message="Please select a password")
	@Length(min=5, max=10, message="Password should be between 5 - 10 charactes")
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
