package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.SQLException;
import java.sql.Types;

import it.unical.demacs.informatica.digitales.app.beans.RemovedPost;
import it.unical.demacs.informatica.digitales.app.database.DBUtil;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class RemovedPostDAOImpl extends DAOImpl implements DAO<RemovedPost>{
	
	private static RemovedPostDAOImpl instance = null;
	
	public static RemovedPostDAOImpl getInstance() {
		if (instance == null) {
			instance = new RemovedPostDAOImpl();
		}
		return instance;
	}
	
	private RemovedPostDAOImpl() {
	}
	
	@Override
	public String create(RemovedPost removedPost) {
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "INSERT INTO removed_posts VALUES(DEFAULT,?,?,?,?);";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setLong(1, removedPost.getModeratorId());
			p.setString(2, removedPost.getReason());
			p.setLong(3, removedPost.getPostId());
			p.setBoolean(4, removedPost.isSeenByUser());
			
			p.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("[RemovedPostDAOImpl] [create]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;
		
	}
	
	@Override
	public String update(RemovedPost removedPost) {
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "UPDATE removed_posts SET moderator_id=?,reason=?,post_id=?,seen_by_user=? WHERE id=?;";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setLong(1, removedPost.getModeratorId());
			p.setString(2, removedPost.getReason());
			p.setLong(3, removedPost.getPostId());
			p.setBoolean(4, removedPost.isSeenByUser());
			p.setLong(5, removedPost.getId());
			
			p.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("[RemovedPostDAOImpl] [update]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;
		
	}
	
	@Override
	public long findId(RemovedPost t) {
		// TODO Auto-generated method stub
		return 0;
	}
}
