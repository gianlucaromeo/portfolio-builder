package it.unical.demacs.informatica.digitales.app.dashboard;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.beans.validation.ResetPasswordValidatorResponse;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.validator.ResetPasswordValidator;

@RestController
public class ResetPasswordControllerREST {
	
	@PostMapping("get_token")
	public String getToken(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {
		Gson gson = new Gson();
		ResetPasswordValidatorResponse res = new ResetPasswordValidatorResponse();
		res = gson.fromJson(req.getReader(),ResetPasswordValidatorResponse.class);

		res.setToken(BCrypt.hashpw(res.getToken(), BCrypt.gensalt(12)));
		res.setToken(res.getToken().replace("/", "ç"));

		return gson.toJson(res);
	}
	
	@PostMapping("reset_password")
	public String resetPassword(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {
		Gson gson = new Gson();
		ResetPasswordValidatorResponse res = new ResetPasswordValidatorResponse();
		res = gson.fromJson(req.getReader(),ResetPasswordValidatorResponse.class);
		
		ResetPasswordValidator.validatePassword(res);
		
		User selectedUser=UserDAOImpl.getInstance().findByUsername(res.getToken());
		System.out.println(selectedUser);
		if(selectedUser!=null&&res.getPassword().equals(res.getRepeatPassword())) {
			selectedUser.setPassword(res.getPassword());
			System.out.println("PASSWORD UPDATED:"+res.getPassword());
			UserDAOImpl.getInstance().update(selectedUser);
		}
		return gson.toJson(res);
	}

}
