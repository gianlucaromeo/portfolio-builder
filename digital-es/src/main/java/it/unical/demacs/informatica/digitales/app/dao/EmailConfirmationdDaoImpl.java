package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.SQLException;
import java.sql.Types;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCrypt;

import it.unical.demacs.informatica.digitales.app.beans.BannedUser;
import it.unical.demacs.informatica.digitales.app.beans.Curriculum;
import it.unical.demacs.informatica.digitales.app.beans.EmailConfirmation;
import it.unical.demacs.informatica.digitales.app.database.DBUtil;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class EmailConfirmationdDaoImpl extends DAOImpl implements DAO<EmailConfirmation> {

	private static EmailConfirmationdDaoImpl instance = null;

	public static EmailConfirmationdDaoImpl getInstance() {
		if (instance == null) {
			instance = new EmailConfirmationdDaoImpl();
		}
		return instance;
	}

	private EmailConfirmationdDaoImpl() {

	}

	@Override
	public String create(EmailConfirmation emailConfirmation) {

		con = DBUtil.getInstance().getConnection();

		String query = "INSERT INTO email_confirmation VALUES(?,?);";

		try {

			p = con.prepareStatement(query);

			p.setLong(1, emailConfirmation.getUserId());
			p.setString(2, emailConfirmation.getToken());

			p.executeUpdate();

		} catch (SQLException e) {
			System.err.println("[EmailConfirmationDAOImpl] [create]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}

		return Protocol.OK;

	}

	public long findUserId(String token) {

		System.out.println("token : " + token);
		long userId = -1; // target
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "SELECT user_id from email_confirmation WHERE token=?;";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setString(1, token);
			
			rs = p.executeQuery();
			
			if (rs.next()) {
				userId = rs.getLong("user_id");
			}
			
		} catch (SQLException e) {
			System.err.println("[EmailConfirmatinoDAOImpl] [findUserId]: ");
			e.printStackTrace();
			return userId;
		} finally {
			closeAll();
		}
		
		return userId;
	}

	@Override
	public EmailConfirmation findById(long userId) {

		EmailConfirmation emailConfirmation = null;

		con = DBUtil.getInstance().getConnection();

		String query = "SELECT * FROM email_confirmation WHERE user_id=?;";

		try {

			p = con.prepareStatement(query);
			p.setLong(1, userId);

			rs = p.executeQuery();

			if (rs.next()) {
				emailConfirmation = new EmailConfirmation();
				emailConfirmation.setUserId(rs.getLong("user_id"));
				emailConfirmation.setToken(rs.getString("token"));
				return emailConfirmation;
			}

		} catch (SQLException e) {
			System.err.println("[EmailConfirmationDAOImpl] [findById]: ");
			e.printStackTrace();
		} finally {
			closeAll();
		}

		return emailConfirmation;

	}

	@Override
	public String delete(EmailConfirmation emailConfirmation) {

		con = DBUtil.getInstance().getConnection();

		String query = "DELETE from email_confirmation WHERE user_id=?";

		try {

			p = con.prepareStatement(query);
			p.setLong(1, emailConfirmation.getUserId());
			p.executeUpdate();

		} catch (SQLException e) {
			System.err.println("[EmailConfirmationDAOImpl] [delete]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}

		return Protocol.OK;
	}

	@Override
	public String update(EmailConfirmation t) {
		throw new NoSuchMethodError();
	}

	@Override
	public long findId(EmailConfirmation t) {
		throw new NoSuchMethodError();
	}

	@Override
	public Set<EmailConfirmation> findAll() {
		throw new NoSuchMethodError();
	}

}
