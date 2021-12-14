package it.unical.demacs.informatica.digitales.app.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import it.unical.demacs.informatica.digitales.app.database.DBUtil;

public abstract class DAOImpl {

	protected Connection con = null;
	protected PreparedStatement p = null;
	protected ResultSet rs = null;
	
	protected void closeAll() {
		DBUtil.getInstance().closeAll(rs, p);
	}
	
}
