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
public class ForgotPasswordController {
	
	@PostMapping("/get_password_and_email_from_username")
	public String signUpAction(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {
		System.out.println("it arrives");
		Gson gson = new Gson();
		
		User user = new User();
		user = gson.fromJson(req.getReader(), User.class);
	
		//TODO
		//user=UserDAOImpl.getInstance().findByUsername(user.getUsername());
		
		//TEST because i don't have a database 
		user.setEmail("pieroangela2000luigim@gmail.com");
		user.setPassword("dddddddds0sdos0d");
		
		String userToJSON = gson.toJson(user);
		return userToJSON;
	
	}
}
