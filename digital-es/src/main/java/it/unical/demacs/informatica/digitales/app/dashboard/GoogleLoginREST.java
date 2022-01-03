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

import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

@RestController
public class GoogleLoginREST {

	@PostMapping("/google_login")
	public synchronized String loginGoogle(HttpServletRequest req, HttpServletResponse resp)
			throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();
		String email = new String();
		email = gson.fromJson(req.getReader(), String.class);
		//System.out.println(email);
		if (UserDAOImpl.getInstance().checkEmailExists(email)) {
			
			System.out.println("email exists");
			
			String username = UserDAOImpl.getInstance().getUsernameByEmail(email);
			
			Cookie cookie = new Cookie("logged_username", username);
			cookie.setMaxAge(60 * 60 * 24);
			cookie.setPath("/");

			resp.addCookie(cookie);
//			resp.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
//			resp.setHeader("Location", "/dashboard/profile"); 

			return gson.toJson(Protocol.OK);
		}
			
		return gson.toJson(Protocol.ERROR);

	}
}
