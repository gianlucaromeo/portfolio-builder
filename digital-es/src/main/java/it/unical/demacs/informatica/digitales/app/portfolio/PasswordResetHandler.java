package it.unical.demacs.informatica.digitales.app.portfolio;

import javax.servlet.http.HttpServletRequest;

import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.dao.EmailConfirmationDaoImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.dashboard.Servlets;

public class PasswordResetHandler {

	public static String initResetPasswordPage(HttpServletRequest req, String username) {
		
		req.setAttribute("token", username);
		
		long userId = EmailConfirmationDaoImpl.getInstance().findUserId(username);
		User user = UserDAOImpl.getInstance().findById(userId);
		System.out.println("user id " + userId);
		System.out.println("User. " + user);
		
		if (user == null) {
			//Servlets.redirect(req, "404_page");
		}
		
		return "reset_password";
	}
}
