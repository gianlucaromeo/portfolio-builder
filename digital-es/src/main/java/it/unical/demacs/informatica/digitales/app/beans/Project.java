package it.unical.demacs.informatica.digitales.app.beans;

import java.util.Objects;

public class Project {

	private long id;
	private long userId;
	private String title;
	private String description;
	private String picture;
	private String linkRef;

	public Project() {}

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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public String getLinkRef() {
		return linkRef;
	}

	public void setLinkRef(String linkRef) {
		this.linkRef = linkRef;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, linkRef, picture, title, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Project other = (Project) obj;
		return Objects.equals(description, other.description) && id == other.id
				&& Objects.equals(linkRef, other.linkRef) && Objects.equals(picture, other.picture)
				&& Objects.equals(title, other.title) && userId == other.userId;
	}

	@Override
	public String toString() {
		return "Project [id=" + id + ", userId=" + userId + ", title=" + title + ", description=" + description
				+ ", picture=" + picture + ", linkRef=" + linkRef + "]";
	}

}
