package it.unical.demacs.informatica.digitales.app.dashboard;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;

@RestController
public class ForgotPasswordREST {
	
	@PostMapping("/get_password_and_email_from_username")
	public String signUpAction(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {
		Gson gson = new Gson();
		
		User user = new User();
		user = gson.fromJson(req.getReader(), User.class);
		
		user=UserDAOImpl.getInstance().findByUsername(user.getUsername());
		
		if(user==null)
			return null;
		String userToJSON = gson.toJson(user);
		return userToJSON;
	}
}
