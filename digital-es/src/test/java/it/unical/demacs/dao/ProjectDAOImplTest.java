package it.unical.demacs.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import it.unical.demacs.informatica.digitales.app.beans.Project;
import it.unical.demacs.informatica.digitales.app.dao.ProjectDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class ProjectDAOImplTest {
	private static Project project;
	private static ProjectDAOImpl projectDAOImpl = ProjectDAOImpl.getInstance();
	
	@BeforeClass
	public static void beforeClass() {
		project= new Project();
		project.setTitle("Google Project");
		project.setDescription("this is a project i've done when i've worked for Google");
		project.setPicture("/img/...");
		project.setLinkRef("www.github.com/...");
		project.setUserId(2);
		
		
	}
	
	@Ignore
	@Test
	public void checkAddPostToDatabase() {
		System.out.println("[checkAddProjectToDatabase]");
		String res = projectDAOImpl.create(project);
		assertEquals(res, Protocol.OK);
	}
	
	@Test
	public void checkFindProjectId() {
		System.out.println("[checkFindProjectId]");
		long id = projectDAOImpl.findId(project);
		assertEquals(-1, id);
	}

}
