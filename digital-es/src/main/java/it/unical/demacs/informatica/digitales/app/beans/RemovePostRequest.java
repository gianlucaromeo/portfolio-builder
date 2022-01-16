package it.unical.demacs.informatica.digitales.app.beans;

public class RemovePostRequest {

	private Integer postId;
	private String reason;

	public RemovePostRequest() {}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
