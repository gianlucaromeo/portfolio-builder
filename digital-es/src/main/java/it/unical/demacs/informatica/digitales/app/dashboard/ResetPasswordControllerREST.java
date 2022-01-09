package it.unical.demacs.informatica.digitales.app.dashboard;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unical.demacs.informatica.digitales.app.beans.validation.ResetPasswordValidatorResponse;

@RestController
public class ResetPasswordControllerREST {
	
	@PostMapping("get_token")
	public String getToken(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {
		Gson gson = new Gson();
		ResetPasswordValidatorResponse res = new ResetPasswordValidatorResponse();
		res = gson.fromJson(req.getReader(),ResetPasswordValidatorResponse.class);

		res.setToken(BCrypt.hashpw(res.getToken(), BCrypt.gensalt(12)));
		res.setToken(res.getToken().replace("/", ""));

		return gson.toJson(res);
	}

}
