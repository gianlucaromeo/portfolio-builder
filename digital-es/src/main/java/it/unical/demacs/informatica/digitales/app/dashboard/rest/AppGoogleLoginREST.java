package it.unical.demacs.informatica.digitales.app.dashboard.rest;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.dashboard.AppServletsHandler;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

@RestController
public class AppGoogleLoginREST {

	@PostMapping("/google_login")
	public synchronized String loginGoogle(HttpServletRequest req, HttpServletResponse resp)
			throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();
		String email = new String();
		email = gson.fromJson(req.getReader(), String.class);
		if (UserDAOImpl.getInstance().checkEmailExists(email)) {

			String username = UserDAOImpl.getInstance().getUsernameByEmail(email);
			boolean bannedUser= UserDAOImpl.getInstance().isBannedByUsername(username);
			if(bannedUser) {
				return gson.toJson(Protocol.BANNED_USER);
			}else {
				Cookie cookie = AppServletsHandler.initLoggedUsernameCookie(req, resp, username);
				resp.addCookie(cookie);
				// Servlets.redirectLogin(resp, cookie);

				return gson.toJson(Protocol.OK);
			}
			
		}

		return gson.toJson(Protocol.ERROR);

	}
}
