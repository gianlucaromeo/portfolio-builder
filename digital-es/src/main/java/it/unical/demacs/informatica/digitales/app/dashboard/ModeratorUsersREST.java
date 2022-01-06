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
import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.beans.UserBanRequest;
import it.unical.demacs.informatica.digitales.app.dao.BannedUserDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.ModeratorDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;
import it.unical.demacs.informatica.digitales.app.settings.BanReasonsUser;
@RestController
public class ModeratorUsersREST {
	@PostMapping("/ban_user")
	public long banUser(HttpServletRequest req, HttpServletResponse resp) throws JsonSyntaxException, JsonIOException, IOException {
		
		Gson gson = new Gson();
		UserBanRequest banReq = gson.fromJson(req.getReader(), UserBanRequest.class);
		User user = new User();
		
		Cookie moderatorCookie= Servlets.getCookie(req, "logged_moderator");
		if (moderatorCookie != null) {
			user = UserDAOImpl.getInstance().findById(banReq.getUserId());
			if (user != null) {
				Moderator mod= new Moderator();
				mod= ModeratorDAOImpl.getInstance().findByUsername(moderatorCookie.getValue());
				BannedUser banUser= new BannedUser();
				banUser.setUserId(banReq.getUserId());
				banUser.setDateStart(banReq.getDateStart());
				banUser.setDateEnd(banReq.getDateEnd());
				banUser.setModeratorId(mod.getId());
				banUser.setReason(banReq.getReason());
				
				BannedUserDAOImpl.getInstance().create(banUser);
				return banUser.getUserId();
			}
		}
		


		return -1;

	}
	
	@PostMapping("/get_users_ban_reasons")
	public String getBanReasonsUser(HttpServletRequest req, HttpServletResponse resp)
			throws JsonSyntaxException, JsonIOException, IOException {
		Gson gson = new Gson();
		Cookie moderatorCookie = Servlets.getCookie(req, "logged_moderator");
		if (moderatorCookie != null) {
			List<String> reasons = new ArrayList<String>();
			reasons.add(BanReasonsUser.POLICY_RULES);
			String reasonsToJSON = gson.toJson(reasons);
			return reasonsToJSON;
		}

		return Protocol.ERROR;

	}

}
