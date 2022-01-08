package it.unical.demacs.informatica.digitales.app.portfolio;

import javax.servlet.http.HttpServletRequest;

public class PostsHandler {

	public static String initPostsPage(HttpServletRequest req, String username) {

		return "portfolio_posts";
	}
}
