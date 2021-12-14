package it.unical.demacs.informatica.digitales.app.beans;

public class CurriculumSkill {
	
	private long id;
	private long userId;
	private long curriculumId;
	private String title;
	private int level;
	

	
	public CurriculumSkill() {
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
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	
	
	
	

}
