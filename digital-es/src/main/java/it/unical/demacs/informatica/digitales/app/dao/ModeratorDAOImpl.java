package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.SQLException;
import org.springframework.security.crypto.bcrypt.BCrypt;

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
	public long findId(Moderator t) {
		// TODO Auto-generated method stub
		return 0;
	}

}
