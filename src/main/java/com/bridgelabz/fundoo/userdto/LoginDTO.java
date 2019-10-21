package com.bridgelabz.fundoo.userdto;


import javax.validation.constraints.Email;
import javax.validation.constraints.Size;

import org.springframework.lang.NonNull;

public class LoginDTO {

	@NonNull
	@Email
	@Size(min=8 ,max=40)
	private String email;
	
	
	@NonNull
	@Size(min=8,max=30)
	private String password;

	 

	public LoginDTO(@Email @Size(min = 8, max = 40) String email, @Size(min = 8, max = 30) String password) {
		super();
		this.email = email;
		this.password = password;
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
	
	
	
	
}
