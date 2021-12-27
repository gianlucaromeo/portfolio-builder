package it.unical.demacs.informatica.digitales.app.dashboard;

import java.io.IOException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;

@Controller
@RequestMapping("/dashboard")
public class AdminPageRedirectController {

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
		
		return "profile";
		
	}
	
	@GetMapping("/profile")
	public String goToUserAdminPageGET(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		Cookie[] cookies = req.getCookies();
		
		for (Cookie c : cookies) {
			if (c.getName().equals("logged_username")) {
				String username = c.getValue();
				fetchUserData(req, username);
				return "profile";
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
	public String testAdminPage(HttpServletRequest req, String username) throws JsonSyntaxException, JsonIOException, IOException {
		return "profile";
	}

	
}
