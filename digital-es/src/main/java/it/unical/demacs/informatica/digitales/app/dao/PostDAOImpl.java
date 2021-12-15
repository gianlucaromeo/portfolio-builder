package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.SQLException;
import java.sql.Types;

import it.unical.demacs.informatica.digitales.app.beans.Post;
import it.unical.demacs.informatica.digitales.app.database.DBUtil;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class PostDAOImpl extends DAOImpl implements DAO<Post> {
	
	private static PostDAOImpl instance = null;
	
	public static PostDAOImpl getInstance() {
		if (instance == null) {
			instance = new PostDAOImpl();
		}
		return instance;
	}
	
	private PostDAOImpl() {
	}
	
	
	@Override
	public String create(Post post) {

		con = DBUtil.getInstance().getConnection();
		
		String query = "INSERT INTO posts VALUES(DEFAULT,?,?,?,?,?,?,?);";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setString(1, post.getTitle());
			p.setString(2, post.getDescription());
			p.setString(3, post.getPicture());
			p.setString(4, post.getPubblicationDate());
			p.setString(5, post.getLastEditDate());
			p.setString(6, post.getRefLink());
			p.setLong(7, post.getUserId());
			
			p.executeUpdate(query);
			
		} catch (SQLException e) {
			System.err.println("[PostDAOImpl] [create]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;
		
	}
	
}
