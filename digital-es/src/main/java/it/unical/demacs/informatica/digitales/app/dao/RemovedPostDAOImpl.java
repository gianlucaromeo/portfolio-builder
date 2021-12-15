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
		
		String query = "INSERT INTO removed_projects VALUES(?,?,?,?,?);";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setNull(1, Types.INTEGER);
			p.setLong(2, removedPost.getModeratorId());
			p.setString(3, removedPost.getReason());
			p.setLong(4, removedPost.getPostId());
			p.setBoolean(5, removedPost.isSeenByUser());
			
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
}
