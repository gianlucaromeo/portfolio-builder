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
	public String update(CurriculumSkill curriculumSkill) {

		con = DBUtil.getInstance().getConnection();
		
		String query = "UPDATE curriculum_skills SET user_id=?, title=?, level=? WHERE id=?;";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setLong(1, curriculumSkill.getUserId());
			p.setString(2, curriculumSkill.getTitle());
			p.setInt(3, curriculumSkill.getLevel());
			p.setLong(4, curriculumSkill.getId());
			
			p.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("[CurriculumSkillDAOImpl] [update]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;
		
	}
	
	@Override
	public long findId(CurriculumSkill curriculumSkill) {
		
		long id = -1; // target
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "SELECT id from curriculum_skills WHERE user_id=?, title=?;";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setLong(1, curriculumSkill.getUserId());
			p.setString(2, curriculumSkill.getTitle());
			
			rs = p.executeQuery();
			
			if (rs.next()) {
				id = rs.getLong("id");
			}
			
		} catch (SQLException e) {
			System.err.println("[CurriculumSkillDAOImpl] [findId]: ");
			e.printStackTrace();
			return id;
		} finally {
			closeAll();
		}
		
		return id;
	}
	

}
