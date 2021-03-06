package it.unical.demacs.informatica.digitales.app.dashboard.rest;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unical.demacs.informatica.digitales.app.beans.EmailConfirmation;
import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.beans.UserMainInformations;
import it.unical.demacs.informatica.digitales.app.dao.EmailConfirmationDaoImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserMainInformationsDAOImpl;
import it.unical.demacs.informatica.digitales.app.dashboard.AppServletsHandler;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;
import it.unical.demacs.informatica.digitales.app.validator.SignUpFormValidator;

@RestController
public class AppSignUpREST {

	@PostMapping("/sign_up_data_validation")
	public String signUpAction(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();
		User user = new User();
		user = gson.fromJson(req.getReader(), User.class);

		String userDataResponseToJSON = gson.toJson(SignUpFormValidator.validateUser(user));

		return userDataResponseToJSON;

	}

	@PostMapping("/dashboard/sign_up")
	public String signUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		User user = new User();
		user = new Gson().fromJson(req.getReader(), User.class);
		user.setSignUpDate(DateTime.now().toString("yyyy-MM-dd"));

		String res = UserDAOImpl.getInstance().create(user);

		if (res.equals(Protocol.OK)) {

			user.setId(UserDAOImpl.getInstance().findId(user));

			UserMainInformations info = new UserMainInformations();
			info.setUserId(user.getId());
			UserMainInformationsDAOImpl.getInstance().create(info);
			EmailConfirmation emailConfirmation = new EmailConfirmation();
			emailConfirmation.setUserId(user.getId());
			emailConfirmation.setToken(createToken());
			EmailConfirmationDaoImpl.getInstance().create(emailConfirmation);

			return new Gson().toJson(emailConfirmation);

		} else {
			return res;
		}

	}

	@GetMapping("/confirm_email/{token}")
	public String emailConfirmationAction(@PathVariable String token, HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		long userId = EmailConfirmationDaoImpl.getInstance().findUserId(token);
		User user = UserDAOImpl.getInstance().findById(userId);

		if (user == null) {
			resp.sendRedirect("/dashboard/404_page");
			return Protocol.ERROR;
		}

		EmailConfirmation confirmation = new EmailConfirmation();
		confirmation.setToken(token);
		confirmation.setUserId(userId);
		EmailConfirmationDaoImpl.getInstance().delete(confirmation);

		user.setConfirmed(true);
		UserDAOImpl.getInstance().updateConfirmation(user);

		Cookie cookie = AppServletsHandler.initLoggedUsernameCookie(req, resp, user.getUsername());

		AppServletsHandler.redirectLogin(resp, cookie);

		return Protocol.OK;

	}

	private String createToken() {
		return UUID.randomUUID().toString();
	}

}
