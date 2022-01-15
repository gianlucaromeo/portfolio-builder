package it.unical.demacs.informatica.digitales.app.portfolio;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import it.unical.demacs.informatica.digitales.app.beans.Project;
import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.beans.UserMainInformations;
import it.unical.demacs.informatica.digitales.app.dao.ProjectDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserMainInformationsDAOImpl;

public class ProjectsHandler {

	public static String initProjectsPage(HttpServletRequest req, String username) {

		if (!UserDAOImpl.getInstance().checkUsernameExists(username)) {
			return "404_page";
		}
		User user = UserDAOImpl.getInstance().findByUsername(username);
		UserMainInformations info = UserMainInformationsDAOImpl.getInstance().findById(user.getId());

		List<Project> projects = ProjectDAOImpl.getInstance().findAllByUserIdNotRemoved(user.getId());

		boolean noProjects = false;
		if (projects.isEmpty())
			noProjects = true;

		req.setAttribute("projects", projects);
		req.setAttribute("firstName", user.getFirstName());
		req.setAttribute("lastName", user.getLastName());
		req.setAttribute("facebook", info.getFacebookLinkRef());
		req.setAttribute("twitter", info.getTwitterLinkRef());
		req.setAttribute("instagram", info.getInstagramLinkRef());
		req.setAttribute("emptyProject", noProjects);

		// foto profilo
		req.setAttribute("profilePicture",
				UserMainInformationsDAOImpl.getInstance().findProfileImageById(user.getId()));

		// link
		req.setAttribute("facebookLink", info.getFacebookLinkRef());
		req.setAttribute("instagramLink", info.getInstagramLinkRef());
		req.setAttribute("twitterLink", info.getTwitterLinkRef());

		return "portfolio_projects";
	}

}
