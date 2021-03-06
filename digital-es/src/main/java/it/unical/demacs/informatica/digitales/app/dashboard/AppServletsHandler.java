package it.unical.demacs.informatica.digitales.app.dashboard;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.dao.ModeratorDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserMainInformationsDAOImpl;


/**
 * Utility class to handle Cookies and redirects.
 * */
public class AppServletsHandler {

	private AppServletsHandler() {}

	public static Cookie getCookie(HttpServletRequest request, String name) {
		if (request.getCookies() != null) {
			for (Cookie cookie : request.getCookies()) {
				if (cookie.getName().equals(name)) {
					return cookie;
				}
			}
		}

		return null;
	}

	public static Cookie initLoggedUsernameCookie(HttpServletRequest req, HttpServletResponse resp, String username) {

		Cookie cookie = AppServletsHandler.getCookie(req, "logged_username");

		Cookie moderatorCookie = AppServletsHandler.getCookie(req, "logged_moderator");
		deleteCookie(resp, moderatorCookie);

		if (cookie == null) {
			cookie = new Cookie("logged_username", username);
		} else {
			cookie.setValue(username);
		}

		cookie.setMaxAge(60 * 60 * 24);
		cookie.setPath("/");

		return cookie;

	}

	public static Cookie initLoggedModeratorUsernameCookie(HttpServletRequest req, HttpServletResponse resp,
			String username) {
		Cookie cookie = AppServletsHandler.getCookie(req, "logged_moderator");

		Cookie userCookie = AppServletsHandler.getCookie(req, "logged_username");
		deleteCookie(resp, userCookie);

		if (cookie == null) {
			cookie = new Cookie("logged_moderator", username);
		} else {
			cookie.setValue(username);
		}

		cookie.setMaxAge(60 * 60 * 24);
		cookie.setPath("/");

		return cookie;
	}

	public static void redirectLogin(HttpServletResponse resp, Cookie cookie) {
		resp.addCookie(cookie);
		resp.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
		resp.setHeader("Location", "/dashboard/profile");
	}
	
	public static void redirectModeratorLogin(HttpServletResponse resp, Cookie cookie) {
		resp.addCookie(cookie);
		resp.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
		resp.setHeader("Location", "/dashboard/moderator/all_users");
	}

	public static String redirect(HttpServletRequest req, String location) {

		Cookie loggedUsernameCookie = AppServletsHandler.getCookie(req, "logged_username");

		if (loggedUsernameCookie == null) {
			return "401_page";
		}

		String username = loggedUsernameCookie.getValue();
		fetchUserData(req, username);

		return location;

	}
	public static String redirectOnPage(String location) {
		return location;
	}

	public static String redirectModerator(HttpServletRequest req, String location) {

		Cookie loggedModeratorCookie = AppServletsHandler.getCookie(req, "logged_moderator");

		if (loggedModeratorCookie == null) {
			return "401_page";
		}
		
		fetchModeratorData(req, loggedModeratorCookie.getValue());

		return location;

	}

	private static void fetchModeratorData(HttpServletRequest req, String username) {
		req.setAttribute("username", username);
		String profilePicture = ModeratorDAOImpl.getInstance().findDeafultModeratorImage();
		req.setAttribute("profilePicture", profilePicture);
		
	}

	public static User getLoggedUser(HttpServletRequest req) {
		Cookie loggedUsernameCookie = AppServletsHandler.getCookie(req, "logged_username");

		if (loggedUsernameCookie == null) {
			return null;
		}

		String username = loggedUsernameCookie.getValue();
		User user = UserDAOImpl.getInstance().findByUsername(username);
		return user;

	}

	public static void fetchUserData(HttpServletRequest req, String username) {

		User user = (User) UserDAOImpl.getInstance().findByUsername(username);

		req.setAttribute("username", username);
		req.setAttribute("firstName", user.getFirstName());
		req.setAttribute("lastName", user.getLastName());
		req.setAttribute("email", user.getEmail());

		req.setAttribute("dateOfBirth", user.getDateOfBirth());

		String profilePicture = UserMainInformationsDAOImpl.getInstance().findProfileImageById(user.getId());
		req.setAttribute("profilePicture", profilePicture);

	}

	public static void deleteCookie(HttpServletResponse resp, Cookie cookie) {
		if (cookie != null) {
			cookie.setMaxAge(0);
			cookie.setValue(null);
			cookie.setPath("/");
			resp.addCookie(cookie);
		}

	}

}
