package it.unical.demacs.informatica.digitales.app.beans;

import java.util.Objects;

public class Curriculum {
	private long id;
	private long userId;
	private String hobbiesDescription;

	public Curriculum() {
		// TODO Auto-generated constructor stub
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getHobbiesDescription() {
		return hobbiesDescription;
	}

	public void setHobbiesDescription(String hobbiesDescription) {
		this.hobbiesDescription = hobbiesDescription;
	}

	@Override
	public int hashCode() {
		return Objects.hash(hobbiesDescription, id, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Curriculum other = (Curriculum) obj;
		return Objects.equals(hobbiesDescription, other.hobbiesDescription) && id == other.id && userId == other.userId;
	}

	@Override
	public String toString() {
		return "Curriculum [id=" + id + ", userId=" + userId + ", hobbiesDescription=" + hobbiesDescription + "]";
	}

}
