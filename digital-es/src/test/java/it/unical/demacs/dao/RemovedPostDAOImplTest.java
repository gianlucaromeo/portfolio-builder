package it.unical.demacs.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import it.unical.demacs.informatica.digitales.app.beans.BannedUser;
import it.unical.demacs.informatica.digitales.app.beans.Moderator;
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
		
		remPost.setId(1);
	}
	
	@Ignore
	@Test
	public void checkAddRemovedPostToDatabase() {
		System.out.println("[checkAddRemovedPostToDatabase]");
		String res = remPostsDAOImpl.create(remPost);
		assertEquals(res, Protocol.OK);
	}
	
//	@Test
//	public void checkUpdateRemovedPostToDatabase() {
//		System.out.println("[checkUpdateRemovedPostToDatabase]");
//		String res = remPostsDAOImpl.update(remPost);
//		assertEquals(res, Protocol.OK);
//	}
	
	@Ignore
	@Test
	public void checkFindBannedUserId() {
		System.out.println("[checkFindRemovedPostId]");
		long id = remPostsDAOImpl.findId(remPost);
		assertEquals(1, id);
	}
	@Ignore
	@Test
	public void checkFindRemovedPostById() {
		System.out.println("[checkFindRemovedPostById]");
		RemovedPost remPostById = remPostsDAOImpl.findById(1);
		System.out.println(remPostById.toString());
		System.out.println(remPost.toString());
		assertEquals(remPost, remPostById);
	}

	@Test
	public void checkFindAllRemovedPost() {
		System.out.println("[checkFindAllRemovedPost]");
		Set<RemovedPost> posts = remPostsDAOImpl.findAll();
		for (RemovedPost p : posts) {
			System.out.println(p.toString());
		}
		assertEquals(1, posts.size());
	}
}
