package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.SQLException;
import java.sql.Types;


import it.unical.demacs.informatica.digitales.app.beans.Project;
import it.unical.demacs.informatica.digitales.app.database.DBUtil;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class ProjectDAOImpl extends DAOImpl implements DAO<Project>{
	@Override
	public String create(Project project) {

		con = DBUtil.getInstance().getConnection();
		
		String query = "INSERT INTO projects VALUES(?,?,?,?,?,?);";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setNull(1, Types.INTEGER);
			p.setLong(2,project.getId());
			p.setString(3,project.getTitle());
			p.setString(4, project.getDescription());
			p.setString(5, project.getPicture());
			p.setString(6, project.getLinkRef());
			
			p.executeUpdate(query);
			
		} catch (SQLException e) {
			System.err.println("[ProjectDAOImpl] [create]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;
		
	}

}
