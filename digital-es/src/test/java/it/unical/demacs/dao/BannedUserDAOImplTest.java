package it.unical.demacs.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import it.unical.demacs.informatica.digitales.app.beans.BannedUser;
import it.unical.demacs.informatica.digitales.app.dao.BannedUserDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class BannedUserDAOImplTest {
	private static BannedUser bannedUser;
	private static BannedUserDAOImpl bannedUserDAOImpl = BannedUserDAOImpl.getInstance();
	
	@BeforeClass
	public static void beforeClass() {
		bannedUser = new BannedUser();
		bannedUser.setUserId(2);
		bannedUser.setModeratorId(1);
		bannedUser.setReason("BannedUsereVero@a.it");
		bannedUser.setDateStart("BannedUsereVero@a.it");
		bannedUser.setDateEnd("BannedUsereVero@a.it");
	}
	
	@Test
	public void checkAddBannedUserToDatabase() {
		System.out.println("[checkAddBannedUserToDatabase]");
		String res = bannedUserDAOImpl.create(bannedUser);
		assertEquals(res, Protocol.OK);
	}
}
