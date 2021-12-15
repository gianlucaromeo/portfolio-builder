package it.unical.demacs.database;

import static org.junit.Assert.assertNotEquals;

import java.sql.Connection;

import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import it.unical.demacs.informatica.digitales.app.database.DBUtil;

public class DatabaseTest {

	@Rule
	public MockitoRule mockitoRule = MockitoJUnit.rule();
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	private static DBUtil dbUtil = null;
	
	private static Connection con = null;
	
	@BeforeClass
	public static void beforeClass() {
		System.out.println("[beforeClass]");
		dbUtil = DBUtil.getInstance();
	}
	
	@Test
	public void connectionWorks() {
		System.out.println("[connectionWorks]");
		con = dbUtil.getConnection();
		assertNotEquals(con, null);
	}
	
}
