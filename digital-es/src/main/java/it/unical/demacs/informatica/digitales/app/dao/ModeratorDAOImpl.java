package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCrypt;

import it.unical.demacs.informatica.digitales.app.beans.Moderator;
import it.unical.demacs.informatica.digitales.app.beans.Moderator;
import it.unical.demacs.informatica.digitales.app.beans.Moderator;
import it.unical.demacs.informatica.digitales.app.database.DBUtil;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class ModeratorDAOImpl extends DAOImpl implements DAO<Moderator>{
	
	private static ModeratorDAOImpl instance = null;
	
	public static ModeratorDAOImpl getInstance() {
		if (instance == null) {
			instance = new ModeratorDAOImpl();
		}
		return instance;
	}
	
	private ModeratorDAOImpl() {
		
	}
	
	private int SALT = 12;
	
	@Override
	public String create(Moderator moderator) {

		con = DBUtil.getInstance().getConnection();
		
		String query = "INSERT INTO moderators VALUES(DEFAULT,?,?,?);";
		
		try {
			
			p = con.prepareStatement(query);

			p.setString(1,moderator.getUsername());
			p.setString(2, BCrypt.hashpw(moderator.getPassword(), BCrypt.gensalt(SALT)));
			p.setString(3, moderator.getEmail());
			
			p.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("[ModeratorDAOImpl] [create]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;
		
	}

	@Override
	public String update(Moderator moderator) {

		con = DBUtil.getInstance().getConnection();
		
		String query = "UPDATE moderators SET username=?,reason=?,password=?,email=? WHERE id=?;";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setString(1,moderator.getUsername());
			p.setString(2, BCrypt.hashpw(moderator.getPassword(), BCrypt.gensalt(SALT)));
			p.setString(3, moderator.getEmail());
			p.setLong(4, moderator.getId());
			
			p.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("[ModeratorDAOImpl] [update]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;
		
	}
	
	@Override
	public long findId(Moderator moderator) {

		long id = -1; // target
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "SELECT id from moderators WHERE username=?;";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setString(1, moderator.getUsername());
			
			rs = p.executeQuery();
			
			if (rs.next()) {
				id = rs.getLong("id");
			}
			
		} catch (SQLException e) {
			System.err.println("[ModeratorDAOImpl] [findId]: ");
			e.printStackTrace();
			return id;
		} finally {
			closeAll();
		}
		
		return id;
		
	}
	
	@Override
	public Moderator findById(long id) {

		Moderator moderator = null;
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "SELECT * FROM moderators WHERE id=?;";
		
		try {
			
			p = con.prepareStatement(query);
			p.setLong(1, id);
			
			rs = p.executeQuery();
			
			if (rs.next()) {
				moderator = new Moderator();
				moderator.setId(id);
				moderator.setUsername(rs.getString("username"));
				moderator.setPassword(rs.getString("password"));
				moderator.setEmail(rs.getString("email"));
			}
	
		} catch (SQLException e) {
			System.err.println("[ModeratorDAOImpl] [findById]: ");
			e.printStackTrace();
		} finally {
			closeAll();
		}
		
		return moderator;
	}
	
	@Override
	public Set<Moderator> findAll() {

		Set<Moderator> moderators = new HashSet<Moderator>();
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "SELECT * FROM moderators;";

		try {
			
			p = con.prepareStatement(query);
			
			rs = p.executeQuery();
			
			while (rs.next()) {
				Moderator moderator = new Moderator();
				moderator.setId(rs.getLong("id"));
				moderator.setUsername(rs.getString("username"));
				moderator.setPassword(rs.getString("password"));
				moderator.setEmail(rs.getString("email"));
				moderators.add(moderator);
			}
			
		} catch (SQLException e) {
			System.err.println("[ModeratorDAOImpl] [findAll]: ");
			e.printStackTrace();
		} finally {
			closeAll();
		}
		
		return moderators;
	}
	
	@Override
	public String delete(Moderator t) {
		// TODO Auto-generated method stub
		return null;
	}

}
