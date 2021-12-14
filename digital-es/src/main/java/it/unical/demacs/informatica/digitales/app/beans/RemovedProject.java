package it.unical.demacs.informatica.digitales.app.beans;

public class RemovedProject {
	
	private long id;
	private long moderatorId;
	private long projectId;
	private String reason;
	private boolean seenByUser;
	
	public RemovedProject() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getModeratorId() {
		return moderatorId;
	}

	public void setModeratorId(long moderatorId) {
		this.moderatorId = moderatorId;
	}

	public long getProjectId() {
		return projectId;
	}

	public void setProjectId(long projectId) {
		this.projectId = projectId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public boolean isSeenByUser() {
		return seenByUser;
	}

	public void setSeenByUser(boolean seenByUser) {
		this.seenByUser = seenByUser;
	}
	
	
	
	
}
