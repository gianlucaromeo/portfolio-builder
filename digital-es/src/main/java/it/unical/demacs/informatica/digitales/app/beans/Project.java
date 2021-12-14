package it.unical.demacs.informatica.digitales.app.beans;

public class Project {
	
	private long id;
	private long userId;
	private String title;
	private String description;
	private byte[] picture;
	private String linkRef;
	
	public Project() {
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
	public byte[] getPicture() {
		return picture;
	}
	public void setPicture(byte[] picture) {
		this.picture = picture;
	}
	public String getLinkRef() {
		return linkRef;
	}
	public void setLinkRef(String linkRef) {
		this.linkRef = linkRef;
	}
	
	
	
}
