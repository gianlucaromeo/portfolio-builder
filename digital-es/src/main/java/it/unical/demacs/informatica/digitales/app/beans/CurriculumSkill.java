package it.unical.demacs.informatica.digitales.app.beans;

import java.util.Objects;

public class CurriculumSkill {
	
	private long id;
	private long userId;
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


	@Override
	public int hashCode() {
		return Objects.hash(id, level, title, userId);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		CurriculumSkill other = (CurriculumSkill) obj;
		return id == other.id && level == other.level && Objects.equals(title, other.title) && userId == other.userId;
	}
	
	
	
	

}
