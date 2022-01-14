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
	public synchronized String goToUserAdminPage(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		return Servlets.redirect(req, "profile");
	}

	@GetMapping("/profile")
	public synchronized String goToUserAdminPageGET(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		return Servlets.redirect(req, "profile");

	}

	@GetMapping("/projects")
	public synchronized String showProjectsPageGET(HttpServletRequest req, HttpServletResponse resp) {
		return Servlets.redirect(req, "projects");
	}

	@GetMapping("/curriculum")
	public synchronized String showCurriculumPageGET(HttpServletResponse resp, HttpServletRequest req) {
		return Servlets.redirect(req, "curriculum");
	}

	@GetMapping("/posts")
	public synchronized String showPostsPageGET(HttpServletResponse resp, HttpServletRequest req) {
		return Servlets.redirect(req, "posts");
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
