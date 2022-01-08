package it.unical.demacs.informatica.digitales.app.portfolio;

import javax.servlet.http.HttpServletRequest;

public class ProjectsHandler {

	public static String initProjectsPage(HttpServletRequest req, String username) {
		
		return "portfolio_projects";
	}
	
}
