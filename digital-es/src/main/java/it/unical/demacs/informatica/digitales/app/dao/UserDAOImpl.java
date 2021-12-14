package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import org.springframework.security.crypto.bcrypt.BCrypt;

import it.unical.demacs.informatica.digitales.app.dao.DAOImpl;
import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.database.DBUtil;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class UserDAOImpl extends DAOImpl implements DAO<User>  {

	private int SALT = 12;

	
	@Override
	public String create(User user) {

		con = DBUtil.getInstance().getConnection();
		
		String query = "INSERT INTO users VALUES(?,?,?,?,?,?,?,?,?,?);";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setNull(1, Types.INTEGER);
			p.setString(2, user.getFirstName());
			p.setString(3, user.getLastName());
			p.setString(4, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(SALT)));
			p.setString(5, user.getEmail());
			p.setString(6, user.getDateOfBirth().toString("YYYY-MM-dd"));
			p.setString(7, user.getMainPhoneNumber());
			p.setString(8, user.getSecondaryPhoneNumber());
			p.setString(9, user.getContactEmail());
			p.setBoolean(10, user.isConfirmed());
			
			p.executeUpdate(query);
			
		} catch (SQLException e) {
			System.err.println("[UserDAOImpl] [create]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;
		
	}

	
}
