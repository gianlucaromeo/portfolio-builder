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

import it.unical.demacs.informatica.digitales.app.beans.Moderator;
import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.beans.UserAuthentication;
import it.unical.demacs.informatica.digitales.app.dao.ModeratorDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.dashboard.AppServletsHandler;
import it.unical.demacs.informatica.digitales.app.validator.LoginValidator;

@RestController
public class AppLoginREST {

	@PostMapping("/login_data_validation")
	public synchronized String loginAction(HttpServletRequest req)
			throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();
		UserAuthentication userAuth = new UserAuthentication();
		userAuth = gson.fromJson(req.getReader(), UserAuthentication.class);

		String userAuthToJSON = gson.toJson(LoginValidator.validateUserAuth(userAuth));

		return userAuthToJSON;
	}

	@PostMapping("/dashboard/login")
	public synchronized void login(HttpServletRequest req, HttpServletResponse resp) {
		UserAuthentication userAuth = new UserAuthentication();
		userAuth.setUsername(req.getParameter("username"));

		User user = new User();
		Moderator moderator = new Moderator();

		moderator = ModeratorDAOImpl.getInstance().findByUsername(userAuth.getUsername());
		user = UserDAOImpl.getInstance().findByUsername(userAuth.getUsername());
		if (user != null) {
			Cookie cookie = AppServletsHandler.initLoggedUsernameCookie(req, resp, user.getUsername());
			AppServletsHandler.redirectLogin(resp, cookie);
			return;
		} else if (moderator != null) {
			Cookie cookie = AppServletsHandler.initLoggedModeratorUsernameCookie(req, resp, moderator.getUsername());
			AppServletsHandler.redirectModeratorLogin(resp, cookie);
		}

	}

}
