package it.unical.demacs.informatica.digitales.app.validator;

import java.util.Set;
import java.util.regex.Pattern;

import org.springframework.security.crypto.bcrypt.BCrypt;

import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.beans.validation.ResetPasswordValidatorResponse;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class ResetPasswordValidator {
	
	private static final String PASSWORD_RGX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";
	private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_RGX);
	
	public ResetPasswordValidator() {
		// TODO Auto-generated constructor stub
	}
	
	public static ResetPasswordValidatorResponse validatePassword(ResetPasswordValidatorResponse resp) {

		resp.setToken(decodeToken(resp.getToken()));
		resp.setPassword(checkPassword(resp.getPassword()));
		return resp;	
	}

	public static String checkPassword(String password) {
		return PASSWORD_PATTERN.matcher(password).matches() ? password : Protocol.ERROR;
	}
	
	public static String decodeToken(String token) {
		token=token.replace("ç", "/");
		Set<User> users=UserDAOImpl.getInstance().findAll();
		String selectedUsername="NOT AN USER";
		for(User u:users) {
			if(BCrypt.checkpw(u.getUsername(),token)) {
				selectedUsername=u.getUsername();
				break;
			}	
		}
		return selectedUsername;
	}
	
}
