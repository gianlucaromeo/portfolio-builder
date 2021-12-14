package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.SQLException;
import java.sql.Types;import it.unical.demacs.informatica.digitales.app.beans.CurriculumSkill;
import it.unical.demacs.informatica.digitales.app.dao.DAOImpl;
import it.unical.demacs.informatica.digitales.app.database.DBUtil;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class CurriculumSkillDAOImpl extends DAOImpl implements DAO<CurriculumSkill>{

	@Override
	public String create(CurriculumSkill curriculumSkill) {
		con = DBUtil.getInstance().getConnection();
		
		String query = "INSERT INTO curriculum_skills VALUES(?,?,?,?,?);";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setNull(1, Types.INTEGER);
			p.setLong(2, curriculumSkill.getUserId());
			p.setString(3, curriculumSkill.getTitle());
			p.setInt(4, curriculumSkill.getLevel());
			p.setLong(5, curriculumSkill.getCurriculumId());
			
			p.executeUpdate(query);
			
		} catch (SQLException e) {
			System.err.println("[CurriculumSkillDAOImpl] [create]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;
		
	
	}
	

}
