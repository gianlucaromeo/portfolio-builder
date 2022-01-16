package it.unical.demacs.informatica.digitales.app.beans;

import java.util.Objects;

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

	@Override
	public int hashCode() {
		return Objects.hash(dateEnd, dateStart, id, moderatorId, reason, userId);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		BannedUser other = (BannedUser) obj;
		return Objects.equals(dateEnd, other.dateEnd) && Objects.equals(dateStart, other.dateStart) && id == other.id
				&& moderatorId == other.moderatorId && Objects.equals(reason, other.reason) && userId == other.userId;
	}

	@Override
	public String toString() {
		return "BannedUser [id=" + id + ", userId=" + userId + ", moderatorId=" + moderatorId + ", reason=" + reason
				+ ", dateStart=" + dateStart + ", dateEnd=" + dateEnd + "]";
	}

}
