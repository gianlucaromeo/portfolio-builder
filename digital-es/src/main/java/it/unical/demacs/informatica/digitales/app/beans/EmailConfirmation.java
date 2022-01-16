package it.unical.demacs.informatica.digitales.app.beans;

public class EmailConfirmation {

	private long userId;
	private String token;

	public EmailConfirmation() {}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

}
