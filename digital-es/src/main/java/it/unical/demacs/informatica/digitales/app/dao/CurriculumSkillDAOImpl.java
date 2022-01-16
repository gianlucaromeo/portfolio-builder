package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

import it.unical.demacs.informatica.digitales.app.beans.CurriculumSkill;
import it.unical.demacs.informatica.digitales.app.database.DBUtil;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class CurriculumSkillDAOImpl extends DAOImpl implements DAO<CurriculumSkill> {

	private static CurriculumSkillDAOImpl instance = null;

	private CurriculumSkillDAOImpl() {}

	public static CurriculumSkillDAOImpl getInstance() {
		if (instance == null)
			instance = new CurriculumSkillDAOImpl();
		return instance;
	}

	@Override
	public String create(CurriculumSkill curriculumSkill) {
		con = DBUtil.getInstance().getConnection();

		String query = "INSERT INTO curriculum_skills VALUES(DEFAULT,?,?,?);";

		try {

			p = con.prepareStatement(query);

			p.setLong(1, curriculumSkill.getUserId());
			p.setString(2, curriculumSkill.getTitle());
			p.setInt(3, curriculumSkill.getLevel());

			p.executeUpdate();

		} catch (SQLException e) {
			System.err.println("[CurriculumSkillDAOImpl] [create]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}

		return Protocol.OK;

	}

	@Override
	public String update(CurriculumSkill curriculumSkill) {

		con = DBUtil.getInstance().getConnection();

		String query = "UPDATE curriculum_skills SET user_id=?, title=?, level=? WHERE id=?;";

		try {

			p = con.prepareStatement(query);

			p.setLong(1, curriculumSkill.getUserId());
			p.setString(2, curriculumSkill.getTitle());
			p.setInt(3, curriculumSkill.getLevel());
			p.setLong(4, curriculumSkill.getId());

			p.executeUpdate();

		} catch (SQLException e) {
			System.err.println("[CurriculumSkillDAOImpl] [update]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}

		return Protocol.OK;

	}

	@Override
	public long findId(CurriculumSkill curriculumSkill) {

		long id = -1; // target

		con = DBUtil.getInstance().getConnection();

		String query = "SELECT id from curriculum_skills WHERE user_id=? AND title=?;";

		try {

			p = con.prepareStatement(query);

			p.setLong(1, curriculumSkill.getUserId());
			p.setString(2, curriculumSkill.getTitle());

			rs = p.executeQuery();

			if (rs.next()) {
				id = rs.getLong("id");
			}

		} catch (SQLException e) {
			System.err.println("[CurriculumSkillDAOImpl] [findId]: ");
			e.printStackTrace();
			return id;
		} finally {
			closeAll();
		}

		return id;
	}

	@Override
	public CurriculumSkill findById(long id) {

		CurriculumSkill curriculumSkill = null;

		con = DBUtil.getInstance().getConnection();

		String query = "SELECT * FROM curriculum_skills WHERE id=?;";

		try {

			p = con.prepareStatement(query);
			p.setLong(1, id);

			rs = p.executeQuery();

			if (rs.next()) {
				curriculumSkill = new CurriculumSkill();
				curriculumSkill.setId(id);
				curriculumSkill.setUserId(rs.getLong("user_id"));
				curriculumSkill.setTitle(rs.getString("title"));
				curriculumSkill.setLevel(rs.getInt("level"));

			}

		} catch (SQLException e) {
			System.err.println("[CurriculumSkillDAOImpl] [findById]: ");
			e.printStackTrace();
		} finally {
			closeAll();
		}

		return curriculumSkill;
	}

	public Set<CurriculumSkill> findAllByUserId(Long userId) {

		Set<CurriculumSkill> skills = new HashSet<CurriculumSkill>();

		con = DBUtil.getInstance().getConnection();

		String query = "SELECT * FROM curriculum_skills WHERE user_id=?;";

		try {

			p = con.prepareStatement(query);
			p.setLong(1, userId);

			rs = p.executeQuery();

			while (rs.next()) {
				CurriculumSkill skill = new CurriculumSkill();
				skill.setId(rs.getLong("id"));
				skill.setUserId(rs.getLong("user_id"));
				skill.setTitle(rs.getString("title"));
				skill.setLevel(rs.getInt("level"));
				skills.add(skill);
			}

		} catch (SQLException e) {
			System.err.println("[CurriculumSkillDAOImpl] [findAllByUserId]: ");
			e.printStackTrace();
		} finally {
			closeAll();
		}

		return skills;

	}

	@Override
	public Set<CurriculumSkill> findAll() {
		Set<CurriculumSkill> curriculums = new HashSet<CurriculumSkill>();

		con = DBUtil.getInstance().getConnection();

		String query = "SELECT * FROM curriculum_skills;";

		try {

			p = con.prepareStatement(query);

			rs = p.executeQuery();

			while (rs.next()) {
				CurriculumSkill curriculumSkill = new CurriculumSkill();
				curriculumSkill = new CurriculumSkill();
				curriculumSkill.setId(rs.getLong("id"));
				curriculumSkill.setUserId(rs.getLong("user_id"));
				curriculumSkill.setTitle(rs.getString("title"));
				curriculumSkill.setLevel(rs.getInt("level"));
				curriculums.add(curriculumSkill);
			}

		} catch (SQLException e) {
			System.err.println("[CurriculumSkillDAOImpl] [findAll]: ");
			e.printStackTrace();
		} finally {
			closeAll();
		}

		return curriculums;
	}

	@Override
	public String delete(CurriculumSkill curriculumSkill) {

		con = DBUtil.getInstance().getConnection();

		String query = "DELETE from curriculum_skills WHERE id=?";

		try {

			p = con.prepareStatement(query);
			p.setLong(1, curriculumSkill.getId());
			p.executeUpdate();

		} catch (SQLException e) {
			System.err.println("[CurriculumSkillDAOImpl] [delete]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}

		return Protocol.OK;
	}

}
