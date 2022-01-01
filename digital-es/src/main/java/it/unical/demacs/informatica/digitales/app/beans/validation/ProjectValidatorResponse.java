package it.unical.demacs.informatica.digitales.app.beans.validation;

public class ProjectValidatorResponse {
	private long id;
	private long userId;
	private String title;
	private String description;
	private String linkRef;
	private String picture;
	private boolean validation=true;
	
	public ProjectValidatorResponse() {
		// TODO Auto-generated constructor stub
	}
	public boolean isValidation() {
		return validation;
	}
	public void setValidation(boolean validation) {
		this.validation = validation;
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
	public String getLinkRef() {
		return linkRef;
	}
	public void setLinkRef(String linkRef) {
		this.linkRef = linkRef;
	}
	public String getPicture() {
		return picture;
	}
	public void setPicture(String picture) {
		this.picture = picture;
	}
	
	
}
