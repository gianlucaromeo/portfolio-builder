package it.unical.demacs.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.BeforeClass;
import org.junit.Ignore;
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
		curriculumSkill.setTitle("Go");
		curriculumSkill.setLevel(100);
		
		curriculumSkill.setUserId(2);
		
		
	}
	
	@Ignore
	@Test
	public void checkAddCurriculumSkillToDatabase() {
		System.out.println("[checkAddCurriculumSkillToDatabase]");
		String res = curriculumSkillDAOImpl.create(curriculumSkill);
		assertEquals(res, Protocol.OK);
	}
	
	@Test
	public void checkFindCurriculumSkillId() {
		System.out.println("[checkFindCurriculumSkillId]");
		long id = curriculumSkillDAOImpl.findId(curriculumSkill);
		assertEquals(1, id);
	}
	

}
