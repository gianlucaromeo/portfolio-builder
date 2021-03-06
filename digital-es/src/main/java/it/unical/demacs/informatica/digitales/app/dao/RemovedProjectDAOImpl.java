package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.unical.demacs.informatica.digitales.app.beans.RemovedProject;
import it.unical.demacs.informatica.digitales.app.database.DBUtil;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class RemovedProjectDAOImpl extends DAOImpl implements DAO<RemovedProject> {

	private static RemovedProjectDAOImpl instance = null;

	private RemovedProjectDAOImpl() {}

	public static RemovedProjectDAOImpl getInstance() {
		if (instance == null) {
			instance = new RemovedProjectDAOImpl();
		}
		return instance;
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
	public long findId(RemovedProject removedProject) {

		long id = -1; // target

		con = DBUtil.getInstance().getConnection();

		String query = "SELECT id from removed_projects WHERE project_id=?;";

		try {

			p = con.prepareStatement(query);

			p.setLong(1, removedProject.getProjectId());

			rs = p.executeQuery();

			if (rs.next()) {
				id = rs.getLong("id");
			}

		} catch (SQLException e) {
			System.err.println("[RemovedProjectDAOImpl] [findId]: ");
			e.printStackTrace();
			return id;
		} finally {
			closeAll();
		}

		return id;
	}

	@Override
	public RemovedProject findById(long id) {

		RemovedProject removedProject = null;

		con = DBUtil.getInstance().getConnection();

		String query = "SELECT * FROM removed_projects WHERE id=?;";

		try {

			p = con.prepareStatement(query);
			p.setLong(1, id);

			rs = p.executeQuery();

			if (rs.next()) {
				removedProject = new RemovedProject();
				removedProject.setModeratorId(rs.getLong("moderator_id"));
				removedProject.setReason(rs.getString("reason"));
				removedProject.setProjectId(rs.getLong("project_id"));
				removedProject.setSeenByUser(rs.getBoolean("seen_by_user"));
				removedProject.setId(id);
			}

		} catch (SQLException e) {
			System.err.println("[RemovedProjectDAOImpl] [findById]: ");
			e.printStackTrace();
		} finally {
			closeAll();
		}

		return removedProject;
	}

	@Override
	public Set<RemovedProject> findAll() {

		Set<RemovedProject> projects = new HashSet<RemovedProject>();

		con = DBUtil.getInstance().getConnection();

		String query = "SELECT * FROM removed_projects;";

		try {

			p = con.prepareStatement(query);

			rs = p.executeQuery();

			while (rs.next()) {
				RemovedProject removedProject = new RemovedProject();
				removedProject.setModeratorId(rs.getLong("moderator_id"));
				removedProject.setReason(rs.getString("reason"));
				removedProject.setProjectId(rs.getLong("project_id"));
				removedProject.setSeenByUser(rs.getBoolean("seen_by_user"));
				removedProject.setId(rs.getLong("id"));
				projects.add(removedProject);
			}

		} catch (SQLException e) {
			System.err.println("[RemovedProjectDAOImpl] [findAll]: ");
			e.printStackTrace();
		} finally {
			closeAll();
		}

		return projects;
	}

	@Override
	public String delete(RemovedProject project) {

		con = DBUtil.getInstance().getConnection();

		String query = "DELETE from removed_projects WHERE id=?";

		try {

			p = con.prepareStatement(query);
			p.setLong(1, project.getId());
			p.executeUpdate();

		} catch (SQLException e) {
			System.err.println("RemovedProjectDAOImpl] [delete]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}

		return Protocol.OK;
	}

	public synchronized List<RemovedProject> findByUserId(long id) {
		List<RemovedProject> projects = new ArrayList<RemovedProject>();

		con = DBUtil.getInstance().getConnection();

		String query = "SELECT * FROM removed_projects WHERE project_id IN (SELECT id FROM projects WHERE user_id=?);";

		try {

			p = con.prepareStatement(query);
			p.setLong(1, id);
			rs = p.executeQuery();

			while (rs.next()) {
				RemovedProject removedProject = new RemovedProject();
				removedProject.setModeratorId(rs.getLong("moderator_id"));
				removedProject.setReason(rs.getString("reason"));
				removedProject.setProjectId(rs.getLong("project_id"));
				removedProject.setSeenByUser(rs.getBoolean("seen_by_user"));
				removedProject.setId(rs.getLong("id"));
				projects.add(removedProject);
			}

		} catch (SQLException e) {
			System.err.println("[RemovedPostDAOImpl] [findAll]: ");
			e.printStackTrace();
		} finally {
			closeAll();
		}

		return projects;
	}

}
