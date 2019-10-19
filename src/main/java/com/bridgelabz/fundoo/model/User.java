package com.bridgelabz.fundoo.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "Id")
	private long id;
	
	@Column(name = "First_Name")
	private String firstName;
	
	@Column(name = "LastName")
	private String lastName;
	
	@Column(name = "MobileNumber")
	private String mobileNumber;
	
	@Column(name ="EmailId")
	private String EmailId;
	
	@Column(name = "Password")
	private String password;
	
	@Column(name = "RegistrationDate")

	private LocalDateTime registeredDate;
	@Column(name = "UpdatedDate")
	private LocalDateTime updatedDate;

	@Column()
	private String reTypePassword;

	
	public User() {
	}
	
	public User(long id, String firstName, String lastName, String email, String password, String isVerified,
			LocalDateTime registeredDate, LocalDateTime updatedDate) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.EmailId = email;
		this.password = password;
		this.reTypePassword = isVerified;
		this.registeredDate = registeredDate;
		this.updatedDate = updatedDate;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
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

	public String getEmailId() {
		return EmailId;
	}

	public void setEmailId(String emailId) {
		EmailId = emailId;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public LocalDateTime getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(LocalDateTime registeredDate) {
		this.registeredDate = registeredDate;
	}

	public LocalDateTime getUpdatedDate() {
		return updatedDate;
	}

	public void setUpdatedDate(LocalDateTime updatedDate) {
		this.updatedDate = updatedDate;
	}

	public String getReTypePassword() {
		return reTypePassword;
	}

	public void setReTypePassword(String reTypePassword) {
		this.reTypePassword = reTypePassword;
	}
	
	
	
}
