package it.unical.demacs.informatica.digitales.app.portfolio;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import it.unical.demacs.informatica.digitales.app.beans.Post;
import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.beans.UserMainInformations;
import it.unical.demacs.informatica.digitales.app.dao.PostDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserMainInformationsDAOImpl;

public class PostsHandler {

	public static String initPostsPage(HttpServletRequest req, String username) {
		if (!UserDAOImpl.getInstance().checkUsernameExistsNotBanned(username)) {
			return "404_page";
		}

		User user = UserDAOImpl.getInstance().findByUsername(username);
		UserMainInformations userInfo = UserMainInformationsDAOImpl.getInstance().findById(user.getId());

		// Nome e Cognome e username
		req.setAttribute("firstName", user.getFirstName());
		req.setAttribute("lastName", user.getLastName());
		//req.setAttribute("username", username);

		// foto profilo
		req.setAttribute("profilePicture", UserMainInformationsDAOImpl.getInstance().findProfileImageById(user.getId()));

		// bio
		req.setAttribute("biography", userInfo.getBio());
		
		List<Post> posts= new ArrayList<Post>();
		posts=PostDAOImpl.getInstance().findAllByUserIdNotBanned(user.getId());
		
		
		req.setAttribute("almostOnePost", true);
		if(posts.isEmpty()) {
			req.setAttribute("almostOnePost", false);
		}else {
			Collections.reverse(posts);
		}
		req.setAttribute("posts", posts);

		System.out.println("in redirect posts");
		return "portfolio_posts";
	}
}
