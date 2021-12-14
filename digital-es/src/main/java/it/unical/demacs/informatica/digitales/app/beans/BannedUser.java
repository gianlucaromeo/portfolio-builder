package it.unical.demacs.informatica.digitales.app.beans;

public class BannedUser {
	
	private long id;
	private long userId;
	private long moderatorId;
	private String reason;
	private String dateStart;
	private String dateEnd;
	
	
	public BannedUser() {
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
	public long getModeratorId() {
		return moderatorId;
	}
	public void setModeratorId(long moderatorId) {
		this.moderatorId = moderatorId;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public String getDateEnd() {
		return dateEnd;
	}
	public void setDateEnd(String dateEnd) {
		this.dateEnd = dateEnd;
	}
	public String getDateStart() {
		return dateStart;
	}
	public void setDateStart(String dateStart) {
		this.dateStart = dateStart;
	}
	
	
	
	
	
	

}
