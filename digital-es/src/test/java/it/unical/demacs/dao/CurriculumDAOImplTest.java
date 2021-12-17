package it.unical.demacs.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import it.unical.demacs.informatica.digitales.app.beans.BannedUser;
import it.unical.demacs.informatica.digitales.app.beans.Curriculum;
import it.unical.demacs.informatica.digitales.app.dao.CurriculumDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class CurriculumDAOImplTest {
	private static Curriculum curriculum;
	private static CurriculumDAOImpl curriculumDAOImpl = CurriculumDAOImpl.getInstance();
	
	@BeforeClass
	public static void beforeClass() {
		curriculum=new Curriculum();
		curriculum.setHobbiesDescription("newHobbies");
		//vedere DB
		curriculum.setUserId(2);
		curriculum.setId(2);
	}
	
	@Ignore
	@Test
	public void checkAddCurriculumToDatabase() {
		System.out.println("[checkAddCurriculumToDatabase]");
		String res = curriculumDAOImpl.create(curriculum);
		assertEquals(res, Protocol.OK);
	}

	@Ignore
	@Test
	public void checkUpdateCurriculum() {
		System.out.println("[checkUpdateCurriculum]");
		curriculum.setHobbiesDescription("newHobbies");
		String res = curriculumDAOImpl.update(curriculum);
		assertEquals(Protocol.OK, res);
	}
	
	@Ignore
	@Test
	public void checkCurriculumId() {
		System.out.println("[checkCurriculumId]");
		long id = curriculumDAOImpl.findId(curriculum);
		assertEquals(2, id);
	}
	@Ignore
	@Test
	public void checkFindCurriculumById() {
		System.out.println("[checkFindCurriculumById]");
		Curriculum curriculumById = curriculumDAOImpl.findById(2);
		System.out.println(curriculumById.toString());
		System.out.println(curriculum.toString());
		assertEquals(curriculum, curriculumById);
	}
	
	@Ignore
	@Test
	public void checkFindAllCurriculum() {
		System.out.println("[checkFindAllCurriculum]");
		Set<Curriculum> curriculums = curriculumDAOImpl.findAll();
		for (Curriculum c : curriculums) {
			System.out.println(c.toString());
		}
		assertEquals(1, curriculums.size());
	}
	

}
