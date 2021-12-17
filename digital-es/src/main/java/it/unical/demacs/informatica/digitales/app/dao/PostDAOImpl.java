package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.SQLException;
import java.sql.Types;
import java.util.HashSet;
import java.util.Set;

import it.unical.demacs.informatica.digitales.app.beans.BannedUser;
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
			
			p.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("[PostDAOImpl] [create]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;
		
	}

	@Override
	public String update(Post post) {

		con = DBUtil.getInstance().getConnection();
		
		String query = "UPDATE posts SET title=?, description=?, picture=?, publication_date=?, last_edit_date=?, ref_link=?, user_id=? WHERE id=?;";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setString(1, post.getTitle());
			p.setString(2, post.getDescription());
			p.setString(3, post.getPicture());
			p.setString(4, post.getPubblicationDate());
			p.setString(5, post.getLastEditDate());
			p.setString(6, post.getRefLink());
			p.setLong(7, post.getUserId());
			p.setLong(8, post.getId());
			
			p.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("[PostDAOImpl] [update]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;
		
	}
	
	@Override
	public long findId(Post post) {

		long id = -1; // target
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "SELECT id from posts WHERE title=? AND description=? AND publication_date=? AND user_id=?;";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setString(1, post.getTitle());
			p.setString(2, post.getDescription());
			p.setString(3, post.getPubblicationDate());
			p.setLong(4, post.getUserId());
			
			rs = p.executeQuery();
			
			if (rs.next()) {
				id = rs.getLong("id");
			}
			
		} catch (SQLException e) {
			System.err.println("[PostDAOImpl] [findId]: ");
			e.printStackTrace();
			return id;
		} finally {
			closeAll();
		}
		
		return id;
		
	}
	
	@Override
	public Post findById(long id) {

		Post post = null;
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "SELECT * FROM posts WHERE id=?";
		
		try {
			
			p = con.prepareStatement(query);
			p.setLong(1, id);
			
			rs = p.executeQuery();
			
			if (rs.next()) {
				post = new Post();
				post.setId(id);
				post.setTitle(rs.getString("title"));
				post.setDescription(rs.getString("description"));
				post.setPicture(rs.getString("picture"));
				post.setPubblicationDate(rs.getString("publication_date"));
				post.setLastEditDate(rs.getString("last_edit_date"));
				post.setRefLink(rs.getString("ref_link"));
				post.setUserId(rs.getLong("user_id"));
			}
		
		} catch (SQLException e) {
			System.err.println("[PostDAOImpl] [findById]: ");
			e.printStackTrace();
		} finally {
			closeAll();
		}
		
		return post;
		
	}
	
	@Override
	public Set<Post> findAll() {
		
		Set<Post> posts = new HashSet<Post>();
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "SELECT * FROM posts;";

		try {
			
			p = con.prepareStatement(query);
			
			rs = p.executeQuery();
			
			while (rs.next()) {
				Post post = new Post();
				post.setId(rs.getLong("id"));
				post.setTitle(rs.getString("title"));
				post.setDescription(rs.getString("description"));
				post.setPicture(rs.getString("picture"));
				post.setPubblicationDate(rs.getString("publication_date"));
				post.setLastEditDate(rs.getString("last_edit_date"));
				post.setRefLink(rs.getString("ref_link"));
				post.setUserId(rs.getLong("user_id"));
				posts.add(post);
			}
			
		} catch (SQLException e) {
			System.err.println("[PostDAOImpl] [findAll]: ");
			e.printStackTrace();
		} finally {
			closeAll();
		}
		
		return posts;
		
	}
	
}
