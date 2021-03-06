package it.unical.demacs.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Ignore;
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
		bannedUser.setDateEnd("newDateEnd");
		bannedUser.setId(1);
	}
	
	@Ignore
	@Test
	public void checkAddBannedUserToDatabase() {
		System.out.println("[checkAddBannedUserToDatabase]");
		String res = bannedUserDAOImpl.create(bannedUser);
		assertEquals(res, Protocol.OK);
	}
	
	@Ignore
	@Test
	public void checkUpdateBannedUser() {
		System.out.println("[checkUpdateBannedUser]");
		bannedUser.setDateEnd("newDateEnd");
		String res = bannedUserDAOImpl.update(bannedUser);
		assertEquals(Protocol.OK, res);
	}
	
	@Ignore
	@Test
	public void checkFindBannedUserId() {
		System.out.println("[checkFindBannedUserId]");
		long id = bannedUserDAOImpl.findId(bannedUser);
		assertEquals(1, id);
	}
	
	@Ignore
	@Test
	public void checkFindBannedUserById() {
		System.out.println("[checkFindBannedUserById]");
		BannedUser bannedUserById = bannedUserDAOImpl.findById(1);
		System.out.println(bannedUserById.toString());
		System.out.println(bannedUser.toString());
		assertEquals(bannedUser, bannedUserById);
	}
	
	@Ignore
	@Test
	public void checkFindAllBannedUsers() {
		System.out.println("[checkFindAllUsers]");
		Set<BannedUser> bannedUsers = bannedUserDAOImpl.findAll();
		for (BannedUser b : bannedUsers) {
			System.out.println(b.toString());
		}
		assertEquals(1, bannedUsers.size());
	}
	
	@Ignore
	@Test
	public void checkDeleteBannedUser() {
		System.out.println("[checkDeleteBannedUser]");
		String res = bannedUserDAOImpl.delete(bannedUser);
		assertEquals(Protocol.OK, res);
	}
	
}
