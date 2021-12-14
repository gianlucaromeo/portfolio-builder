package it.unical.demacs.informatica.digitales.app.beans;

import org.joda.time.DateTime;

public class User {

	private long id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private DateTime dateOfBirth; 
	private String mainPhoneNumber;
	private String secondaryPhoneNumber;
	private String contactEmail;
	private boolean confirmed;
	
	
	public User() {
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
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public DateTime getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(DateTime dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	public String getMainPhoneNumber() {
		return mainPhoneNumber;
	}
	public void setMainPhoneNumber(String mainPhoneNumber) {
		this.mainPhoneNumber = mainPhoneNumber;
	}
	public String getSecondaryPhoneNumber() {
		return secondaryPhoneNumber;
	}
	public void setSecondaryPhoneNumber(String secondaryPhoneNumber) {
		this.secondaryPhoneNumber = secondaryPhoneNumber;
	}
	public String getContactEmail() {
		return contactEmail;
	}
	public void setContactEmail(String contactEmail) {
		this.contactEmail = contactEmail;
	}
	public boolean isConfirmed() {
		return confirmed;
	}
	public void setConfirmed(boolean confirmed) {
		this.confirmed = confirmed;
	}
	
	
	
}
