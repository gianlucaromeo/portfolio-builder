package it.unical.demacs.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

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
		moderator.setPassword("Password");
		moderator.setEmail("ModeratoreVero@a.it");
	}
	
	@Ignore
	@Test
	public void checkAddModeratorToDatabase() {
		System.out.println("[checkAddModeratorToDatabase]");
		String res = moderatorDAOImpl.create(moderator);
		assertEquals(res, Protocol.OK);
	}
	
	@Test
	public void checkFindModeratorId() {
		System.out.println("[checkFindModeratorId]");
		long id = moderatorDAOImpl.findId(moderator);
		assertEquals(1, id);
	}

}
