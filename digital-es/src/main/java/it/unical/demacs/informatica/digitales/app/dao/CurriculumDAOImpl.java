package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCrypt;

import it.unical.demacs.informatica.digitales.app.beans.BannedUser;
import it.unical.demacs.informatica.digitales.app.beans.Curriculum;
import it.unical.demacs.informatica.digitales.app.dao.DAOImpl;
import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.database.DBUtil;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class CurriculumDAOImpl extends DAOImpl implements DAO<Curriculum> {

	private static CurriculumDAOImpl instance= null;
	
	private CurriculumDAOImpl() {}
	
	public static CurriculumDAOImpl getInstance(){
		if(instance==null)
			instance= new CurriculumDAOImpl();
		return instance;
	}
	
	@Override
	public String create(Curriculum curriculum) {
	con = DBUtil.getInstance().getConnection();
		
		String query = "INSERT INTO curriculum VALUES(?,?,DEFAULT);";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setLong(1, curriculum.getUserId());
			p.setString(2, curriculum.getHobbiesDescription());
			p.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("[CurriculumDAOImpl] [create]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;
		
	}

	@Override
	public String update(Curriculum curriculum) {
		
		con = DBUtil.getInstance().getConnection();
		String query = "UPDATE curriculum SET user_id=?, hobbies_descr=? WHERE id=?;";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setLong(1, curriculum.getUserId());
			p.setString(2, curriculum.getHobbiesDescription());
			p.setLong(3, curriculum.getId());
			
			
			p.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("[CurriculumDAOImpl] [update]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;
	}
	
	@Override
	public long findId(Curriculum curriculum) {

		long id = -1; // target
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "SELECT id from curriculum WHERE user_id=?;";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setLong(1, curriculum.getUserId());
			
			rs = p.executeQuery();
			
			if (rs.next()) {
				id = rs.getLong("id");
			}
			
		} catch (SQLException e) {
			System.err.println("[CurriculumDAOImpl] [findId]: ");
			e.printStackTrace();
			return id;
		} finally {
			closeAll();
		}
		
		return id;
	}
	
	@Override
	public Curriculum findById(long id) {

		Curriculum curriculum = null;
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "SELECT * FROM curriculum WHERE id=?;";
		
		try {
			
			p = con.prepareStatement(query);
			p.setLong(1, id);
			
			rs = p.executeQuery();
			
			if (rs.next()) {
				curriculum= new Curriculum();
				curriculum.setId(id);
				curriculum.setUserId(rs.getLong("user_id"));
				curriculum.setHobbiesDescription(rs.getString("hobbies_descr"));
			}
	
		} catch (SQLException e) {
			System.err.println("[CurriculumDAOImpl] [findById]: ");
			e.printStackTrace();
		} finally {
			closeAll();
		}
		
		return curriculum;
	}

	@Override
	public Set<Curriculum> findAll() {
		Set<Curriculum> curriculums = new HashSet<Curriculum>();
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "SELECT * FROM curriculum;";

		try {
			
			p = con.prepareStatement(query);
			
			rs = p.executeQuery();
			
			while (rs.next()) {
				Curriculum curriculum = new Curriculum();
				curriculum.setId(rs.getLong("id"));
				curriculum.setUserId(rs.getLong("user_id"));
				curriculum.setHobbiesDescription(rs.getString("hobbies_descr"));
				curriculums.add(curriculum);
			}
			
		} catch (SQLException e) {
			System.err.println("[CurriculumDAOImpl] [findAll]: ");
			e.printStackTrace();
		} finally {
			closeAll();
		}
		
		return curriculums;
	}

}
