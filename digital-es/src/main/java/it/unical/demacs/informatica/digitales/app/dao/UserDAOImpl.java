package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.crypto.bcrypt.BCrypt;

import it.unical.demacs.informatica.digitales.app.dao.DAOImpl;
import it.unical.demacs.informatica.digitales.app.beans.Project;
import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.database.DBUtil;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class UserDAOImpl extends DAOImpl implements DAO<User>  {

	private static UserDAOImpl instance = null;
	
	public static UserDAOImpl getInstance() {
		if (instance == null) {
			instance = new UserDAOImpl();
		}
		return instance;
	}
	
	private UserDAOImpl() {
		
	}
	
	private int SALT = 12;

	@Override
	public String create(User user) {

		con = DBUtil.getInstance().getConnection(); 
		
		String query = "INSERT INTO users VALUES(DEFAULT,?,?,?,?,?,?,?,?,?,?,?);";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setString(1, user.getFirstName());
			p.setString(2, user.getLastName());
			p.setString(3, user.getUsername());
			p.setString(4, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(SALT)));
			p.setString(5, user.getEmail());
			p.setString(6, user.getDateOfBirth());
			p.setString(7, user.getMainPhoneNumber());
			p.setString(8, user.getSecondaryPhoneNumber());
			p.setString(9, user.getContactEmail());
			p.setBoolean(10, user.isConfirmed());
			p.setString(11, user.getSignUpDate());
			
			p.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("[UserDAOImpl] [create]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;
		
	}

	@Override
	public String update(User user) {
		
		con = DBUtil.getInstance().getConnection(); 
		
		String query = "UPDATE users SET first_name=?, last_name=?, username=?, password=?, email=?, date_of_birth=?, main_phone_number=?, secondary_phone_number=?, contact_email=?, confirmed=?, sign_up_date=? WHERE id=?;";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setString(1, user.getFirstName());
			p.setString(2, user.getLastName());
			p.setString(3, user.getUsername());
			p.setString(4, BCrypt.hashpw(user.getPassword(), BCrypt.gensalt(SALT)));
			p.setString(5, user.getEmail());
			p.setString(6, user.getDateOfBirth());
			p.setString(7, user.getMainPhoneNumber());
			p.setString(8, user.getSecondaryPhoneNumber());
			p.setString(9, user.getContactEmail());
			p.setBoolean(10, user.isConfirmed());
			p.setLong(11, user.getId());
			p.setString(12, user.getSignUpDate());
			
			p.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("[UserDAOImpl] [update]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;

	}
	
	@Override
	public long findId(User user) {
		
		long id = -1; // target
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "SELECT id from users WHERE username=?;";
		
		try {
			
			p = con.prepareStatement(query);
			
			p.setString(1, user.getUsername());
			
			
			
			rs = p.executeQuery();
			
			if (rs.next()) {
				id = rs.getLong("id");
			}
			
		} catch (SQLException e) {
			System.err.println("[UserDAOImpl] [findId]: ");
			e.printStackTrace();
			return id;
		} finally {
			closeAll();
		}
		
		return id;
	}
	
	@Override
	public User findById(long id) {
		User user = null;
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "SELECT * FROM users WHERE id=?;";
		
		try {
			
			p = con.prepareStatement(query);
			p.setLong(1, id);
			
			rs = p.executeQuery();
			
			if (rs.next()) {
				user= new User();
				user.setId(id);
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setDateOfBirth(rs.getString("date_of_birth"));
				user.setMainPhoneNumber(rs.getString("main_phone_number"));
				user.setSecondaryPhoneNumber(rs.getString("secondary_phone_number"));
				user.setContactEmail(rs.getString("contact_email"));
				user.setConfirmed(rs.getBoolean("confirmed"));
				user.setSignUpDate(rs.getString("sign_up_date"));
			}
	
		} catch (SQLException e) {
			System.err.println("[UserDAOImpl] [findById]: ");
			e.printStackTrace();
		} finally {
			closeAll();
		}
		
		return user;
	}
	
	@Override
	public Set<User> findAll() {

		Set<User> users = new HashSet<User>();
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "SELECT * FROM users;";

		try {
			
			p = con.prepareStatement(query);
			
			rs = p.executeQuery();
			
			while (rs.next()) {
				User user= new User();
				user.setId(rs.getLong("id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setDateOfBirth(rs.getString("date_of_birth"));
				user.setMainPhoneNumber(rs.getString("main_phone_number"));
				user.setSecondaryPhoneNumber(rs.getString("secondary_phone_number"));
				user.setContactEmail(rs.getString("contact_email"));
				user.setConfirmed(rs.getBoolean("confirmed"));
				user.setSignUpDate(rs.getString("sign_up_date"));
				users.add(user);
			}
			
		} catch (SQLException e) {
			System.err.println("[UserDAOImpl] [findAll]: ");
			e.printStackTrace();
		} finally {
			closeAll();
		}
		
		return users;
		
	}
	
	@Override
	public String delete(User user) {

		con = DBUtil.getInstance().getConnection();
		
		String query = "DELETE from users WHERE id=?";
		
		try {
			
			p = con.prepareStatement(query);
			p.setLong(1, user.getId());
			p.executeUpdate();
			
		} catch (SQLException e) {
			System.err.println("[UserDAOImpl] [delete]: ");
			e.printStackTrace();
			return Protocol.ERROR;
		} finally {
			closeAll();
		}
		
		return Protocol.OK;
		
	}

	public boolean checkUsernameExists(String username) {
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "SELECT * from users WHERE username=?";
		
		try {
			
			p = con.prepareStatement(query);
			p.setString(1, username);
			rs = p.executeQuery();
			
			if (rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			System.err.println("[UserDAOImpl] [checkUsernameExists]: ");
			e.printStackTrace();
			return false;
		} finally {
			closeAll();
		}
		
		return false;
		
	}
	
	public boolean checkEmailExists(String email) {
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "SELECT * from users WHERE email=?";
		
		try {
			
			p = con.prepareStatement(query);
			p.setString(1, email);
			rs = p.executeQuery();
			
			if (rs.next()) {
				return true;
			}
			
		} catch (SQLException e) {
			System.err.println("[UserDAOImpl] [checkEmailExists]: ");
			e.printStackTrace();
			return false;
		} finally {
			closeAll();
		}
		
		return false;
		
	}
	
	public User findByUsername(String username) {
		
		User user = null;
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "SELECT * FROM users WHERE username=?;";
		
		try {
			
			p = con.prepareStatement(query);
			p.setString(1, username);
			
			rs = p.executeQuery();
			
			if (rs.next()) {
				user= new User();
				user.setId(rs.getLong("id"));
				user.setFirstName(rs.getString("first_name"));
				user.setLastName(rs.getString("last_name"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setEmail(rs.getString("email"));
				user.setDateOfBirth(rs.getString("date_of_birth"));
				user.setMainPhoneNumber(rs.getString("main_phone_number"));
				user.setSecondaryPhoneNumber(rs.getString("secondary_phone_number"));
				user.setContactEmail(rs.getString("contact_email"));
				user.setConfirmed(rs.getBoolean("confirmed"));
				user.setSignUpDate(rs.getString("sign_up_date"));
			}
	
		} catch (SQLException e) {
			System.err.println("[UserDAOImpl] [findByUsername]: ");
			e.printStackTrace();
		} finally {
			closeAll();
		}
		
		return user;
	}

	public boolean checkUsernameAndPassword(String username, String password) {

		boolean userExists = false;
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "SELECT * FROM users WHERE username=?;";
		
		try {
			
			p = con.prepareStatement(query);
			p.setString(1, username);
			
			rs = p.executeQuery();

			if (rs.next()) {
				String hashPassword = rs.getString("password");
				userExists = BCrypt.checkpw(password, hashPassword);
			}
	
		} catch (SQLException e) {
			System.err.println("[UserDAOImpl] [checkUsernameAndPassword]: ");
			e.printStackTrace();
			userExists = false;
		} finally {
			closeAll();
		}
		
		return userExists;
		
	}
	
	public Integer getUsersCounter() {
		
		Integer count = null;
		
		con = DBUtil.getInstance().getConnection();
		
		String query = "SELECT count(*) as total FROM users;";
		
		try {
			
			p = con.prepareStatement(query);
			rs = p.executeQuery();
			
			if (rs.next()) {
				count = rs.getInt("total") + 1;
			}
	
		} catch (SQLException e) {
			System.err.println("[UserDAOImpl] [getUsersNumber]: ");
			e.printStackTrace();
			return count;
		} finally {
			closeAll();
		}
		
		return count;
		
	}
	
	
	
}
