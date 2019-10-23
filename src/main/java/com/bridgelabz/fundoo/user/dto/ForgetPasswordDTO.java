package com.bridgelabz.fundoo.user.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

public class ForgetPasswordDTO {
	
	@NotNull
	@Size(min=8,max=30)
	private String password;

    private String reTypePassword;

    private String email;
    
	public ForgetPasswordDTO(@NotNull @Size(min = 8, max = 30) String password, String reTypePassword) {
		super();
		this.password = password;
		this.reTypePassword = reTypePassword;
	
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
    
}
