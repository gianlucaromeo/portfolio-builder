package it.unical.demacs.informatica.digitales.app.dashboard;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unical.demacs.informatica.digitales.app.validator.LoginValidator;

@RestController
public class LoginREST {


	@PostMapping("/login_data_validation")
	public String loginAction(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {
		
		Gson gson = new Gson();
		UserAuthentication userAuth = new UserAuthentication();
		userAuth = gson.fromJson(req.getReader(), UserAuthentication.class);
		
		String userAuthToJSON = gson.toJson(LoginValidator.validateUserAuth(userAuth));
		
		return userAuthToJSON;
	}
	
	@PostMapping("/dashboard/login")
	public void login() {
		
		UserAuthentication userAuth = new UserAuthentication();
		
	}
	
}
