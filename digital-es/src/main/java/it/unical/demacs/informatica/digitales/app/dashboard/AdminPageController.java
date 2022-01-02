package it.unical.demacs.informatica.digitales.app.dashboard;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unical.demacs.informatica.digitales.app.beans.CurriculumExperience;
import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.dao.CurriculumExperienceDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;

@Controller
@RequestMapping("/dashboard")
public class AdminPageController {

	@PostMapping("/profile")
	public String goToUserAdminPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		Cookie[] cookies = req.getCookies();
		String username = null;

		for (Cookie c : cookies) {
			if (c.getName().equals("logged_username")) {
				username = c.getValue();
				break;
			}
		}

		fetchUserData(req, username);
		System.out.println("devo essere reindirizzato ma non succede nulla");
		return "profile";

	}

	@GetMapping("/profile")
	public String goToUserAdminPageGET(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		Cookie[] cookies = req.getCookies();

		for (Cookie c : cookies) {
			if (c.getName().equals("logged_username")) {
				String username = c.getValue();
				System.out.println(c.getValue());
				fetchUserData(req, username);
				return "profile";
			}
		}
		return "error_page";

	}
	
	@GetMapping("/moderator_posts")
	public String goToPostsAdminPageGET(HttpServletRequest req, HttpServletResponse resp) throws IOException {

		Cookie[] cookies = req.getCookies();

		for (Cookie c : cookies) {
			if (c.getName().equals("logged_username")) {
				String username = c.getValue();
				System.out.println(c.getValue());
				fetchUserData(req, username);
				System.out.println("moderator");
				return "moderator_posts";
			}
		}
		return "error_page";

	}

	private void fetchUserData(HttpServletRequest req, String username) {

		User user = (User) UserDAOImpl.getInstance().findByUsername(username);

		req.setAttribute("username", username);
		req.setAttribute("firstName", user.getFirstName());
		req.setAttribute("lastName", user.getLastName());
		req.setAttribute("email", user.getEmail());

		req.setAttribute("dateOfBirth", user.getDateOfBirth());

	}

	@GetMapping("/profile_test")
	public String testAdminPage(HttpServletRequest req, String username)
			throws JsonSyntaxException, JsonIOException, IOException {
		return "profile";
	}

	@GetMapping("/posts_test")
	public String testPostsPage(HttpServletRequest req, String username)
			throws JsonSyntaxException, JsonIOException, IOException {
		return "posts";
	}

	@GetMapping("/projects_test")
	public String testProjectsPage(HttpServletRequest req, String username)
			throws JsonSyntaxException, JsonIOException, IOException {
		return "projects";
	}

	@GetMapping("/projects")
	public String showProjectsPage(HttpServletRequest req, HttpServletResponse resp) {

		Cookie[] cookies = req.getCookies();

		for (Cookie c : cookies) {
			if (c.getName().equals("logged_username")) {
				String username = c.getValue();
				fetchUserData(req, username);
				return "projects";
			}
		}

		return "error_page";
	}

	@GetMapping("/curriculum")
	public String showCurriculumPageGET(HttpServletResponse resp, HttpServletRequest req) {

		String username = null;
		Cookie[] cookies = req.getCookies();

		for (Cookie c : cookies) {

			if (c.getName().equals("logged_username")) {
				
				username = c.getValue();
				Cookie cookie = new Cookie("logged_username", username);
				cookie.setMaxAge(60 * 60 * 24);

				req.setAttribute("username", username);
				resp.addCookie(cookie);

			}

		}

		if (username == null) {
			return "error_page";
		}

		return "curriculum";

	}


	@GetMapping("/posts")
	public String showPostsPageGET(HttpServletResponse resp, HttpServletRequest req) {

		String username = null;
		Cookie[] cookies = req.getCookies();

		for (Cookie c : cookies) {

			if (c.getName().equals("logged_username")) {

				username = c.getValue();
				Cookie cookie = new Cookie("logged_username", username);
				cookie.setMaxAge(60 * 60 * 24);
					
				resp.addCookie(cookie);
				fetchUserData(req, username);

			}

		}

		if (username == null) {
			return "error_page";
		}

		return "posts";

	}


}
