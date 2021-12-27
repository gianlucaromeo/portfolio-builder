package it.unical.demacs.informatica.digitales.app.dashboard;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

@Controller
public class GoogleLoginREST {
	@PostMapping("/google_login")
	public String getNextId(HttpServletRequest req, HttpServletResponse resp, String email) throws JsonSyntaxException, JsonIOException, IOException {
		
		if(UserDAOImpl.getInstance().checkEmailExists(email)) {
			String username= UserDAOImpl.getInstance().getUsernameByEmail(email);		
			Cookie cookie = new Cookie("logged_username", username);
		    cookie.setMaxAge(60 * 60 * 24);
		    resp.addCookie(cookie);
			resp.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
			resp.setHeader("Location", "/dashboard/profile");
			
			return Protocol.OK;
		}else {
			resp.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
			resp.setHeader("Location", "/dashboard/sign_up");
		}
		return Protocol.ERROR;
		
		
	
	}
}
