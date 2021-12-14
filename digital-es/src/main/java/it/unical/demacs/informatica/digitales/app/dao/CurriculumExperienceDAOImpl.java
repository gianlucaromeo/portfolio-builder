package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.SQLException;
import java.sql.Types;

import it.unical.demacs.informatica.digitales.app.beans.CurriculumExperience;
import it.unical.demacs.informatica.digitales.app.dao.DAOImpl;
import it.unical.demacs.informatica.digitales.app.database.DBUtil;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class CurriculumExperienceDAOImpl extends DAOImpl implements DAO<CurriculumExperience>{

	@Override
	public String create(CurriculumExperience curriculumExperience) {
		con = DBUtil.getInstance().getConnection();
		
		String query = "INSERT INTO curriculum_experiences VALUES(?,?,?,?,?,?,?,?,?);";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setNull(1, Types.INTEGER);
			p.setLong(2, curriculumExperience.getUserId());
			p.setString(3, curriculumExperience.getTitle());
			p.setString(4, curriculumExperience.getPlace());
			p.setString(5, curriculumExperience.getStartDate());
			p.setString(6, curriculumExperience.getEndDate());
			p.setString(7, curriculumExperience.getDescription());
			p.setString(8, curriculumExperience.getType());
			p.setLong(9, curriculumExperience.getCurriculumId());
			
			p.executeUpdate(query);
			
		} catch (SQLException e) {
			System.err.println("[CurriculumExperiencesDAOImpl] [create]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;
		
	}

}
