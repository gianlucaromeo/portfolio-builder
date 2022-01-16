package it.unical.demacs.informatica.digitales.app.dashboard.rest.moderator;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unical.demacs.informatica.digitales.app.beans.Moderator;
import it.unical.demacs.informatica.digitales.app.beans.Project;
import it.unical.demacs.informatica.digitales.app.beans.RemoveProjectRequest;
import it.unical.demacs.informatica.digitales.app.beans.RemovedProject;
import it.unical.demacs.informatica.digitales.app.dao.ModeratorDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.ProjectDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.RemovedProjectDAOImpl;
import it.unical.demacs.informatica.digitales.app.dashboard.AppServletsHandler;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;
import it.unical.demacs.informatica.digitales.app.settings.RemoveProjectReasons;

@RestController
public class ModeratorProjectsREST {

	@PostMapping("/get_projects_by_user_id")
	public String getUserPostsAction(HttpServletRequest req, HttpServletResponse resp)
			throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();

		Long id = gson.fromJson(req.getReader(), Long.class);

		Cookie moderatorCookie = AppServletsHandler.getCookie(req, "logged_moderator");

		if (moderatorCookie != null) {

			List<Project> projects = new ArrayList<Project>();
//			projects = ProjectDAOImpl.getInstance().findAllByUserId(id);
			projects = ProjectDAOImpl.getInstance().findAllByUserIdNotRemoved(id);
			String projectsToJSON = gson.toJson(projects);

			return projectsToJSON;

		}

		return Protocol.ERROR;

	}

	@PostMapping("/get_remove_projects_reasons")
	public String getBanReasons(HttpServletRequest req, HttpServletResponse resp)
			throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();
		Cookie moderatorCookie = AppServletsHandler.getCookie(req, "logged_moderator");
		if (moderatorCookie != null) {
			List<String> reasons = new ArrayList<String>();
			reasons.add(RemoveProjectReasons.INAPPROPRIATE_CONTENT);
			String reasonsToJSON = gson.toJson(reasons);
			return reasonsToJSON;
		}

		return Protocol.ERROR;

	}

	@PostMapping("/remove_user_project")
	public String banPost(HttpServletRequest req, HttpServletResponse resp)
			throws JsonSyntaxException, JsonIOException, IOException {

		System.out.println("REMOVE PROJECT REQUEST");
		Gson gson = new Gson();
		RemoveProjectRequest removeProjectReq = gson.fromJson(req.getReader(), RemoveProjectRequest.class);
		Project projectToRemove = new Project();

		Cookie moderatorCookie = AppServletsHandler.getCookie(req, "logged_moderator");
		if (moderatorCookie != null) {
			projectToRemove = ProjectDAOImpl.getInstance().findById(removeProjectReq.getId());
			if (projectToRemove != null) {
				RemovedProject removedProject = new RemovedProject();
				Moderator m = ModeratorDAOImpl.getInstance().findByUsername(moderatorCookie.getValue());
				removedProject.setModeratorId(m.getId());
				removedProject.setProjectId(removeProjectReq.getId());
				removedProject.setReason(removeProjectReq.getReason());
				removedProject.setSeenByUser(false);
				System.out.println("REMOVE PROJECT REQUEST OK");
				return RemovedProjectDAOImpl.getInstance().create(removedProject);
			}
		}

		System.out.println("REMOVE PROJECT REQUEST ERROR");
		return Protocol.ERROR;

	}

}
