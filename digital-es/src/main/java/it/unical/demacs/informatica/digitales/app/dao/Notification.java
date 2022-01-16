package it.unical.demacs.informatica.digitales.app.dao;

public class Notification {
	private String contentTitle;
	private String reason;
	private String contentDescription;
	private String type;
	private long contentId;
	private long id;
	
	public Notification() {}
	
	
	public long getContentId() {
		return contentId;
	}


	public void setContentId(long contentId) {
		this.contentId = contentId;
	}


	public void setId(long id) {
		this.id = id;
	}
	public long getId() {
		return id;
	}
	
	public String getContentTitle() {
		return contentTitle;
	}



	public void setContentTitle(String contentTitle) {
		this.contentTitle = contentTitle;
	}



	public String getType() {
		return type;
	}



	public void setType(String type) {
		this.type = type;
	}



	public String getTitle() {
		return contentTitle;
	}

	public void setTitle(String title) {
		this.contentTitle = title;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public String getContentDescription() {
		return contentDescription;
	}

	public void setContentDescription(String contentDescription) {
		this.contentDescription = contentDescription;
	}
	
	
	
}
