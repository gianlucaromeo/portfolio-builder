package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.security.crypto.bcrypt.BCrypt;

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
	public boolean update(Curriculum t) {
		// TODO Auto-generated method stub
		return false;
	}
	


}
