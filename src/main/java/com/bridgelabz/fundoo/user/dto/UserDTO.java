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
	@Pattern(regexp = "\\D*" ,message = "character accept only")
    private String firstName;
	
	@NotNull
	@Size(min = 3, message = "last name cannot be less than 3 characters")
	@Pattern(regexp = "\\D*" ,message = "character accept only")
	private String lastName;
	
	@NotNull
	@Pattern(regexp=".+@.+\\..+", message="Wrong email!")
	private String email;
	
	@NotNull(message="Please select a password")
	@Length(min=6, max=15, message="Password should be between 6 - 15 charactes")
	@Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}",message = "password has digit,lower case,upper case and special character must occur at least once,Password should be between 6 - 15 charactes")
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
