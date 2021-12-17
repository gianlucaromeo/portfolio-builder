package it.unical.demacs.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import it.unical.demacs.informatica.digitales.app.beans.Post;
import it.unical.demacs.informatica.digitales.app.beans.Project;
import it.unical.demacs.informatica.digitales.app.beans.RemovedProject;
import it.unical.demacs.informatica.digitales.app.dao.ProjectDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class ProjectDAOImplTest {
	private static Project project;
	private static ProjectDAOImpl projectDAOImpl = ProjectDAOImpl.getInstance();
	
	@BeforeClass
	public static void beforeClass() {
		project= new Project();
		project.setTitle("Google Project");
		project.setDescription("newDescription");
		project.setPicture("/img/...");
		project.setLinkRef("www.github.com/...");
		project.setUserId(2);
		
		project.setId(2);
	}
	
	@Ignore
	@Test
	public void checkAddPostToDatabase() {
		System.out.println("[checkAddProjectToDatabase]");
		String res = projectDAOImpl.create(project);
		assertEquals(res, Protocol.OK);
	}
	
	@Ignore
	@Test
	public void checkUpdateProject() {
		System.out.println("[checkUpdateProject]");
		project.setDescription("newDescription");
		String res = projectDAOImpl.update(project);
		assertEquals(Protocol.OK, res);
	}
	
	@Ignore
	@Test
	public void checkFindProjectId() {
		System.out.println("[checkFindProjectId]");
		long id = projectDAOImpl.findId(project);
		assertEquals(2, id);
	}
	
	@Ignore
	@Test
	public void checkFindProjectById() {
		System.out.println("[checkFindProjectById]");
		Project projectById = projectDAOImpl.findById(2);
		System.out.println(projectById.toString());
		System.out.println(project.toString());
		assertEquals(project, projectById);
	}
	
	@Ignore
	@Test
	public void checkFindAllProjects() {
		System.out.println("[checkFindAllProjects]");
		Set<Project> projects = projectDAOImpl.findAll();
		for (Project p : projects) {
			System.out.println(p.toString());
		}
		assertEquals(1, projects.size());
	}
	
	@Ignore
	@Test
	public void checkDeleteProject() {
		System.out.println("[checkDeleteProject]");
		String res = projectDAOImpl.delete(project);
		assertEquals(Protocol.OK, res);
	}
	
}
