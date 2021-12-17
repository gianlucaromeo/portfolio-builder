package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.SQLException;
import java.sql.Types;
import java.util.HashSet;
import java.util.Set;

import it.unical.demacs.informatica.digitales.app.beans.CurriculumSkill;
import it.unical.demacs.informatica.digitales.app.beans.Post;
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
			
			p.setLong(1,project.getUserId());
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

	@Override
	public String update(Project project) {
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "UPDATE projects SET user_id=?, title=?, description=?, picture=?, link_ref=? WHERE id=?;";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setLong(1,project.getUserId());
			p.setString(2,project.getTitle());
			p.setString(3, project.getDescription());
			p.setString(4, project.getPicture());
			p.setString(5, project.getLinkRef());
			p.setLong(6, project.getId());
			
			p.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("[ProjectDAOImpl] [update]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;
	}
	
	@Override
	public long findId(Project project) {

		long id = -1; // target
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "SELECT id from projects WHERE title=? AND description=? AND user_id=?;";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setString(1, project.getTitle());
			p.setString(2, project.getDescription());
			p.setLong(3, project.getUserId());
			
			rs = p.executeQuery();
			
			if (rs.next()) {
				id = rs.getLong("id");
			}
			
		} catch (SQLException e) {
			System.err.println("[ProjectDAOImpl] [findId]: ");
			e.printStackTrace();
			return id;
		} finally {
			closeAll();
		}
		
		return id;
		
	}
	
	@Override
	public Project findById(long id) {
		
		Project project = null;
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "SELECT * FROM projects WHERE id=?;";
		
		try {
			
			p = con.prepareStatement(query);
			p.setLong(1, id);
			
			rs = p.executeQuery();
			
			if (rs.next()) {
				project= new Project();
				project.setId(id);
				project.setUserId(rs.getLong("user_id"));
				project.setTitle(rs.getString("title"));
				project.setDescription(rs.getString("description"));
				project.setPicture(rs.getString("picture"));
				project.setLinkRef(rs.getString("link_ref"));
			
			}
	
		} catch (SQLException e) {
			System.err.println("[ProjectDAOImpl] [findById]: ");
			e.printStackTrace();
		} finally {
			closeAll();
		}
		
		return project;
	}
	
	@Override
	public Set<Project> findAll() {
		
		Set<Project> projects = new HashSet<Project>();
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "SELECT * FROM projects;";

		try {
			
			p = con.prepareStatement(query);
			
			rs = p.executeQuery();
			
			while (rs.next()) {
				Project project = new Project();
				project.setId(rs.getLong("id"));
				project.setUserId(rs.getLong("user_id"));
				project.setTitle(rs.getString("title"));
				project.setDescription(rs.getString("description"));
				project.setPicture(rs.getString("picture"));
				project.setLinkRef(rs.getString("link_ref"));
				projects.add(project);
			}
			
		} catch (SQLException e) {
			System.err.println("[ProjectDAOImpl] [findAll]: ");
			e.printStackTrace();
		} finally {
			closeAll();
		}
		
		return projects;
		
	}

}
