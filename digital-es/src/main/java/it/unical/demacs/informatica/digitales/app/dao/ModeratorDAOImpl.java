package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.security.crypto.bcrypt.BCrypt;

import it.unical.demacs.informatica.digitales.app.beans.Moderator;
import it.unical.demacs.informatica.digitales.app.database.DBUtil;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class ModeratorDAOImpl extends DAOImpl implements DAO<Moderator>{
	
	private int SALT = 12;
	
	@Override
	public String create(Moderator moderator) {

		con = DBUtil.getInstance().getConnection();
		
		String query = "INSERT INTO moderators VALUES(?,?,?,?);";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setNull(1, Types.INTEGER);
			p.setString(2,moderator.getUsername());
			p.setString(3, BCrypt.hashpw(moderator.getPassword(), BCrypt.gensalt(SALT)));
			p.setString(4, moderator.getEmail());
			
			p.executeUpdate(query);
			
		} catch (SQLException e) {
			System.err.println("[ModeratorDAOImpl] [create]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;
		
	}

}
