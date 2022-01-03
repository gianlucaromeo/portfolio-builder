package it.unical.demacs.informatica.digitales.app.dashboard;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class Servlets {

	private Servlets() {
	}

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

	public static Cookie initLoggedUsernameCookie(HttpServletRequest req, String username) {

		Cookie cookie = Servlets.getCookie(req, "logged_username");

		if (cookie == null) {
			System.out.println("created new cookie: " + username);
			cookie = new Cookie("logged_username", username);
		} else {
			System.out.println("set new value: " + username);
			cookie.setValue(username);
		}

		cookie.setMaxAge(60 * 60 * 24);
		cookie.setPath("/");

		return cookie;

	}

	public static void redirectLogin(HttpServletResponse resp, Cookie cookie) {
		System.out.println("in redirectLogin: Username = " + cookie.getValue());
		resp.addCookie(cookie);
		resp.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
		resp.setHeader("Location", "/dashboard/profile");
	}

	public static String redirect(HttpServletRequest req, String location) {

		Cookie loggedUsernameCookie = Servlets.getCookie(req, "logged_username");
		System.out.println("in redirect: usernaem = " + loggedUsernameCookie.getValue());

		if (loggedUsernameCookie == null) {
			return "error_page";
		}

		String username = loggedUsernameCookie.getValue();
		fetchUserData(req, username);

		return location;

	}

	public static User getLoggedUser(HttpServletRequest req) {

		Cookie loggedUsernameCookie = Servlets.getCookie(req, "logged_username");

		if (loggedUsernameCookie == null) {
			return null;
		}

		String username = loggedUsernameCookie.getValue();
		User user = UserDAOImpl.getInstance().findByUsername(username);
		return user;

	}

	private static void fetchUserData(HttpServletRequest req, String username) {

		User user = (User) UserDAOImpl.getInstance().findByUsername(username);

		req.setAttribute("username", username);
		req.setAttribute("firstName", user.getFirstName());
		req.setAttribute("lastName", user.getLastName());
		req.setAttribute("email", user.getEmail());

		req.setAttribute("dateOfBirth", user.getDateOfBirth());

	}

}
