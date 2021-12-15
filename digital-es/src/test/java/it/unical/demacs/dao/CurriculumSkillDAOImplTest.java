package it.unical.demacs.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import it.unical.demacs.informatica.digitales.app.beans.CurriculumSkill;
import it.unical.demacs.informatica.digitales.app.dao.CurriculumSkillDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class CurriculumSkillDAOImplTest {
	private static CurriculumSkill curriculumSkill;
	private static CurriculumSkillDAOImpl curriculumSkillDAOImpl = CurriculumSkillDAOImpl.getInstance();
	
	@BeforeClass
	public static void beforeClass() {
		curriculumSkill=new CurriculumSkill();
		curriculumSkill.setTitle("Java");
		curriculumSkill.setLevel(80);
		//vedere DB
		curriculumSkill.setCurriculumId(1);
		curriculumSkill.setUserId(1);
		
		
	}
	
	@Test
	public void checkAddCurriculumSkillToDatabase() {
		System.out.println("[checkAddCurriculumSkillToDatabase]");
		String res = curriculumSkillDAOImpl.create(curriculumSkill);
		assertEquals(res, Protocol.OK);
	}
	

}
