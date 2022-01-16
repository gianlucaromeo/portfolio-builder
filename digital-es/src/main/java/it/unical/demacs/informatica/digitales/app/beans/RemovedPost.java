package it.unical.demacs.informatica.digitales.app.beans;

import java.util.Objects;

public class RemovedPost {

	private long id;
	private long moderatorId;
	private String reason;
	private long postId;
	private boolean seenByUser;

	public RemovedPost() {}

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

	@Override
	public int hashCode() {
		return Objects.hash(id, moderatorId, postId, reason, seenByUser);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RemovedPost other = (RemovedPost) obj;
		return id == other.id && moderatorId == other.moderatorId && postId == other.postId
				&& Objects.equals(reason, other.reason) && seenByUser == other.seenByUser;
	}

	@Override
	public String toString() {
		return "RemovedPost [id=" + id + ", moderatorId=" + moderatorId + ", reason=" + reason + ", postId=" + postId
				+ ", seenByUser=" + seenByUser + "]";
	}

}
