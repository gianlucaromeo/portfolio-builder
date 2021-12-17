package it.unical.demacs.informatica.digitales.app.beans;

import java.util.Objects;

public class Post {

	private long id;
	private String title;
	private String description;
	private String picture;
	private String pubblicationDate;
	private String lastEditDate;
	private String refLink;
	private long userId;
	
	public Post() {
	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
	public String getPubblicationDate() {
		return pubblicationDate;
	}
	public void setPubblicationDate(String pubblicationDate) {
		this.pubblicationDate = pubblicationDate;
	}
	public String getLastEditDate() {
		return lastEditDate;
	}
	public void setLastEditDate(String lastEditDate) {
		this.lastEditDate = lastEditDate;
	}
	public String getRefLink() {
		return refLink;
	}
	public void setRefLink(String refLink) {
		this.refLink = refLink;
	}
	public long getUserId() {
		return userId;
	}
	public void setUserId(long userId) {
		this.userId = userId;
	}

	@Override
	public int hashCode() {
		return Objects.hash(description, id, lastEditDate, picture, pubblicationDate, refLink, title, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Post other = (Post) obj;
		return Objects.equals(description, other.description) && id == other.id
				&& Objects.equals(lastEditDate, other.lastEditDate) && Objects.equals(picture, other.picture)
				&& Objects.equals(pubblicationDate, other.pubblicationDate) && Objects.equals(refLink, other.refLink)
				&& Objects.equals(title, other.title) && userId == other.userId;
	}

	@Override
	public String toString() {
		return "Post [id=" + id + ", title=" + title + ", description=" + description + ", picture=" + picture
				+ ", pubblicationDate=" + pubblicationDate + ", lastEditDate=" + lastEditDate + ", refLink=" + refLink
				+ ", userId=" + userId + "]";
	}
	
	
	
}
