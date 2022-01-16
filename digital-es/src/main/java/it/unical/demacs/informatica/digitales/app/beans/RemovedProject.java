package it.unical.demacs.informatica.digitales.app.beans;

import java.util.Objects;

public class RemovedProject {

	private long id;
	private long moderatorId;
	private long projectId;
	private String reason;
	private boolean seenByUser;

	public RemovedProject() {}

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

	@Override
	public int hashCode() {
		return Objects.hash(id, moderatorId, projectId, reason, seenByUser);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RemovedProject other = (RemovedProject) obj;
		return id == other.id && moderatorId == other.moderatorId && projectId == other.projectId
				&& Objects.equals(reason, other.reason) && seenByUser == other.seenByUser;
	}

	@Override
	public String toString() {
		return "RemovedProject [id=" + id + ", moderatorId=" + moderatorId + ", projectId=" + projectId + ", reason="
				+ reason + ", seenByUser=" + seenByUser + "]";
	}

}
