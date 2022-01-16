package it.unical.demacs.informatica.digitales.app.beans.validation;

public class PostValidatorResponse {
	
	private long id;
	private String title;
	private String description;
	private String picture;
	private String pubblicationDate;
	private String lastEditDate;
	private String refLink;
	private long userId;
	boolean validation=true;
	
	
	public PostValidatorResponse() {}
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
	
	

}
