package it.unical.demacs.informatica.digitales.app.dashboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.beans.UserMainInformations;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserMainInformationsDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

@RestController
public class GoogleSignUpREST {

	@PostMapping("/sign_up_get_new_google_username")
	public String getNextId(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {

		Integer usersCounter = UserDAOImpl.getInstance().getUsersCounter();

		String username = "googleUser" + usersCounter;
		while (UserDAOImpl.getInstance().checkUsernameExists(username)) {
			System.out.println("Username " + username + " already exists");
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

	@PostMapping("/dashboard/sign_up_with_google")
	public String signUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		Gson gson = new Gson();
		User user = new User();
		user = gson.fromJson(req.getReader(), User.class);

		UserDAOImpl.getInstance().create(user);
		
		user.setId(UserDAOImpl.getInstance().findId(user));

		UserMainInformations info = new UserMainInformations();
		info.setUserId(user.getId());
		UserMainInformationsDAOImpl.getInstance().create(info);

		Cookie cookie = Servlets.initLoggedUsernameCookie(req, resp, user.getUsername());

		Servlets.redirectLogin(resp, cookie);

		return Protocol.OK;
		
	}

}
