package it.unical.demacs.informatica.digitales.app.dashboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unical.demacs.informatica.digitales.app.beans.Post;
import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.beans.validation.PostValidatorResponse;
import it.unical.demacs.informatica.digitales.app.dao.PostDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;
import it.unical.demacs.informatica.digitales.app.validator.PostValidator;

@RestController
public class PostsREST {

	@PostMapping("/create_post")
	public String createPost(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();
		Post post = new Post();
		post = gson.fromJson(req.getReader(), Post.class);

		User user = Servlets.getLoggedUser(req);
		if (user == null) {
			return gson.toJson(Protocol.ERROR);
		}

		PostValidatorResponse newPost = PostValidator.validatePost(post);
		post.setUserId(user.getId());
		post.setLastEditDate("");
		newPost.setUserId(user.getId());
		newPost.setLastEditDate("");

		if (PostValidator.isValidPost(newPost)) {
			PostDAOImpl.getInstance().create(post);
		}

		return gson.toJson(newPost);

	}

	@PostMapping("/delete_post")
	public String deletePost(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {
		Gson gson = new Gson();
		Integer id = gson.fromJson(req.getReader(), Integer.class);
		Post post = new Post();
		post.setId(id);
		PostDAOImpl.getInstance().delete(post);
		return id.toString();
	}

	@PostMapping("/update_post")
	public String updatePost(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {
		Gson gson = new Gson();
		Post post = new Post();
		post = gson.fromJson(req.getReader(), Post.class);
		Post originalPost = PostDAOImpl.getInstance().findById(post.getId());

		post.setPubblicationDate(originalPost.getPubblicationDate());
		post.setUserId(originalPost.getUserId());

		PostValidatorResponse newPost = PostValidator.validatePost(post);
		if (PostValidator.isValidPost(newPost)) {
			PostDAOImpl.getInstance().update(post);
		}
		return gson.toJson(newPost);

	}

	@PostMapping("/get_posts_data_action")
	public String getPostsDataAction(HttpServletRequest req, HttpServletResponse resp) {
		
		Gson gson = new Gson();

		User user = Servlets.getLoggedUser(req);
		if (user == null) {
			return Protocol.ERROR;
		}

		List<Post> posts = new ArrayList<Post>();
		posts = PostDAOImpl.getInstance().findAllByUserIdNotBanned(user.getId());
		String postsToJSON = gson.toJson(posts);

		return postsToJSON;

	}

}
