package it.unical.demacs.informatica.digitales.app.dashboard;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unical.demacs.informatica.digitales.app.beans.CurriculumExperience;
import it.unical.demacs.informatica.digitales.app.beans.Post;
import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.beans.UserAuthentication;
import it.unical.demacs.informatica.digitales.app.dao.CurriculumExperienceDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.PostDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

@RestController
public class PostsREST {
	@PostMapping("/delete_post")
	public void deletePost(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {
		Gson gson = new Gson();
		Integer id = gson.fromJson(req.getReader(), Integer.class);
		Post post = new Post();
		post.setId(id);
		System.out.println(id);
		PostDAOImpl.getInstance().delete(post);

	}

	@PostMapping("/update_post")
	public void updatePost(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {
		Gson gson = new Gson();
		Post post = new Post();
		post = gson.fromJson(req.getReader(), Post.class);
		Post originalPost= PostDAOImpl.getInstance().findById(post.getId());
		
		post.setPubblicationDate(originalPost.getPubblicationDate());
		post.setUserId(originalPost.getUserId());
		System.out.println(post);
		PostDAOImpl.getInstance().update(post);

	}
	@PostMapping("/get_posts_data_action")
	public String getPostsDataAction(HttpServletRequest req, HttpServletResponse resp) {
		Gson gson = new Gson();

		Cookie[] cookies = req.getCookies();
		for (Cookie c : cookies) {
			System.out.println("Cookie: " + c.getName());
			if (c.getName().equals("logged_username")) {
				String username = c.getValue();
				User user = UserDAOImpl.getInstance().findByUsername(username);

				Set<Post> posts = PostDAOImpl.getInstance().findAllByUserId(user.getId());
				String postsToJSON = gson.toJson(posts);

				System.out.println(postsToJSON);

				return postsToJSON;
			}
		}

		return Protocol.ERROR;

	}
}
