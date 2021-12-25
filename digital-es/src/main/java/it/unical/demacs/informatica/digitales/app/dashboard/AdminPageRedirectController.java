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

	@PostMapping("/admin-page")
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
		
		return "admin_page";
		
	}
	
	@GetMapping("/admin-page")
	public String goToUserAdminPageGET(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		
		Cookie[] cookies = req.getCookies();
		
		for (Cookie c : cookies) {
			if (c.getName().equals("logged_username")) {
				String username = c.getValue();
				fetchUserData(req, username);
				return "admin_page";
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
		
	}
	
	@GetMapping("/admin")
	public String testAdminPage(HttpServletRequest req, String username) throws JsonSyntaxException, JsonIOException, IOException {
		return "admin_page";
	}

	
}
