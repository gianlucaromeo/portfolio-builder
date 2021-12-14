package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.security.crypto.bcrypt.BCrypt;

import it.unical.demacs.informatica.digitales.app.beans.Curriculum;
import it.unical.demacs.informatica.digitales.app.beans.DAOImpl;
import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.database.DBUtil;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class CurriculumDAOImpl extends DAOImpl implements DAO<Curriculum> {


	@Override
	public String create(Curriculum curriculum) {
	con = DBUtil.getInstance().getConnection();
		
		String query = "INSERT INTO curriculum VALUES(?,?,?);";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setNull(1, Types.INTEGER);
			p.setLong(2, curriculum.getUserId());
			p.setString(3, curriculum.getHobbiesDescription());
			p.executeUpdate(query);
			
		} catch (SQLException e) {
			System.err.println("[CurriculumDAOImpl] [create]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;
		
	}
	


}
