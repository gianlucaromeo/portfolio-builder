package it.unical.demacs.informatica.digitales.app.dashboard;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;

@Controller
@RequestMapping("/dashboard")
public class AdminPageRedirectController {

	@PostMapping("/{username}/admin-page")
	public String goToUserAdminPage(@PathVariable String username, HttpServletRequest req) {
		req.setAttribute("username", username);
		return "admin_page";
	}
	@GetMapping("/admin-page")
	public String goToUserAdminPageGet( HttpServletRequest req) {
	//	req.setAttribute("username", username);
		return "admin_page";
	}
	
}
