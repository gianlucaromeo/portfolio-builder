package it.unical.demacs.informatica.digitales.app.dashboard;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard/moderator")
public class ModeratorAdminPageController {
	
	@GetMapping("/all_posts")
	public synchronized String goToAllPostsModeratorPageGET(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		return AppServletsHandler.redirectModerator(req, "moderator_posts");
	}

	@GetMapping("/all_projects")
	public synchronized String goToAllProjectsPageGET(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		return AppServletsHandler.redirectModerator(req, "moderator_projects");
	}

	@GetMapping("/all_users")
	public synchronized String goToAllUsersPageGET(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		return AppServletsHandler.redirectModerator(req, "moderator_users");
	}
	
	@PostMapping("/all_users")
	public synchronized String goToAllUsersPagePOST(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		return AppServletsHandler.redirectModerator(req, "moderator_users");
	}
	
}
