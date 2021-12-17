package it.unical.demacs.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import it.unical.demacs.informatica.digitales.app.beans.CurriculumExperience;
import it.unical.demacs.informatica.digitales.app.beans.CurriculumSkill;
import it.unical.demacs.informatica.digitales.app.dao.CurriculumSkillDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class CurriculumSkillDAOImplTest {
	private static CurriculumSkill curriculumSkill;
	private static CurriculumSkillDAOImpl curriculumSkillDAOImpl = CurriculumSkillDAOImpl.getInstance();
	
	@BeforeClass
	public static void beforeClass() {
		curriculumSkill=new CurriculumSkill();
		curriculumSkill.setTitle("newTitle");
		curriculumSkill.setLevel(100);
		
		curriculumSkill.setUserId(2);
		curriculumSkill.setId(1);
		
		
	}
	
	@Ignore
	@Test
	public void checkAddCurriculumSkillToDatabase() {
		System.out.println("[checkAddCurriculumSkillToDatabase]");
		String res = curriculumSkillDAOImpl.create(curriculumSkill);
		assertEquals(res, Protocol.OK);
	}
	

	@Ignore
	@Test
	public void checkUpdateCurriculumSkill() {
		System.out.println("[checkUpdateCurriculumSkill]");
		curriculumSkill.setTitle("newTitle");
		String res = curriculumSkillDAOImpl.update(curriculumSkill);
		assertEquals(Protocol.OK, res);
	}
	
	@Ignore
	@Test
	public void checkFindCurriculumSkillId() {
		System.out.println("[checkFindCurriculumSkillId]");
		long id = curriculumSkillDAOImpl.findId(curriculumSkill);
		assertEquals(1, id);
	}
	@Ignore
	@Test
	public void checkFindCurriculumSkillById() {
		System.out.println("[checkFindCurriculumSkillById]");
		CurriculumSkill curriculumSkillById = curriculumSkillDAOImpl.findById(1);
		System.out.println(curriculumSkillById.toString());
		System.out.println(curriculumSkill.toString());
		assertEquals(curriculumSkill, curriculumSkillById);
	}
	
	@Ignore
	@Test
	public void checkFindAllCurriculumSkill() {
		System.out.println("[checkFindAllCurriculumSkill]");
		Set<CurriculumSkill> curriculumSkills = curriculumSkillDAOImpl.findAll();
		for (CurriculumSkill c : curriculumSkills) {
			System.out.println(c.toString());
		}
		assertEquals(1, curriculumSkills.size());
	}

	

}
