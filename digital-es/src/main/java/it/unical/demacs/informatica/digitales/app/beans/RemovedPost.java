package it.unical.demacs.informatica.digitales.app.beans;

public class RemovedPost {

	private long id;
	private long moderatorId;
	private String reason;
	private long postId;
	private boolean seenByUser;
	
	public RemovedPost() {
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
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public long getPostId() {
		return postId;
	}
	public void setPostId(long postId) {
		this.postId = postId;
	}
	public boolean isSeenByUser() {
		return seenByUser;
	}
	public void setSeenByUser(boolean seenByUser) {
		this.seenByUser = seenByUser;
	}
	
	
	
}
