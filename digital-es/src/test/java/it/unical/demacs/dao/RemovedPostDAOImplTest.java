package it.unical.demacs.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;

import it.unical.demacs.informatica.digitales.app.beans.RemovedPost;
import it.unical.demacs.informatica.digitales.app.dao.RemovedPostDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class RemovedPostDAOImplTest {
	private static RemovedPost remPost;
	private static RemovedPostDAOImpl remPostsDAOImpl = RemovedPostDAOImpl.getInstance();
	
	@BeforeClass
	public static void beforeClass() {
		remPost= new RemovedPost();
		remPost.setReason("Offensive language");
		remPost.setSeenByUser(false);
		//vedere DB
		remPost.setModeratorId(1);
		remPost.setPostId(1);
		
	}
	
	@Test
	public void checkAddRemovedPostToDatabase() {
		System.out.println("[checkAddRemovedPostToDatabase]");
		String res = remPostsDAOImpl.create(remPost);
		assertEquals(res, Protocol.OK);
	}


}
