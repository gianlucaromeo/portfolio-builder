package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.SQLException;
import java.sql.Types;

import org.springframework.security.crypto.bcrypt.BCrypt;

import it.unical.demacs.informatica.digitales.app.beans.BannedUser;
import it.unical.demacs.informatica.digitales.app.database.DBUtil;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class BannedUserDAOImpl extends DAOImpl implements DAO<BannedUser> {

	@Override
	public String create(BannedUser bannedUser) {
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "INSERT INTO banned_users VALUES(?,?,?,?,?,?);";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setNull(1, Types.INTEGER);
			p.setLong(2, bannedUser.getUserId());
			p.setLong(3,bannedUser.getModeratorId());
			p.setString(4, bannedUser.getReason());
			p.setString(5, bannedUser.getDateStart());
			p.setString(6, bannedUser.getDateEnd());
			
			p.executeUpdate(query);
			
		} catch (SQLException e) {
			System.err.println("[BanendUsersDAOImpl] [create]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;
		
	}
	
}
