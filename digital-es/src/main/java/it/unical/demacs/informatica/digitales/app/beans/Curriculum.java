package it.unical.demacs.informatica.digitales.app.beans;

public class Curriculum {
	private long id; 
	private long userId;
	private String hobbiesDescription;
	
	
	public Curriculum(long id, long userId, String hobbiesDescription) {
		super();
		this.id = id;
		this.userId = userId;
		this.hobbiesDescription = hobbiesDescription;
	}
	
	public Curriculum() {
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
	public String getHobbiesDescription() {
		return hobbiesDescription;
	}
	public void setHobbiesDescription(String hobbiesDescription) {
		this.hobbiesDescription = hobbiesDescription;
	}

	
	

}
