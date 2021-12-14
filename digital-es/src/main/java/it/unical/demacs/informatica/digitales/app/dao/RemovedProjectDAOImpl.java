package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.SQLException;
import java.sql.Types;

import org.springframework.security.crypto.bcrypt.BCrypt;

import it.unical.demacs.informatica.digitales.app.beans.RemovedProject;
import it.unical.demacs.informatica.digitales.app.database.DBUtil;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class RemovedProjectDAOImpl extends DAOImpl implements DAO<RemovedProject> {

	@Override
	public String create(RemovedProject removedProject) {
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "INSERT INTO removed_projects VALUES(?,?,?,?,?);";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setNull(1, Types.INTEGER);
			p.setLong(2, removedProject.getModeratorId());
			p.setLong(3, removedProject.getProjectId());
			p.setString(4, removedProject.getReason());
			p.setBoolean(5, removedProject.isSeenByUser());
			
			p.executeUpdate(query);
			
		} catch (SQLException e) {
			System.err.println("[RemovedProjectDAOImpl] [create]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;
		
	}
	
}
