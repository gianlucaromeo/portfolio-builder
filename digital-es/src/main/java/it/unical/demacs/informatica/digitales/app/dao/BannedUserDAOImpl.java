package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.SQLException;
import java.sql.Types;

import org.springframework.security.crypto.bcrypt.BCrypt;

import it.unical.demacs.informatica.digitales.app.beans.BannedUser;
import it.unical.demacs.informatica.digitales.app.database.DBUtil;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class BannedUserDAOImpl extends DAOImpl implements DAO<BannedUser> {
	
	private static BannedUserDAOImpl instance = null;
	
	public static BannedUserDAOImpl getInstance() {
		if (instance == null) {
			instance = new BannedUserDAOImpl();
		}
		return instance;
	}
	
	private BannedUserDAOImpl() {
		
	}
	
	@Override
	public String create(BannedUser bannedUser) {
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "INSERT INTO banned_users VALUES(?,?,DEFAULT,?,?,?);";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setLong(1, bannedUser.getUserId());
			p.setLong(2,bannedUser.getModeratorId());
			p.setString(3, bannedUser.getReason());
			p.setString(4, bannedUser.getDateStart());
			p.setString(5, bannedUser.getDateEnd());
			
			p.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("[BanendUsersDAOImpl] [create]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;
		
	}

	@Override
	public String update(BannedUser t) {
		return Protocol.OK;
	}
	
}
