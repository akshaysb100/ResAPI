package com.bridgelabz.fundoo.user.dto;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class UpdateUserInformationDTO {

	@NotEmpty(message = "first name must not be empty")
	@Pattern(regexp = "\\D*" ,message = "character accept only")
    private String firstName;
	
	@NotNull
	@Size(min = 3, message = "last name cannot be less than 3 characters")
	@Pattern(regexp = "\\D*" ,message = "character accept only")
	private String lastName;

	public UpdateUserInformationDTO(
			@NotEmpty(message = "first name must not be empty") @Pattern(regexp = "\\D*", message = "character accept only") String firstName,
			@NotNull @Size(min = 3, message = "last name cannot be less than 3 characters") @Pattern(regexp = "\\D*", message = "character accept only") String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
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
	
}
