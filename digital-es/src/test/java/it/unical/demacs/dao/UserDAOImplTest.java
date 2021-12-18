package it.unical.demacs.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import it.unical.demacs.informatica.digitales.app.beans.BannedUser;
import it.unical.demacs.informatica.digitales.app.beans.Project;
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
		user.setDateOfBirth("newDateOfBirth");
		user.setMainPhoneNumber("3169981021");
		user.setSecondaryPhoneNumber("0189556712");
		user.setContactEmail("bellissima@me.it");
		user.setConfirmed(false);
		user.setSignUpDate("2021-17-01");
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
	public void checkUpdateUser() {
		System.out.println("[checkUpdateUser]");
		user.setDateOfBirth("newDateOfBirth");
		String res = userDAOImpl.update(user);
		assertEquals(Protocol.OK, res);
	}
	
	@Ignore
	@Test
	public void checkFindBannedUserId() {
		System.out.println("[checkFindUserId]");
		long id = userDAOImpl.findId(user);
		assertEquals(3, id);
	}
	
	@Ignore
	@Test
	public void checkFindUserById() {
		System.out.println("[checkFindUserById]");
		User userById = userDAOImpl.findById(3);
		System.out.println(userById.toString());
		System.out.println(user.toString());
		assertEquals(user, userById);
	}
	
	@Ignore
	@Test
	public void checkFindAllUsers() {
		System.out.println("[checkFindAllUsers]");
		Set<User> users = userDAOImpl.findAll();
		for (User u : users) {
			System.out.println(u.toString());
		}
		assertEquals(3, users.size());
	}
	
	@Ignore
	@Test
	public void checkDeleteUser() {
		System.out.println("[checkDeleteUser]");
		String res = userDAOImpl.delete(user);
		assertEquals(Protocol.OK, res);
	}
	
}
