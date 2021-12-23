package it.unical.demacs.informatica.digitales.app.dashboard;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
		System.out.println(userDataResponseToJSON);
		return userDataResponseToJSON;
	
	}
	
	@PostMapping("/dashboard/sign_up")
	public void signUp(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		User user = new User();
		user.setFirstName(req.getParameter("first_name"));
		user.setLastName(req.getParameter("last_name"));
		user.setUsername(req.getParameter("username"));
		user.setEmail(req.getParameter("email"));
		user.setPassword(req.getParameter("password"));
		user.setDateOfBirth(req.getParameter("date_of_birth"));
		user.setSignUpDate(DateTime.now().toString("yyyy-MM-dd"));
		
		String res = UserDAOImpl.getInstance().create(user);
		if (res.equals(Protocol.OK)) {
			user.setId(UserDAOImpl.getInstance().findId(user));
			RequestDispatcher dispatcher = req.getRequestDispatcher("/dashboard/" + user.getUsername() + "/admin-page");
			dispatcher.forward(req, resp);
		} else {
			System.out.println(res);
		}
		
	}
	
}
