package it.unical.demacs.informatica.digitales.app.dashboard;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.DBUtil;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;
import it.unical.demacs.informatica.digitales.app.validator.SignUpFormValidator;

public class GoogleSignUpRest {
	@PostMapping("/sign_up_get_next_id_googleRest")
	public String getNextId(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {
		String id= UserDAOImpl.getInstance().getUsersNumber().toString();
		return id;
	
	}
	@PostMapping("/sign_up_verify_username_exists_googleRest")
	public String verifyUsername(HttpServletRequest req, String username) throws JsonSyntaxException, JsonIOException, IOException {
		
		boolean check= UserDAOImpl.getInstance().checkUsernameExists(username);
		if(check) {
			return Protocol.ERROR;
		}else {
			return Protocol.OK;
		}
	
	}

}
