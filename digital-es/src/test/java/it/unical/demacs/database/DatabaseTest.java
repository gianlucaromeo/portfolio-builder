package it.unical.demacs.database;

import static org.junit.Assert.assertNotEquals;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import it.unical.demacs.informatica.digitales.app.database.DBUtil;

public class DatabaseTest {

	private static DBUtil dbUtil = null;	
	private static Connection con = null;
	
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println("[beforeClass]");
		dbUtil = DBUtil.getInstance();
	}
	
	@AfterClass
	public static void afterClass() {
		System.out.println("[afterClass]");
	}
	
	@Before
	public void before() {
		System.out.println("[before]");
	}
	
	@After
	public void after() {
		System.out.println("[after]");
	}
	
	@Test
	public void connectionWorks() {
		System.out.println("[connectionWorks]");
		con = dbUtil.getConnection();
		assertNotEquals(con, null);
	}
	
}
