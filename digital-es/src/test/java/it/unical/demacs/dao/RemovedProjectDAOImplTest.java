package it.unical.demacs.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import it.unical.demacs.informatica.digitales.app.beans.RemovedProject;
import it.unical.demacs.informatica.digitales.app.dao.RemovedProjectDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class RemovedProjectDAOImplTest {
	private static RemovedProject removedProject;
	private static RemovedProjectDAOImpl removedProjectDAOImpl = RemovedProjectDAOImpl.getInstance();
	
	@BeforeClass
	public static void beforeClass() {
		removedProject = new RemovedProject();
		removedProject.setModeratorId(0);
		removedProject.setProjectId(0);
		removedProject.setReason("RemovedProjecteVero@a.it");
		removedProject.setSeenByUser(false);
	}
	
	@Test
	public void checkAddRemovedProjectToDatabase() {
		System.out.println("[checkAddRemovedProjectToDatabase]");
		String res = removedProjectDAOImpl.create(removedProject);
		assertEquals(res, Protocol.OK);
	}
}
