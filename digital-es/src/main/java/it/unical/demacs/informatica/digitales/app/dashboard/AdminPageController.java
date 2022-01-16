package it.unical.demacs.informatica.digitales.app.dashboard;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class AdminPageController {
	
	@PostMapping("/profile")
	public synchronized String goToUserAdminPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		return Servlets.redirect(req, "dashboard_profile");
	}

	@GetMapping("/profile")
	public synchronized String goToUserAdminPageGET(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		return Servlets.redirect(req, "dashboard_profile");

	}

	@GetMapping("/projects")
	public synchronized String showProjectsPageGET(HttpServletRequest req, HttpServletResponse resp) {
		return Servlets.redirect(req, "dashboard_projects");
	}

	@GetMapping("/curriculum")
	public synchronized String showCurriculumPageGET(HttpServletResponse resp, HttpServletRequest req) {
		return Servlets.redirect(req, "dashboard_cv");
	}

	@GetMapping("/posts")
	public synchronized String showPostsPageGET(HttpServletResponse resp, HttpServletRequest req) {
		return Servlets.redirect(req, "dashboard_posts");
	}
	
	@GetMapping("/404_page")
	public synchronized String go404PageGET(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		return Servlets.redirectOnPage("404_page");

	}
	@GetMapping("/500_page")
	public synchronized String go500PageGET(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		return Servlets.redirectOnPage("500_page");

	}
	
}
