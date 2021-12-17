package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.SQLException;
import java.sql.Types;
import java.util.HashSet;
import java.util.Set;

import it.unical.demacs.informatica.digitales.app.beans.Curriculum;
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
	public long findId(CurriculumExperience curriculumEx) {

		long id = -1; // target
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "SELECT id from curriculum_experiences WHERE user_id=? AND title=? AND date_start=?;";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setLong(1, curriculumEx.getUserId());
			p.setString(2, curriculumEx.getTitle());
			p.setString(3, curriculumEx.getStartDate());
			
			
			rs = p.executeQuery();
			
			if (rs.next()) {
				id = rs.getLong("id");
			}
			
		} catch (SQLException e) {
			System.err.println("[CurriculumExperienceDAOImpl] [findId]: ");
			e.printStackTrace();
			return id;
		} finally {
			closeAll();
		}
		
		return id;
	}

	@Override
	public CurriculumExperience findById(long id) {
		CurriculumExperience curriculumEx = null;
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "SELECT * FROM curriculum_experiences WHERE id=?;";
		
		try {
			
			p = con.prepareStatement(query);
			p.setLong(1, id);
			
			rs = p.executeQuery();
			
			if (rs.next()) {
				curriculumEx= new CurriculumExperience();
				curriculumEx.setId(id);
				curriculumEx.setUserId(rs.getLong("user_id"));
				curriculumEx.setTitle(rs.getString("title"));
				curriculumEx.setPlace(rs.getString("place"));
				curriculumEx.setStartDate(rs.getString("date_start"));
				curriculumEx.setEndDate(rs.getString("end_date"));
				curriculumEx.setDescription(rs.getString("description"));
				curriculumEx.setType(rs.getString("type"));
			
			}
	
		} catch (SQLException e) {
			System.err.println("[CurriculumExperienceDAOImpl] [findById]: ");
			e.printStackTrace();
		} finally {
			closeAll();
		}
		
		return curriculumEx;
	}
	
	@Override
	public Set<CurriculumExperience> findAll() {
		Set<CurriculumExperience> curriculums = new HashSet<CurriculumExperience>();
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "SELECT * FROM curriculum_experiences;";

		try {
			
			p = con.prepareStatement(query);
			
			rs = p.executeQuery();
			
			while (rs.next()) {
				CurriculumExperience curriculumEx = new CurriculumExperience();
				curriculumEx.setId(rs.getLong("id"));
				curriculumEx.setUserId(rs.getLong("user_id"));
				curriculumEx.setTitle(rs.getString("title"));
				curriculumEx.setPlace(rs.getString("place"));
				curriculumEx.setStartDate(rs.getString("date_start"));
				curriculumEx.setEndDate(rs.getString("end_date"));
				curriculumEx.setDescription(rs.getString("description"));
				curriculumEx.setType(rs.getString("type"));
				curriculums.add(curriculumEx);
			}
			
		} catch (SQLException e) {
			System.err.println("[CurriculumExperienceDAOImpl] [findAll]: ");
			e.printStackTrace();
		} finally {
			closeAll();
		}
		
		return curriculums;
	}
	
}
