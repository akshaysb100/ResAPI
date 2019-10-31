package com.bridgelabz.fundoo.user.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;

public class EmailPasswordDTO {

	
	@NotNull
	@Pattern(regexp=".+@.+\\..+", message="Wrong email!")
	private String email;
	
	@NotNull(message="Please select a password")
	@Length(min=6, max=15, message="Password should be between 6 - 15 charactes")
	@Pattern(regexp="(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}",message = "password has digit,lower case,upper case and special character must occur at least once,Password should be between 6 - 15 charactes")
	private String password;

	public EmailPasswordDTO(@NotNull @Pattern(regexp = ".+@.+\\..+", message = "Wrong email!") String email,
			@NotNull(message = "Please select a password") @Length(min = 6, max = 15, message = "Password should be between 6 - 15 charactes") @Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{6,}", message = "password has digit,lower case,upper case and special character must occur at least once,Password should be between 6 - 15 charactes") String password) {
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
