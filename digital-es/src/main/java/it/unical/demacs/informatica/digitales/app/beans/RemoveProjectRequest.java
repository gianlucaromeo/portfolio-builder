package it.unical.demacs.informatica.digitales.app.beans;

public class RemoveProjectRequest {

	private long id;
	private String reason;

	public RemoveProjectRequest() {}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

}
