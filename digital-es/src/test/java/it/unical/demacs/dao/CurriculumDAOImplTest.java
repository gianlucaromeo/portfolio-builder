package it.unical.demacs.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import it.unical.demacs.informatica.digitales.app.beans.Curriculum;
import it.unical.demacs.informatica.digitales.app.dao.CurriculumDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class CurriculumDAOImplTest {
	private static Curriculum curriculum;
	private static CurriculumDAOImpl curriculumDAOImpl = CurriculumDAOImpl.getInstance();
	
	@BeforeClass
	public static void beforeClass() {
		curriculum=new Curriculum();
		curriculum.setHobbiesDescription("no one in particular");
		//vedere DB
		curriculum.setUserId(2);
	}
	
	@Ignore
	@Test
	public void checkAddCurriculumToDatabase() {
		System.out.println("[checkAddCurriculumToDatabase]");
		String res = curriculumDAOImpl.create(curriculum);
		assertEquals(res, Protocol.OK);
	}
	
	@Test
	public void checkCurriculumId() {
		System.out.println("[checkCurriculumId]");
		long id = curriculumDAOImpl.findId(curriculum);
		assertEquals(2, id);
	}
	

}
