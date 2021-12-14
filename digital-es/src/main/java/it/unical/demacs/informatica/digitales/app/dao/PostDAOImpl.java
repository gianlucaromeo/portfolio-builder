package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import it.unical.demacs.informatica.digitales.app.beans.Post;
import it.unical.demacs.informatica.digitales.app.database.DBUtil;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class PostDAOImpl implements DAO<Post> {
	
	private Connection con = null;
	private PreparedStatement p = null;
	private ResultSet rs = null;
	
	@Override
	public String create(Post post) {

		con = DBUtil.getInstance().getConnection();
		
		String query = "INSERT INTO posts VALUES();";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setNull(1, Types.INTEGER);
			p.setString(2, post.getTitle());
			p.setString(3, post.getDescription());
			p.setString(4, post.getPicture());
			p.setString(5, post.getPubblicationDate());
			p.setString(6, post.getLastEditDate());
			p.setString(7, post.getRefLink());
			p.setLong(8, post.getUserId());
			
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

	private void closeAll() {
		DBUtil.getInstance().closeAll(rs, p);
	}
	
}
