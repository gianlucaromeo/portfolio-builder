package it.unical.demacs.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
		removedProject.setReason("RemovedProjecteVero@a.it");
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
	public void checkFindRemovedProjectId() {
		System.out.println("[checkFindRemovedProjectId]");
		long id = removedProjectDAOImpl.findId(removedProject);
		assertEquals(-1, id);
	}

	@Test
	public void checkFindRemovedProjectById() {
		System.out.println("[checkFindRemovedProjectById]");
		RemovedProject removedProjectById = removedProjectDAOImpl.findById(1);
		System.out.println(removedProjectById.toString());
		System.out.println(removedProject.toString());
		assertEquals(removedProjectById, removedProject);
	}
}
