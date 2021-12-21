package it.unical.demacs.informatica.digitales.app.beans.validation;

import java.util.Objects;

public class SignUpFormValidatorResponse {

	private String firstNameResp;
	private String lastNameResp;
	private String dateOfBirthResp;
	private String emailResp;
	private String usernameResp;
	private String passwordResp;
	
	public SignUpFormValidatorResponse() {
		// TODO Auto-generated constructor stub
	}
	
	public String getDateOfBirthResp() {
		return dateOfBirthResp;
	}

	public void setDateOfBirthResp(String dateOfBirthResp) {
		this.dateOfBirthResp = dateOfBirthResp;
	}

	public String getEmailResp() {
		return emailResp;
	}

	public void setEmailResp(String emailResp) {
		this.emailResp = emailResp;
	}

	public String getUsernameResp() {
		return usernameResp;
	}

	public void setUsernameResp(String usernameResp) {
		this.usernameResp = usernameResp;
	}

	public String getPasswordResp() {
		return passwordResp;
	}

	public void setPasswordResp(String passwordResp) {
		this.passwordResp = passwordResp;
	}

	public String getLastNameResp() {
		return lastNameResp;
	}

	public void setLastNameResp(String lastNameResp) {
		this.lastNameResp = lastNameResp;
	}
	
	public void setFirstNameResp(String firstNameResp) {
		this.firstNameResp = firstNameResp;
	}
	
	public String getFirstNameResp() {
		return firstNameResp;
	}

	@Override
	public int hashCode() {
		return Objects.hash(dateOfBirthResp, emailResp, firstNameResp, lastNameResp, passwordResp, usernameResp);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		SignUpFormValidatorResponse other = (SignUpFormValidatorResponse) obj;
		return Objects.equals(dateOfBirthResp, other.dateOfBirthResp) && Objects.equals(emailResp, other.emailResp)
				&& Objects.equals(firstNameResp, other.firstNameResp)
				&& Objects.equals(lastNameResp, other.lastNameResp) && Objects.equals(passwordResp, other.passwordResp)
				&& Objects.equals(usernameResp, other.usernameResp);
	}
	
}
