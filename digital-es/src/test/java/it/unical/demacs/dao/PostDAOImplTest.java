package it.unical.demacs.dao;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;

import it.unical.demacs.informatica.digitales.app.beans.BannedUser;
import it.unical.demacs.informatica.digitales.app.beans.CurriculumSkill;
import it.unical.demacs.informatica.digitales.app.beans.Post;
import it.unical.demacs.informatica.digitales.app.beans.RemovedProject;
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
		
		post.setId(1);
	}
	
	@Ignore
	@Test
	public void checkAddPostToDatabase() {
		System.out.println("[checkAddPostToDatabase]");
		String res = postDAOImpl.create(post);
		assertEquals(res, Protocol.OK);
	}
	@Ignore
	@Test
	public void checkFindPostId() {
		System.out.println("[checkFindPostId]");
		long id = postDAOImpl.findId(post);
		assertEquals(1, id);
	}
	
	@Ignore
	@Test
	public void checkFindPostById() {
		System.out.println("[checkFindPostById]");
		Post postById = postDAOImpl.findById(1);
		System.out.println(postById.toString());
		System.out.println(post.toString());
		assertEquals(post, postById);
	}

	@Test
	public void checkFindAllPosts() {
		System.out.println("[checkFindAllPosts]");
		Set<Post> posts = postDAOImpl.findAll();
		for (Post p : posts) {
			System.out.println(p.toString());
		}
		assertEquals(1, posts.size());
	}
	
}
