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

@RestController
public class ProjectsREST {
	
	@PostMapping("/load_user_id")
	public String loadUserId(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {

//		Cookie[] cookies = req.getCookies();
//		String username="";
//		for (Cookie c : cookies) {
//			if (c.getName().equals("logged_username")) {
//				username = c.getValue();
//				break;
//			}
//		}
		String username=(String) req.getAttribute("username");
		User user=UserDAOImpl.getInstance().findByUsername(username);
		Gson gson = new Gson();
		user = gson.fromJson(req.getReader(), User.class);
		
		user=UserDAOImpl.getInstance().findByUsername(user.getUsername());
		
		String userDataResponseToJSON = gson.toJson(user);
		
		return userDataResponseToJSON;
	}
	
	@PostMapping("/load_projects_id")
	public String loadProjectsId(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {

		//long id=(long) req.getAttribute("id");
		
		Set<Project> projects=ProjectDAOImpl.getInstance().findAll();
		String res="";
		for(Project project:projects) {
			if(project.getUserId()==44) {
				res+=project.getId();
				res+=" ";
			}
		}
		
		return res;
	}
	
}
