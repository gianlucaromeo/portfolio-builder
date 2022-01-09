package it.unical.demacs.informatica.digitales.app.beans.validation;

public class ResetPasswordValidatorResponse {
	
	private String password;
	private String repeatPassword;
	private String token;
	
	public ResetPasswordValidatorResponse() {}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRepeatPassword() {
		return repeatPassword;
	}

	public void setRepeatPassword(String repeatPassword) {
		this.repeatPassword = repeatPassword;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	
	
	
	

}
