package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.unical.demacs.informatica.digitales.app.beans.RemovedPost;
import it.unical.demacs.informatica.digitales.app.database.DBUtil;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class RemovedPostDAOImpl extends DAOImpl implements DAO<RemovedPost> {

	private static RemovedPostDAOImpl instance = null;

	private RemovedPostDAOImpl() {}

	public static RemovedPostDAOImpl getInstance() {
		if (instance == null) {
			instance = new RemovedPostDAOImpl();
		}
		return instance;
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
	public long findId(RemovedPost removedPost) {

		long id = -1; // target

		con = DBUtil.getInstance().getConnection();

		String query = "SELECT id from removed_posts WHERE post_id=?;";

		try {

			p = con.prepareStatement(query);

			p.setLong(1, removedPost.getPostId());

			rs = p.executeQuery();

			if (rs.next()) {
				id = rs.getLong("id");
			}

		} catch (SQLException e) {
			System.err.println("[RemovedPostDAOImpl] [findId]: ");
			e.printStackTrace();
			return id;
		} finally {
			closeAll();
		}

		return id;
	}

	@Override
	public RemovedPost findById(long id) {

		RemovedPost removedPost = null;

		con = DBUtil.getInstance().getConnection();

		String query = "SELECT * FROM removed_posts WHERE id=?;";

		try {

			p = con.prepareStatement(query);
			p.setLong(1, id);

			rs = p.executeQuery();

			if (rs.next()) {
				removedPost = new RemovedPost();
				removedPost.setModeratorId(rs.getLong("moderator_id"));
				removedPost.setReason(rs.getString("reason"));
				removedPost.setPostId(rs.getLong("post_id"));
				removedPost.setSeenByUser(rs.getBoolean("seen_by_user"));
				removedPost.setId(id);
			}

		} catch (SQLException e) {
			System.err.println("[RemovedPostDAOImpl] [findById]: ");
			e.printStackTrace();
		} finally {
			closeAll();
		}

		return removedPost;
	}

	@Override
	public Set<RemovedPost> findAll() {

		Set<RemovedPost> posts = new HashSet<RemovedPost>();

		con = DBUtil.getInstance().getConnection();

		String query = "SELECT * FROM removed_posts;";

		try {

			p = con.prepareStatement(query);

			rs = p.executeQuery();

			while (rs.next()) {
				RemovedPost removedPost = new RemovedPost();
				removedPost.setModeratorId(rs.getLong("moderator_id"));
				removedPost.setReason(rs.getString("reason"));
				removedPost.setPostId(rs.getLong("post_id"));
				removedPost.setSeenByUser(rs.getBoolean("seen_by_user"));
				removedPost.setId(rs.getLong("id"));
				posts.add(removedPost);
			}

		} catch (SQLException e) {
			System.err.println("[RemovedPostDAOImpl] [findAll]: ");
			e.printStackTrace();
		} finally {
			closeAll();
		}

		return posts;
	}

	@Override
	public String delete(RemovedPost post) {

		con = DBUtil.getInstance().getConnection();

		String query = "DELETE from removed_posts WHERE id=?";

		try {

			p = con.prepareStatement(query);
			p.setLong(1, post.getId());
			p.executeUpdate();

		} catch (SQLException e) {
			System.err.println("RemovedPostDAOImpl] [delete]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}

		return Protocol.OK;
	}

	public synchronized List<RemovedPost> findByUserId(long id) {
		List<RemovedPost> posts = new ArrayList<RemovedPost>();

		con = DBUtil.getInstance().getConnection();

		String query = "SELECT * FROM removed_posts WHERE post_id IN (SELECT id FROM posts WHERE user_id=?);";

		try {

			p = con.prepareStatement(query);
			p.setLong(1, id);
			rs = p.executeQuery();

			while (rs.next()) {
				RemovedPost removedPost = new RemovedPost();
				removedPost.setModeratorId(rs.getLong("moderator_id"));
				removedPost.setReason(rs.getString("reason"));
				removedPost.setPostId(rs.getLong("post_id"));
				removedPost.setSeenByUser(rs.getBoolean("seen_by_user"));
				removedPost.setId(rs.getLong("id"));
				posts.add(removedPost);
			}

		} catch (SQLException e) {
			System.err.println("[RemovedPostDAOImpl] [findByUserId]: ");
			e.printStackTrace();
		} finally {
			closeAll();
		}

		return posts;
	}

}
