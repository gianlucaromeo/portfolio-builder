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

				Set<Project> posts = ProjectDAOImpl.getInstance().findAllByUserId(user.getId());
				String postsToJSON = gson.toJson(posts);

				System.out.println(postsToJSON);

				return postsToJSON;
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
	
}
