package it.unical.demacs.informatica.digitales.app.beans;

public class BannedUser {
	
	private long id;
	private long userId;
	private long moderatorId;
	private String reason;
	private String startDate;
	private String endDate;
	
	
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
	
	
	
	
	
	

}
