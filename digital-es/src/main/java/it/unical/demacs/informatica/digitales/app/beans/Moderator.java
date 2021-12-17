package it.unical.demacs.informatica.digitales.app.beans;

import java.util.Objects;

public class Moderator {
	
	private long id;
	private String username;
	private String password;
	private String email;
	
	public Moderator() {}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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

	@Override
	public int hashCode() {
		return Objects.hash(email, id, password, username);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Moderator other = (Moderator) obj;
		return Objects.equals(email, other.email) && id == other.id && Objects.equals(password, other.password)
				&& Objects.equals(username, other.username);
	}

	@Override
	public String toString() {
		return "Moderator [id=" + id + ", username=" + username + ", password=" + password + ", email=" + email + "]";
	}
	
	
	
}
