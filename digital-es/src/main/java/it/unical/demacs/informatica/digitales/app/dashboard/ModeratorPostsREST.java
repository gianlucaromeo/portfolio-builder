package it.unical.demacs.informatica.digitales.app.dashboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unical.demacs.informatica.digitales.app.beans.Post;
import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.dao.PostDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserMainInformationsDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

@RestController
public class ModeratorPostsREST {
	
	@PostMapping("/get_users_data_action")
	public String getUsersDataAction(HttpServletRequest req, HttpServletResponse resp) {
		Gson gson = new Gson();

		Cookie[] cookies = req.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("logged_username")) {
				Set<User> users = new HashSet<User>();
				users = UserDAOImpl.getInstance().findAll();
				String usersToJSON = gson.toJson(users);

				return usersToJSON;
			}
		}

		return Protocol.ERROR;

	}
	
	@PostMapping("/get_user_profile_image")
	public String getUsersProfileImageAction(HttpServletRequest req, HttpServletResponse resp) throws JsonSyntaxException, JsonIOException, IOException {
		Gson gson = new Gson();
		Integer id = gson.fromJson(req.getReader(), Integer.class);

		Cookie[] cookies = req.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("logged_username")) {
				String profileImage64 = "";
				profileImage64 = UserMainInformationsDAOImpl.getInstance().findProfileImageById(id);
				String imageToJSON = gson.toJson(profileImage64);

				return imageToJSON;
			}
		}

		return Protocol.ERROR;

	}
	
	@PostMapping("/get_users_posts_by_id")
	public String getUserPostsAction(HttpServletRequest req, HttpServletResponse resp) throws JsonSyntaxException, JsonIOException, IOException {
		Gson gson = new Gson();
		Integer id = gson.fromJson(req.getReader(), Integer.class);
		Cookie[] cookies = req.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("logged_username")) {
				List<Post> posts = new ArrayList<Post>();
				posts = PostDAOImpl.getInstance().findAllByUserId(id);
				String postsToJSON = gson.toJson(posts);

				return postsToJSON;
			}
		}

		return Protocol.ERROR;

	}
	
	

}
