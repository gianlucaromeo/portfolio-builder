package it.unical.demacs.informatica.digitales.app.dashboard;

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

import it.unical.demacs.informatica.digitales.app.beans.BannedUser;
import it.unical.demacs.informatica.digitales.app.beans.Moderator;
import it.unical.demacs.informatica.digitales.app.beans.Post;
import it.unical.demacs.informatica.digitales.app.beans.Project;
import it.unical.demacs.informatica.digitales.app.beans.RemovedPost;
import it.unical.demacs.informatica.digitales.app.beans.RemovedProject;
import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.beans.UserBanRequest;
import it.unical.demacs.informatica.digitales.app.beans.validation.PostValidatorResponse;
import it.unical.demacs.informatica.digitales.app.dao.BannedUserDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.ModeratorDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.Notification;
import it.unical.demacs.informatica.digitales.app.dao.PostDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.ProjectDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.RemovedPostDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.RemovedProjectDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;
import it.unical.demacs.informatica.digitales.app.validator.PostValidator;

@RestController
public class NotificationREST {
	@PostMapping("/get_notifications")
	public String getNotifications(HttpServletRequest req, HttpServletResponse resp) throws JsonSyntaxException, JsonIOException, IOException {
		

		Gson gson = new Gson();

		User user = Servlets.getLoggedUser(req);
		if (user == null) {
			return gson.toJson(Protocol.ERROR);
		}
		List<Notification> notifications= new ArrayList<Notification>();
		List<RemovedPost> remPosts = RemovedPostDAOImpl.getInstance().findByUserId(user.getId());
		for(var remPost : remPosts) {
			Notification n= new Notification();
			Post p= PostDAOImpl.getInstance().findById(remPost.getPostId());
			n.setReason(remPost.getReason());
			n.setTitle(p.getTitle());
			n.setContentDescription(p.getDescription());
			notifications.add(n);
		}
		List<RemovedProject> remProjects = RemovedProjectDAOImpl.getInstance().findByUserId(user.getId());
		for(var remProject : remProjects) {
			Notification n= new Notification();
			Project p= ProjectDAOImpl.getInstance().findById(remProject.getProjectId());
			n.setReason(remProject.getReason());
			n.setTitle(p.getTitle());
			n.setContentDescription(p.getDescription());
			notifications.add(n);
		}
		return gson.toJson(notifications);

	

	}

}
