package it.unical.demacs.informatica.digitales.app.portfolio;

import javax.servlet.http.HttpServletRequest;

public class PasswordResetHandler {

	public static String initResetPasswordPage(HttpServletRequest req, String username) {
		
		req.setAttribute("token", username);
		
		return "reset_password";
	}
}
