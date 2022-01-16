package it.unical.demacs.informatica.digitales.app.dashboard;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unical.demacs.informatica.digitales.app.beans.EmailConfirmation;
import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.beans.validation.ResetPasswordValidatorResponse;
import it.unical.demacs.informatica.digitales.app.dao.EmailConfirmationDaoImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.validator.ResetPasswordValidator;

@RestController
public class ResetPasswordControllerREST {

	@PostMapping("get_token")
	public String getToken(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {
		Gson gson = new Gson();
		ResetPasswordValidatorResponse res = new ResetPasswordValidatorResponse();
		res = gson.fromJson(req.getReader(), ResetPasswordValidatorResponse.class);

		User user = UserDAOImpl.getInstance().findByUsername(res.getToken());

		EmailConfirmation emailConfirmation = new EmailConfirmation();
		emailConfirmation.setUserId(user.getId());
		emailConfirmation.setToken(createToken());
		EmailConfirmationDaoImpl.getInstance().create(emailConfirmation);

		return gson.toJson(emailConfirmation);
	}

	@PostMapping("reset_password")
	public String resetPassword(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {
		Gson gson = new Gson();
		ResetPasswordValidatorResponse res = new ResetPasswordValidatorResponse();
		res = gson.fromJson(req.getReader(), ResetPasswordValidatorResponse.class);

		ResetPasswordValidator.validatePassword(res);

		long userId = EmailConfirmationDaoImpl.getInstance().findUserId(res.getToken());
		User selectedUser = UserDAOImpl.getInstance().findById(userId);
		if (selectedUser != null && res.getPassword().equals(res.getRepeatPassword())) {

			selectedUser.setPassword(res.getPassword());

			UserDAOImpl.getInstance().update(selectedUser);
			EmailConfirmation eC = new EmailConfirmation();
			eC.setUserId(userId);
			eC.setToken(res.getToken());
			EmailConfirmationDaoImpl.getInstance().delete(eC);
		}
		return gson.toJson(res);
	}

	private String createToken() {
		return UUID.randomUUID().toString();
	}
}
