package it.unical.demacs.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

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
		user.setPassword("MariaCristinaBellissima1234?");
		user.setEmail("thebreakinggames0@outlook.it");
		user.setDateOfBirth("01-30-2007");
		user.setMainPhoneNumber("3169981021");
		user.setSecondaryPhoneNumber("0189556712");
		user.setContactEmail("bellissima@me.it");
		user.setConfirmed(false);
	}
	
	@Test
	public void checkAddUserToDatabase() {
		System.out.println("[checkAddUserToDatabase]");
		String res = userDAOImpl.create(user);
		assertEquals(res, Protocol.OK);
	}
	
}
