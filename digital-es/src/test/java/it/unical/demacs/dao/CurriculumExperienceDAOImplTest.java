package it.unical.demacs.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.junit.BeforeClass;
import org.junit.Test;

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
		curriculumExperience.setDescription("I've worked for google as software engineer");
		curriculumExperience.setPlace("Zurigo, Switzerland" );
		curriculumExperience.setType(CurriculumSettings.WORK_EXPERIENCE);
		curriculumExperience.setStartDate("2011-07-23");
		curriculumExperience.setEndDate("2017-04-28");
		
		//vedere DB
		curriculumExperience.setUserId(2);
		
	}
	
	@Test
	public void checkAddCurriculumExperienceToDatabase() {
		System.out.println("[checkAddCurriculumExperienceToDatabase]");
		String res = curriculumExperienceDAOImpl.create(curriculumExperience);
		assertEquals(res, Protocol.OK);
	}
	

}
