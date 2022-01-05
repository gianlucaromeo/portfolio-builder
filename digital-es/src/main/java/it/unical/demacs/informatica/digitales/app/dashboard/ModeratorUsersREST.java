package it.unical.demacs.informatica.digitales.app.dashboard;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unical.demacs.informatica.digitales.app.beans.BannedUser;
import it.unical.demacs.informatica.digitales.app.beans.Moderator;
import it.unical.demacs.informatica.digitales.app.beans.Post;
import it.unical.demacs.informatica.digitales.app.beans.PostBanRequest;
import it.unical.demacs.informatica.digitales.app.beans.RemovedPost;
import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.dao.ModeratorDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.PostDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.RemovedPostDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class ModeratorUsersREST {
	@PostMapping("/ban_user")
	public String banUser(HttpServletRequest req, HttpServletResponse resp) throws JsonSyntaxException, JsonIOException, IOException {
		
		Gson gson = new Gson();
		Integer userId = gson.fromJson(req.getReader(), Integer.class);
		User user = new User();
		
		Cookie moderatorCookie= Servlets.getCookie(req, "logged_moderator");
		if (moderatorCookie != null) {
			user = UserDAOImpl.getInstance().findById(userId);
			if (user != null) {
				Moderator mod= new Moderator();
				mod= ModeratorDAOImpl.getInstance().findByUsername(moderatorCookie.getValue());
				BannedUser banUser= new BannedUser();
				banUser.setUserId(userId);
				banUser.setDateStart(null);
				banUser.setDateEnd(null);
				banUser.setModeratorId(mod.getId());
				banUser.setReason(null);
			}
		}
		


		return Protocol.ERROR;

	}

}
