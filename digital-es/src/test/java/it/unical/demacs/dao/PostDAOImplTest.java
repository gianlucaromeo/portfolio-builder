package it.unical.demacs.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import it.unical.demacs.informatica.digitales.app.beans.CurriculumSkill;
import it.unical.demacs.informatica.digitales.app.beans.Post;
import it.unical.demacs.informatica.digitales.app.dao.CurriculumSkillDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.PostDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class PostDAOImplTest {
	private static Post post;
	private static PostDAOImpl postDAOImpl = PostDAOImpl.getInstance();
	
	@BeforeClass
	public static void beforeClass() {
		post=new Post();
		post.setTitle("Post number 1");
		post.setDescription("This is my first post wooow lol xD");
		post.setPicture(null);
		post.setPubblicationDate("2021-12-14");
		post.setLastEditDate(null);
		post.setRefLink(null);
		post.setUserId(2);
		
	}
	
	@Ignore
	@Test
	public void checkAddPostToDatabase() {
		System.out.println("[checkAddPostToDatabase]");
		String res = postDAOImpl.create(post);
		assertEquals(res, Protocol.OK);
	}
	
	@Test
	public void checkFindPostId() {
		System.out.println("[checkFindPostId]");
		long id = postDAOImpl.findId(post);
		assertEquals(1, id);
	}

}
