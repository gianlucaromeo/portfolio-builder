package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.SQLException;
import java.sql.Types;
import java.util.HashSet;
import java.util.Set;

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
	public String update(BannedUser bannedUser) {
		con = DBUtil.getInstance().getConnection();
		
		String query = "UPDATE banned_users SET user_id=?, moderator_id=?, reason=?, date_start=?, date_end=? WHERE id=?;";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setLong(1, bannedUser.getUserId());
			p.setLong(2, bannedUser.getModeratorId());
			p.setString(3, bannedUser.getReason());
			p.setString(4, bannedUser.getDateStart());
			p.setString(5, bannedUser.getDateEnd());
			p.setLong(6, bannedUser.getId());
			
			p.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("[BannedUserDAOImpl] [update]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;
	}
	
	@Override
	public long findId(BannedUser bannedUser) {
		
		long id = -1; // target
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "SELECT id from banned_users WHERE user_id=?;";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setLong(1, bannedUser.getUserId());
			
			rs = p.executeQuery();
			
			if (rs.next()) {
				id = rs.getLong("id");
			}
			
		} catch (SQLException e) {
			System.err.println("[BannedUserDAOImpl] [findId]: ");
			e.printStackTrace();
			return id;
		} finally {
			closeAll();
		}
		
		return id;
		
	}
	
	@Override
	public BannedUser findById(long id) {

		BannedUser bannedUser = null;
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "SELECT * FROM banned_users WHERE id=?;";
		
		try {
			
			p = con.prepareStatement(query);
			p.setLong(1, id);
			
			rs = p.executeQuery();
			
			if (rs.next()) {
				bannedUser = new BannedUser();
				bannedUser.setId(id);
				bannedUser.setUserId(rs.getLong("user_id"));
				bannedUser.setModeratorId(rs.getLong("moderator_id"));
				bannedUser.setReason(rs.getString("reason"));
				bannedUser.setDateStart(rs.getString("date_start"));
				bannedUser.setDateEnd(rs.getString("date_end"));
			}
	
		} catch (SQLException e) {
			System.err.println("[BannedUserDAOImpl] [findById]: ");
			e.printStackTrace();
		} finally {
			closeAll();
		}
		
		return bannedUser;
		
	}
	
	@Override
	public Set<BannedUser> findAll() {
		
		Set<BannedUser> users = new HashSet<BannedUser>();
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "SELECT * FROM banned_users;";

		try {
			
			p = con.prepareStatement(query);
			
			rs = p.executeQuery();
			
			while (rs.next()) {
				BannedUser bannedUser = new BannedUser();
				bannedUser.setId(rs.getLong("id"));
				bannedUser.setUserId(rs.getLong("user_id"));
				bannedUser.setModeratorId(rs.getLong("moderator_id"));
				bannedUser.setReason(rs.getString("reason"));
				bannedUser.setDateStart(rs.getString("date_start"));
				bannedUser.setDateEnd(rs.getString("date_end"));
				users.add(bannedUser);
			}
			
		} catch (SQLException e) {
			System.err.println("[BannedUserDAOImpl] [findAll]: ");
			e.printStackTrace();
		} finally {
			closeAll();
		}
		
		return users;
		
	}
	
	@Override
	public String delete(BannedUser t) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
