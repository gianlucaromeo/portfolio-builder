package it.unical.demacs.informatica.digitales.app.dashboard;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;
import it.unical.demacs.informatica.digitales.app.validator.SignUpFormValidator;

@RestController
public class SignUpREST {
	

	@PostMapping("/sign_up_data_validation")
	public String signUpAction(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {
		
		Gson gson = new Gson();
		User user = new User();
		user = gson.fromJson(req.getReader(), User.class);
	
		String userDataResponseToJSON = gson.toJson(SignUpFormValidator.validateUser(user));
		
		return userDataResponseToJSON;
	
	}
	
	@PostMapping("/dashboard/sign_up")
	public void signUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User user = new User();
		fetchUserData(user, req);
		
		String res = UserDAOImpl.getInstance().create(user);
		
		if (res.equals(Protocol.OK)) {
			
			user.setId(UserDAOImpl.getInstance().findId(user));
			
			Cookie cookie = new Cookie("logged_username", user.getUsername());
		    cookie.setMaxAge(60 * 60 * 24);
		    cookie.setPath("/");
		    resp.addCookie(cookie);
			resp.setStatus(HttpServletResponse.SC_TEMPORARY_REDIRECT);
			resp.setHeader("Location", "/dashboard/login");
			
		} else {
			System.out.println(res);
		}
		
	}

	private void fetchUserData(User user, HttpServletRequest req) {
		user.setFirstName(req.getParameter("first_name"));
		user.setLastName(req.getParameter("last_name"));
		user.setUsername(req.getParameter("username"));
		user.setEmail(req.getParameter("email"));
		user.setPassword(req.getParameter("password"));
		user.setDateOfBirth(req.getParameter("date_of_birth"));
		user.setSignUpDate(DateTime.now().toString("yyyy-MM-dd"));
	}
	
}
