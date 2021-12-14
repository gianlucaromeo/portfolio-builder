package it.unical.demacs.informatica.digitales.app.beans;

public class CurriculumExperiences {
	private long id;
	private long userId;
	private long curriculumId;
	private String title;
	private String place;
	private String startDate;
	private String endDate;
	private String description;
	private String type;
	
	public CurriculumExperiences(long id, long userId, long curriculumId, String title, String place, String startDate,
			String endDate, String description, String type) {
		super();
		this.id = id;
		this.userId = userId;
		this.curriculumId = curriculumId;
		this.title = title;
		this.place = place;
		this.startDate = startDate;
		this.endDate = endDate;
		this.description = description;
		this.type = type;
	}
	
	
	
	public CurriculumExperiences() {
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
	public long getCurriculumId() {
		return curriculumId;
	}
	public void setCurriculumId(long curriculumId) {
		this.curriculumId = curriculumId;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPlace() {
		return place;
	}
	public void setPlace(String place) {
		this.place = place;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
	
	
	

}
