package it.unical.demacs.informatica.digitales.app.dashboard;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unical.demacs.informatica.digitales.app.beans.Moderator;
import it.unical.demacs.informatica.digitales.app.beans.Post;
import it.unical.demacs.informatica.digitales.app.beans.PostBanRequest;
import it.unical.demacs.informatica.digitales.app.beans.Project;
import it.unical.demacs.informatica.digitales.app.beans.RemovedPost;
import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.dao.ModeratorDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.PostDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.ProjectDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.RemovedPostDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserMainInformationsDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;
import it.unical.demacs.informatica.digitales.app.settings.BanReasons;
import it.unical.demacs.informatica.digitales.app.settings.RemoveProjectReasons;

@RestController
public class ModeratorProjectsREST {

	@PostMapping("/get_projects_by_user_id")
	public String getUserPostsAction(HttpServletRequest req, HttpServletResponse resp)
			throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();

		Long id = gson.fromJson(req.getReader(), Long.class);

		Cookie moderatorCookie = Servlets.getCookie(req, "logged_moderator");

		if (moderatorCookie != null) {

			Set<Project> projects = new HashSet<Project>();
			projects = ProjectDAOImpl.getInstance().findAllByUserId(id);
			String projectsToJSON = gson.toJson(projects);

			return projectsToJSON;
			
		}

		return Protocol.ERROR;

	}
	
	@PostMapping("/get_remove_projects_reasons")
	public String getBanReasons(HttpServletRequest req, HttpServletResponse resp)
			throws JsonSyntaxException, JsonIOException, IOException {
		
		Gson gson = new Gson();
		Cookie moderatorCookie = Servlets.getCookie(req, "logged_moderator");
		if (moderatorCookie != null) {
			List<String> reasons = new ArrayList<String>();
			reasons.add(RemoveProjectReasons.INAPPROPRIATE_CONTENT);
			String reasonsToJSON = gson.toJson(reasons);
			return reasonsToJSON;
		}

		return Protocol.ERROR;

	}
/*
	@PostMapping("/ban_post")
	public String banPost(HttpServletRequest req, HttpServletResponse resp)
			throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();
		PostBanRequest banReq = gson.fromJson(req.getReader(), PostBanRequest.class);
		Post post = new Post();

		Cookie moderatorCookie = Servlets.getCookie(req, "logged_moderator");
		if (moderatorCookie != null) {
			post = PostDAOImpl.getInstance().findById(banReq.getPostId());
			if (post != null) {
				RemovedPost rem = new RemovedPost();
				Moderator m = ModeratorDAOImpl.getInstance().findByUsername(moderatorCookie.getValue());
				rem.setModeratorId(m.getId());
				rem.setPostId(banReq.getPostId());
				rem.setReason(banReq.getReason());
				RemovedPostDAOImpl.getInstance().create(rem);
			}
		}

		return Protocol.ERROR;

	}
	*/

}
