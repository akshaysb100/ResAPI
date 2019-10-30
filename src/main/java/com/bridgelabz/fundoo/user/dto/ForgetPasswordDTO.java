package com.bridgelabz.fundoo.user.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ForgetPasswordDTO {
	
	@NotNull
	@Size(min=8,max=30)
	private String password;

    private String confirmPassword;

    private String email;

    
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
	
    
}
