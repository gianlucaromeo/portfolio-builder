package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.SQLException;
import java.sql.Types;

import it.unical.demacs.informatica.digitales.app.beans.CurriculumExperience;
import it.unical.demacs.informatica.digitales.app.dao.DAOImpl;
import it.unical.demacs.informatica.digitales.app.database.DBUtil;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class CurriculumExperienceDAOImpl extends DAOImpl implements DAO<CurriculumExperience>{
	private static CurriculumExperienceDAOImpl instance= null;
	
	private CurriculumExperienceDAOImpl() {}
	
	public static CurriculumExperienceDAOImpl getInstance(){
		if(instance==null)
			instance= new CurriculumExperienceDAOImpl();
		return instance;
	}


	@Override
	public String create(CurriculumExperience curriculumExperience) {
		con = DBUtil.getInstance().getConnection();
		
		String query = "INSERT INTO curriculum_experiences VALUES(DEFAULT,?,?,?,?,?,?,?);";
		
		try {
			
			p = con.prepareStatement(query);
			
			
			p.setLong(1, curriculumExperience.getUserId());
			p.setString(2, curriculumExperience.getTitle());
			p.setString(3, curriculumExperience.getPlace());
			p.setString(4, curriculumExperience.getStartDate());
			p.setString(5, curriculumExperience.getEndDate());
			p.setString(6, curriculumExperience.getDescription());
			p.setString(7, curriculumExperience.getType());
			
			p.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("[CurriculumExperiencesDAOImpl] [create]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;
		
	}

	@Override
	public String update(CurriculumExperience curriculumEx) {
		con = DBUtil.getInstance().getConnection();
		String query = "UPDATE curriculum_experiences SET user_id=?, title=?, place=?, date_start=?, end_date=?, description=?, type=? WHERE id=?;";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setLong(1, curriculumEx.getUserId());
			p.setString(2, curriculumEx.getTitle());
			p.setString(3, curriculumEx.getPlace());
			p.setString(4, curriculumEx.getStartDate());
			p.setString(5, curriculumEx.getEndDate());
			p.setString(6, curriculumEx.getDescription());
			p.setString(7, curriculumEx.getType());
			p.setLong(8, curriculumEx.getId());
			
			p.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("[CurriculumExperienceDAOImpl] [update]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;
	}
	
	@Override
	public long findId(CurriculumExperience t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
