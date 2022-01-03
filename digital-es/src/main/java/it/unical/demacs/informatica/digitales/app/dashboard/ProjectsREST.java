package it.unical.demacs.informatica.digitales.app.dashboard;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unical.demacs.informatica.digitales.app.beans.Project;
import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.beans.validation.PostValidatorResponse;
import it.unical.demacs.informatica.digitales.app.beans.validation.ProjectValidatorResponse;
import it.unical.demacs.informatica.digitales.app.dao.PostDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.ProjectDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;
import it.unical.demacs.informatica.digitales.app.validator.PostValidator;
import it.unical.demacs.informatica.digitales.app.validator.ProjectValidator;

@RestController
public class ProjectsREST {

	@PostMapping("/load_projects")
	public String loadProjects(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();

		User user = Servlets.getLoggedUser(req);

		if (user == null) {
			return Protocol.ERROR;
		}

		Set<Project> projects = ProjectDAOImpl.getInstance().findAllByUserId(user.getId());
		String projectsToJSON = gson.toJson(projects);
		return projectsToJSON;

	}

	@PostMapping("delete_project")
	public void deleteProject(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {
		Gson gson = new Gson();
		Integer id = gson.fromJson(req.getReader(), Integer.class);
		Project project = new Project();
		project.setId(id);
		ProjectDAOImpl.getInstance().delete(project);
	}

	@PostMapping("edit_project")
	public String editProject(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {
		Gson gson = new Gson();
		Project project = new Project();
		project = gson.fromJson(req.getReader(), Project.class);
		Project originalProject = ProjectDAOImpl.getInstance().findById(project.getId());
		project.setUserId(originalProject.getUserId());

		ProjectValidatorResponse newProject = ProjectValidator.validateProject(project);
		if (ProjectValidator.isValidProject(newProject)) {
			ProjectDAOImpl.getInstance().update(project);
			project.setId(ProjectDAOImpl.getInstance().findId(project));
		}

		return gson.toJson(newProject);
	}

	@PostMapping("restore_project")
	public String restoreProject(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {
		Gson gson = new Gson();
		Integer id = gson.fromJson(req.getReader(), Integer.class);
		Project originalProject = ProjectDAOImpl.getInstance().findById(id);

		return gson.toJson(originalProject);
	}

	@PostMapping("add_project")
	public String addProject(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {
		Gson gson = new Gson();
		Project project = new Project();
		project = gson.fromJson(req.getReader(), Project.class);
		User user = Servlets.getLoggedUser(req);
		ProjectValidatorResponse newProject = ProjectValidator.validateProject(project);
		if (user != null) {
			project.setUserId(user.getId());
			newProject.setUserId(user.getId());
			if (ProjectValidator.isValidProject(newProject)) {
				ProjectDAOImpl.getInstance().create(project);
				project.setId(ProjectDAOImpl.getInstance().findId(project));
				newProject.setId(project.getId());
			}

		}
		return gson.toJson(newProject);
	}

}
