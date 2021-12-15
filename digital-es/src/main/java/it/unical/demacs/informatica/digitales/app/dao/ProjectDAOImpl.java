package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.SQLException;
import java.sql.Types;


import it.unical.demacs.informatica.digitales.app.beans.Project;
import it.unical.demacs.informatica.digitales.app.database.DBUtil;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class ProjectDAOImpl extends DAOImpl implements DAO<Project>{
	
	private static ProjectDAOImpl instance = null;
	
	public static ProjectDAOImpl getInstance() {
		if (instance == null) {
			instance = new ProjectDAOImpl();
		}
		return instance;
	}
	
	private ProjectDAOImpl() {
	}
	
	@Override
	public String create(Project project) {

		con = DBUtil.getInstance().getConnection();
		
		String query = "INSERT INTO projects VALUES(DEFAULT,?,?,?,?,?);";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setLong(1,project.getId());
			p.setString(2,project.getTitle());
			p.setString(3, project.getDescription());
			p.setString(4, project.getPicture());
			p.setString(5, project.getLinkRef());
			
			p.executeUpdate();
			
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
