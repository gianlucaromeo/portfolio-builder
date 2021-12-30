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
import it.unical.demacs.informatica.digitales.app.dao.ProjectDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

@RestController
public class ProjectsREST {
	
	@PostMapping("/load_projects")
	public String loadProjects(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {
		Gson gson = new Gson();

		Cookie[] cookies = req.getCookies();
		for (Cookie c : cookies) {
			System.out.println("Cookie: " + c.getName());
			if (c.getName().equals("logged_username")) {
				String username = c.getValue();
				User user = UserDAOImpl.getInstance().findByUsername(username);

				Set<Project> projects = ProjectDAOImpl.getInstance().findAllByUserId(user.getId());
				String projectsToJSON = gson.toJson(projects);

				System.out.println(projectsToJSON);

				return projectsToJSON;
			}
		}

		return Protocol.ERROR;
	}
	
	@PostMapping("delete_project")
	public void deleteProject(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {
		Gson gson = new Gson();
		Integer id = gson.fromJson(req.getReader(), Integer.class);
		Project project = new Project();
		project.setId(id);
		System.out.println(id);
		ProjectDAOImpl.getInstance().delete(project);
	}
	
	@PostMapping("edit_project")
	public String editProject(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {
		Gson gson = new Gson();
		Project project = new Project();
		project = gson.fromJson(req.getReader(), Project.class);
		Project originalProject= ProjectDAOImpl.getInstance().findById(project.getId());
		
		project.setUserId(originalProject.getUserId());
		ProjectDAOImpl.getInstance().update(project);
		return gson.toJson(project);
	}
	
	@PostMapping("add_project")
	public String addProject(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {
		Gson gson = new Gson();
		Project project = new Project();
		User user=null;
		project = gson.fromJson(req.getReader(), Project.class);
		Cookie[] cookies = req.getCookies();
		for (Cookie c : cookies) {
			if (c.getName().equals("logged_username")) {
				String username = c.getValue();
				user = UserDAOImpl.getInstance().findByUsername(username);
			}

		}
		if(user!=null) {
			project.setUserId(user.getId());
			ProjectDAOImpl.getInstance().create(project);
			return gson.toJson(project);
			
		}else {
			return Protocol.ERROR;
		}
	}
	
}
