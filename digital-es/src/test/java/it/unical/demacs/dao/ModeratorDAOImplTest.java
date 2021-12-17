package it.unical.demacs.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.security.crypto.bcrypt.BCrypt;

import it.unical.demacs.informatica.digitales.app.beans.BannedUser;
import it.unical.demacs.informatica.digitales.app.beans.Moderator;
import it.unical.demacs.informatica.digitales.app.dao.ModeratorDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class ModeratorDAOImplTest {
	
	private static Moderator moderator;
	private static ModeratorDAOImpl moderatorDAOImpl = ModeratorDAOImpl.getInstance();
	
	@BeforeClass
	public static void beforeClass() {
		moderator = new Moderator();
		moderator.setUsername("ModeratoreVero1");
		moderator.setPassword("$2a$12$OohPDn/Uka7/4XfOwOI.g.UvGl5oOMpUXxT7w6mpdh/fU.sO/l9uS");
		moderator.setEmail("ModeratoreVero@a.it");
		
		moderator.setId(1);
	}
	
	@Ignore
	@Test
	public void checkAddModeratorToDatabase() {
		System.out.println("[checkAddModeratorToDatabase]");
		String res = moderatorDAOImpl.create(moderator);
		assertEquals(res, Protocol.OK);
	}
	
	@Ignore
	@Test
	public void checkFindModeratorId() {
		System.out.println("[checkFindModeratorId]");
		long id = moderatorDAOImpl.findId(moderator);
		assertEquals(1, id);
	}
	
	@Test
	public void checkFindModeratorById() {
		System.out.println("[checkFindModeratorById]");
		Moderator moderatorById = moderatorDAOImpl.findById(1);
		System.out.println(moderatorById.toString());
		System.out.println(moderator.toString());
		assertEquals(moderator, moderatorById);
	}

}
