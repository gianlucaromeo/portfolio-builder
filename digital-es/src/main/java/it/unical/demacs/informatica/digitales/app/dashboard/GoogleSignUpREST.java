package it.unical.demacs.informatica.digitales.app.dashboard;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

@RestController
public class GoogleSignUpREST {
	
	@PostMapping("/sign_up_get_new_google_username")
	public String getNextId(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {
		
		Integer usersCounter =  UserDAOImpl.getInstance().getUsersCounter();
		
	    String username = "googleUser" + usersCounter;
		while (UserDAOImpl.getInstance().checkUsernameExists(username)) {
			System.out.println("Username " + username + " already exists");
			usersCounter++;
			username = "googleUser" + usersCounter;
		}
		
		return new Gson().toJson(username);	
	
	}
	
	@PostMapping("/sign_up_verify_username_exists_googleRest")
	public String verifyUsername(HttpServletRequest req, String username) throws JsonSyntaxException, JsonIOException, IOException {
		boolean usernameExists = UserDAOImpl.getInstance().checkUsernameExists(username);
		return usernameExists ? Protocol.ERROR : Protocol.OK;
	}


}
