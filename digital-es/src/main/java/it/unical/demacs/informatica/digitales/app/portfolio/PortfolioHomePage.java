package it.unical.demacs.informatica.digitales.app.portfolio;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/users")
public class PortfolioHomePage {

	@GetMapping(value = {"/{username}", "/{username}/homepage"})
	public String showUserHomePage(@PathVariable String username, HttpServletRequest req) {
		return HomePageHandler.initHomePage(req, username);
	}
	
	@GetMapping("/{username}/projects")
	public String showUserProjectsPage() {
		return "portfolio_projects";
	}
	
	@GetMapping("/{username}/posts")
	public String showUserPostsPage() {
		return "portfolio_posts";
	}
	
	@GetMapping("/{username}/curriculum")
	public String showUserCurriculumPage() {
		return "portfolio_cv";
	}
	
}
