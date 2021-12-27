package it.unical.demacs.informatica.digitales.app.dashboard;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.beans.UserAuthentication;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
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
	public void login(HttpServletRequest req, HttpServletResponse resp) {
		
		System.out.println("DO LOGIN");
		UserAuthentication userAuth = new UserAuthentication();
		userAuth.setUsername(req.getParameter("username"));
		
		User user = new User();
		user = UserDAOImpl.getInstance().findByUsername(userAuth.getUsername());
		if (user.getFirstName() != null) {
			user.setId(UserDAOImpl.getInstance().findId(user));
			if (user.getId() != 0) {
				
				Cookie cookie = new Cookie("logged_username", user.getUsername());
			    cookie.setMaxAge(60 * 60 * 24);
			    cookie.setPath("/");
			    
			    resp.addCookie(cookie);
				resp.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
				resp.setHeader("Location", "/dashboard/profile"); 
				
			}
		}
		
	}
	
}
