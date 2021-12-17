package it.unical.demacs.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import it.unical.demacs.informatica.digitales.app.beans.BannedUser;
import it.unical.demacs.informatica.digitales.app.beans.Curriculum;
import it.unical.demacs.informatica.digitales.app.beans.CurriculumExperience;
import it.unical.demacs.informatica.digitales.app.dao.CurriculumDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.CurriculumExperienceDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;
import it.unical.demacs.informatica.digitales.app.settings.CurriculumSettings;

public class CurriculumExperienceDAOImplTest {
	private static CurriculumExperience curriculumExperience;
	private static CurriculumExperienceDAOImpl curriculumExperienceDAOImpl = CurriculumExperienceDAOImpl.getInstance();
	
	@BeforeClass
	public static void beforeClass() {
		curriculumExperience=new CurriculumExperience();
		curriculumExperience.setTitle("Google Software Engineer" );
		curriculumExperience.setDescription("newFDescription");
		curriculumExperience.setPlace("Zurigo, Switzerland" );
		curriculumExperience.setType(CurriculumSettings.WORK_EXPERIENCE);
		curriculumExperience.setStartDate("2011-07-23");
		curriculumExperience.setEndDate("2017-04-28");
		
		//vedere DB
		curriculumExperience.setUserId(2);
		curriculumExperience.setId(4);
		
	}
	
	@Ignore
	@Test
	public void checkAddCurriculumExperienceToDatabase() {
		System.out.println("[checkAddCurriculumExperienceToDatabase]");
		String res = curriculumExperienceDAOImpl.create(curriculumExperience);
		assertEquals(res, Protocol.OK);
	}
	
	@Ignore
	@Test
	public void checkUpdateCurriculumExperience() {
		System.out.println("[checkUpdateCurriculumExperience]");
		curriculumExperience.setDescription("newFDescription");
		String res = curriculumExperienceDAOImpl.update(curriculumExperience);
		assertEquals(Protocol.OK, res);
	}
	
	@Ignore
	@Test
	public void checkFindCurriculumExperienceId() {
		System.out.println("[checkFindCurriculumExperienceId]");
		long id = curriculumExperienceDAOImpl.findId(curriculumExperience);
		assertEquals(id, 4);
	}
	@Ignore
	@Test
	public void checkFindCurriculumExperienceById() {
		System.out.println("[checkFindCurriculumExperienceById]");
		CurriculumExperience curriculumExperienceById = curriculumExperienceDAOImpl.findById(4);
		System.out.println(curriculumExperienceById.toString());
		System.out.println(curriculumExperience.toString());
		assertEquals(curriculumExperience, curriculumExperienceById);
	}
	
	@Ignore
	@Test
	public void checkFindAllCurriculumExperience() {
		System.out.println("[checkFindAllCurriculumExperience]");
		Set<CurriculumExperience> curriculumExs = curriculumExperienceDAOImpl.findAll();
		for (CurriculumExperience c : curriculumExs) {
			System.out.println(c.toString());
		}
		assertEquals(1, curriculumExs.size());
	}
	
	@Ignore
	@Test
	public void checkDeleteCurriculumExperiences() {
		System.out.println("[checkDeleteCurriculumExperiences]");
		String res = curriculumExperienceDAOImpl.delete(curriculumExperience);
		assertEquals(Protocol.OK, res);
	}

}
