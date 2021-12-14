package it.unical.demacs.informatica.digitales.app.beans;

public class CurriculumSkills {
	
	private long id;
	private long userId;
	private long curriculumId;
	private String title;
	private int level;
	
	
	public CurriculumSkills(long id, long userId, long curriculumId, String title, int level) {
		super();
		this.id = id;
		this.userId = userId;
		this.curriculumId = curriculumId;
		this.title = title;
		this.level = level;
	}
	
	
	public CurriculumSkills() {
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
