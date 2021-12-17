package it.unical.demacs.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

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
		moderator.setUsername("UpdatedUsername");
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
	public void checkUpdateModeratorToDatabase() {
		System.out.println("[checkUpdateModeratorToDatabase]");
		moderator.setUsername("UpdatedUsername");
		String res = moderatorDAOImpl.update(moderator);
		assertEquals(res, Protocol.OK);
	}
	
	@Ignore
	@Test
	public void checkFindModeratorId() {
		System.out.println("[checkFindModeratorId]");
		long id = moderatorDAOImpl.findId(moderator);
		assertEquals(1, id);
	}
	
	@Ignore
	@Test
	public void checkFindModeratorById() {
		System.out.println("[checkFindModeratorById]");
		Moderator moderatorById = moderatorDAOImpl.findById(1);
		System.out.println(moderatorById.toString());
		System.out.println(moderator.toString());
		assertEquals(moderator, moderatorById);
	}
	
	@Ignore
	@Test
	public void checkFindAllModerators() {
		System.out.println("[checkFindAllModerators]");
		Set<Moderator> moderators = moderatorDAOImpl.findAll();
		for (Moderator m : moderators) {
			System.out.println(m.toString());
		}
		assertEquals(1, moderators.size());
	}
	
	@Test
	public void checkDeleteModerator() {
		System.out.println("[checkDeleteModerator]");
		String res = moderatorDAOImpl.delete(moderator);
		assertEquals(Protocol.OK, res);
	}

}
