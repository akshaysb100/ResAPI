package com.bridgelabz.fundoo.user.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.bridgelabz.fundoo.note.model.NoteData;


@Entity
@Table(name = "user")
public class UserData {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userid")
	private long userid;
	
	@Column(name = "firstName")
	private String firstName;
	
	@Column(name = "lastName")
	private String lastName;
	
	@Column(name ="emailId")
	private String email;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "registrationDate")
	private LocalDateTime registeredDate;
	
	@Column(name = "updatedDate")
	private LocalDateTime updatedDate;

	@Column()
	private String reTypePassword;

	@Column(name  =  "verify", nullable = false)
	private boolean verify;
	
	@Column(name = "time")
	private long time;
	
	@OneToMany(cascade = CascadeType.ALL)
	private List<NoteData> noteList = new ArrayList<NoteData>();

	 
	public UserData(long userid, String firstName, String lastName, String email, String password,
			LocalDateTime registeredDate, LocalDateTime updatedDate, String reTypePassword, boolean verify, long time,
			List<NoteData> noteList) {
		super();
		this.userid = userid;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.registeredDate = registeredDate;
		this.updatedDate = updatedDate;
		this.reTypePassword = reTypePassword;
		this.verify = verify;
		this.time = time;
		this.noteList = noteList;
	}

	public long getUserid() {
		return userid;
	}

	public void setUserid(long userid) {
		this.userid = userid;
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

	public boolean isVerify() {
		return verify;
	}

	public void setVerify(boolean verify) {
		this.verify = verify;
	}

	public long getTime() {
		return time;
	}

	public void setTime(long time) {
		this.time = time;
	}

	public List<NoteData> getNoteList() {
		return noteList;
	}

	public void setNoteList(List<NoteData> noteList) {
		this.noteList = noteList;
	}

	@Override
	public String toString() {
		return "UserData [userid=" + userid + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", password=" + password + ", registeredDate=" + registeredDate + ", updatedDate=" + updatedDate
				+ ", reTypePassword=" + reTypePassword + ", verify=" + verify + ", time=" + time + ", noteList="
				+ noteList + "]";
	}
	
}
