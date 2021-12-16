package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.SQLException;
import java.sql.Types;import it.unical.demacs.informatica.digitales.app.beans.CurriculumSkill;
import it.unical.demacs.informatica.digitales.app.dao.DAOImpl;
import it.unical.demacs.informatica.digitales.app.database.DBUtil;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class CurriculumSkillDAOImpl extends DAOImpl implements DAO<CurriculumSkill>{
	
	private static CurriculumSkillDAOImpl instance= null;
	
	private CurriculumSkillDAOImpl() {}
	
	public static CurriculumSkillDAOImpl getInstance(){
		if(instance==null)
			instance= new CurriculumSkillDAOImpl();
		return instance;
	}

	@Override
	public String create(CurriculumSkill curriculumSkill) {
		con = DBUtil.getInstance().getConnection();
		
		String query = "INSERT INTO curriculum_skills VALUES(DEFAULT,?,?,?);";
		
		try {
			
			p = con.prepareStatement(query);
			
			
			p.setLong(1, curriculumSkill.getUserId());
			p.setString(2, curriculumSkill.getTitle());
			p.setInt(3, curriculumSkill.getLevel());
			
			p.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("[CurriculumSkillDAOImpl] [create]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;
		
	
	}

	@Override
	public String update(CurriculumSkill t) {
		return Protocol.OK;
	}
	

}
