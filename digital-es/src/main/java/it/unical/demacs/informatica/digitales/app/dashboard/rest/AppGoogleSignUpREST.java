package it.unical.demacs.informatica.digitales.app.dashboard.rest;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.beans.UserMainInformations;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserMainInformationsDAOImpl;
import it.unical.demacs.informatica.digitales.app.dashboard.AppServletsHandler;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

@RestController
public class AppGoogleSignUpREST {

	@PostMapping("/sign_up_get_new_google_username")
	public String getNextId(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {

		Integer usersCounter = UserDAOImpl.getInstance().getUsersCounter();

		String username = "googleUser" + usersCounter;
		while (UserDAOImpl.getInstance().checkUsernameExists(username)) {
			// System.out.println("Username " + username + " already exists");
			usersCounter++;
			username = "googleUser" + usersCounter;
		}

		return new Gson().toJson(username);

	}

	@PostMapping("/sign_up_verify_username_exists_googleRest")
	public String verifyUsername(HttpServletRequest req, String username)
			throws JsonSyntaxException, JsonIOException, IOException {
		boolean usernameExists = UserDAOImpl.getInstance().checkUsernameExists(username);
		return usernameExists ? Protocol.ERROR : Protocol.OK;
	}

	@PostMapping("/check_google_user_exists")
	public String checkGoogleUserExists(HttpServletRequest req)
			throws JsonSyntaxException, JsonIOException, IOException {
		String email = new Gson().fromJson(req.getReader(), String.class);

		String username = UserDAOImpl.getInstance().getUsernameByEmail(email);
		// System.out.println(username);
		if (username != null)
			return new Gson().toJson(email);
		else
			return new Gson().toJson(null);
	}

	@PostMapping("/sign_up_with_google")
	public String signUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Gson gson = new Gson();
		User user = new User();
		user = gson.fromJson(req.getReader(), User.class);
		System.out.println(user);

		String username = UserDAOImpl.getInstance().getUsernameByEmail(user.getEmail());
		System.out.println(username);
		if (username == null) {
			user.setSignUpDate(DateTime.now().toString("yyyy-MM-dd"));
			UserDAOImpl.getInstance().create(user);

			user.setId(UserDAOImpl.getInstance().findId(user));

			UserMainInformations info = new UserMainInformations();
			info.setUserId(user.getId());
			UserMainInformationsDAOImpl.getInstance().create(info);
			username = user.getUsername();
			System.out.println("Sign up with google");
		}

		System.out.println(username);
		Cookie cookie = AppServletsHandler.initLoggedUsernameCookie(req, resp, username);
		resp.addCookie(cookie);

		return gson.toJson(Protocol.OK);

	}

}
