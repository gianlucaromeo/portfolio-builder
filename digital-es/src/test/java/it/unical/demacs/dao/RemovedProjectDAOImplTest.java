package it.unical.demacs.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import it.unical.demacs.informatica.digitales.app.beans.RemovedPost;
import it.unical.demacs.informatica.digitales.app.beans.RemovedProject;
import it.unical.demacs.informatica.digitales.app.dao.RemovedProjectDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class RemovedProjectDAOImplTest {
	private static RemovedProject removedProject;
	private static RemovedProjectDAOImpl removedProjectDAOImpl = RemovedProjectDAOImpl.getInstance();
	
	@BeforeClass
	public static void beforeClass() {
		removedProject = new RemovedProject();
		removedProject.setModeratorId(1);
		removedProject.setProjectId(2);
		removedProject.setReason("UpdatedReason");
		removedProject.setSeenByUser(false);
		
		removedProject.setId(1);
	}
	
	@Ignore
	@Test
	public void checkAddRemovedProjectToDatabase() {
		System.out.println("[checkAddRemovedProjectToDatabase]");
		String res = removedProjectDAOImpl.create(removedProject);
		assertEquals(res, Protocol.OK);
	}
	
	@Ignore
	@Test
	public void checkUpdateRemovedProjectToDatabase() {
		System.out.println("[checkUpdateRemovedProjectToDatabase]");
		removedProject.setReason("UpdatedReason");
		String res = removedProjectDAOImpl.update(removedProject);
		assertEquals(res, Protocol.OK);
	}

	@Ignore
	@Test
	public void checkFindRemovedProjectId() {
		System.out.println("[checkFindRemovedProjectId]");
		long id = removedProjectDAOImpl.findId(removedProject);
		assertEquals(1, id);
	}
	@Ignore
	@Test
	public void checkFindRemovedProjectById() {
		System.out.println("[checkFindRemovedProjectById]");
		RemovedProject removedProjectById = removedProjectDAOImpl.findById(1);
		System.out.println(removedProjectById.toString());
		System.out.println(removedProject.toString());
		assertEquals(removedProject, removedProjectById);
	}
	
	@Ignore
	@Test
	public void checkFindAllRemovedProject() {
		System.out.println("[checkFindAllRemovedProject]");
		Set<RemovedProject> projects = removedProjectDAOImpl.findAll();
		for (RemovedProject p : projects) {
			System.out.println(p.toString());
		}
		assertEquals(1, projects.size());
	}
	
	@Test
	public void checkDeleteRemovedProject() {
		System.out.println("[checkDeleteRemovedProject]");
		String res = removedProjectDAOImpl.delete(removedProject);
		assertEquals(Protocol.OK, res);
	}
}
