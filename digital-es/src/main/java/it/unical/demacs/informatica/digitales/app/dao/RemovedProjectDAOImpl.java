package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.SQLException;
import java.sql.Types;

import org.springframework.security.crypto.bcrypt.BCrypt;

import it.unical.demacs.informatica.digitales.app.beans.RemovedProject;
import it.unical.demacs.informatica.digitales.app.database.DBUtil;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class RemovedProjectDAOImpl extends DAOImpl implements DAO<RemovedProject> {

	private static RemovedProjectDAOImpl instance = null;
	
	public static RemovedProjectDAOImpl getInstance() {
		if (instance == null) {
			instance = new RemovedProjectDAOImpl();
		}
		return instance;
	}
	
	private RemovedProjectDAOImpl() {
	}
	
	@Override
	public String create(RemovedProject removedProject) {
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "INSERT INTO removed_projects VALUES(DEFAULT,?,?,?,?);";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setLong(1, removedProject.getModeratorId());
			p.setLong(2, removedProject.getProjectId());
			p.setString(3, removedProject.getReason());
			p.setBoolean(4, removedProject.isSeenByUser());
			
			p.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("[RemovedProjectDAOImpl] [create]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;
		
	}

	@Override
	public String update(RemovedProject removedProject) {

		con = DBUtil.getInstance().getConnection();
		
		String query = "UPDATE removed_projects SET moderator_id=?,project_id=?,reason=?,seen_by_user=? WHERE id=?;";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setLong(1, removedProject.getModeratorId());
			p.setLong(2, removedProject.getProjectId());
			p.setString(3, removedProject.getReason());
			p.setBoolean(4, removedProject.isSeenByUser());
			p.setLong(5, removedProject.getId());
			
			p.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("[RemovedProjectDAOImpl] [update]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;
		
	}
	
	@Override
	public long findId(RemovedProject t) {
		// TODO Auto-generated method stub
		return 0;
	}
	
}
