package it.unical.demacs.informatica.digitales.app.beans;

import java.util.Objects;

public class CurriculumExperience {

	private long id;
	private long userId;
	private String title;
	private String place;
	private String startDate;
	private String endDate;
	private String description;
	private String type;

	public CurriculumExperience() {}

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

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getPlace() {
		return place;
	}

	public void setPlace(String place) {
		this.place = place;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, endDate, id, place, startDate, title, type, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CurriculumExperience other = (CurriculumExperience) obj;
		return Objects.equals(description, other.description) && Objects.equals(endDate, other.endDate)
				&& id == other.id && Objects.equals(place, other.place) && Objects.equals(startDate, other.startDate)
				&& Objects.equals(title, other.title) && Objects.equals(type, other.type) && userId == other.userId;
	}

	@Override
	public String toString() {
		return "CurriculumExperience [id=" + id + ", userId=" + userId + ", title=" + title + ", place=" + place
				+ ", startDate=" + startDate + ", endDate=" + endDate + ", description=" + description + ", type="
				+ type + "]";
	}

}
