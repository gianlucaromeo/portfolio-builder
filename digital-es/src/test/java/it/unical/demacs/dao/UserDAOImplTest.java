package it.unical.demacs.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import it.unical.demacs.informatica.digitales.app.beans.BannedUser;
import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class UserDAOImplTest {

	private static User user;
	private static UserDAOImpl userDAOImpl = UserDAOImpl.getInstance();
	
	@BeforeClass
	public static void beforeClass() {
		user = new User();
		user.setFirstName("Cristian");
		user.setLastName("Dramisino");
		user.setUsername("cristiandrami");
		user.setPassword("$2a$12$XcBMbnC3b7Bgf.g9LRmpre1Yrch53qXoJv1s/C8Qps483oSbIlNkS");
		user.setEmail("thebreakinggames0@outlook.it");
		user.setDateOfBirth("2007-01-30");
		user.setMainPhoneNumber("3169981021");
		user.setSecondaryPhoneNumber("0189556712");
		user.setContactEmail("bellissima@me.it");
		user.setConfirmed(false);
		user.setId(3);
	}
	
	@Ignore
	@Test
	public void checkAddUserToDatabase() {
		System.out.println("[checkAddUserToDatabase]");
		String res = userDAOImpl.create(user);
		assertEquals(res, Protocol.OK);
	}
	
	@Ignore
	@Test
	public void checkFindBannedUserId() {
		System.out.println("[checkFindUserId]");
		long id = userDAOImpl.findId(user);
		assertEquals(3, id);
	}
	
	@Test
	public void checkFindUserById() {
		System.out.println("[checkFindUserById]");
		User userById = userDAOImpl.findById(3);
		System.out.println(userById.toString());
		System.out.println(user.toString());
		assertEquals(user, userById);
	}
	
	
}
