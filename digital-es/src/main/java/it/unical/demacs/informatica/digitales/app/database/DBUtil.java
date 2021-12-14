package it.unical.demacs.informatica.digitales.app.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DBUtil {

	/* SINGLETON */
	
	private static DBUtil instance = null;
	
	public static DBUtil getInstance() {
		if (instance == null) {
			instance = new DBUtil();
		}
		return instance;
	}
	
	private DBUtil() {}
	
	/* END SINGLETON */
	
	/* ============================== */
	
	private Connection con = null;
	
	public synchronized Connection getConnection() {
		
		con = null;
		
		try {
			con = DriverManager.getConnection(DBSettings.URL, DBSettings.USER, DBSettings.PASSWORD);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return con;
		
	}
	
	public synchronized void closeAll(ResultSet rs, PreparedStatement p) {
		closeResultSet(rs);
		closePreparedStatement(p);
		closeConnection();
	}
	
	private synchronized void closeConnection() {
		if (con != null) {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private synchronized void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	private synchronized void closePreparedStatement(PreparedStatement p) {
		if (p != null) {
			try {
				p.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
	
	public synchronized boolean checkConnection() {

		try {
			if (con == null || con.isClosed())
				return false;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}
	
}
