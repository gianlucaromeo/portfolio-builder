package it.unical.demacs.informatica.digitales.app.validator;

import it.unical.demacs.informatica.digitales.app.beans.UserAuthentication;
import it.unical.demacs.informatica.digitales.app.beans.validation.LoginValidatorResponse;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class LoginValidator {

	public static LoginValidatorResponse validateUserAuth(UserAuthentication userAuth) {
		
		LoginValidatorResponse resp = new LoginValidatorResponse();
		
		String username = userAuth.getUsername();
		resp.setUsernameResp(checkUsername(userAuth.getUsername()));
		
		if (resp.getUsernameResp().equals(Protocol.OK)) {
			resp.setPasswordResp(checkPassword(username, userAuth.getPassword()));
		} else {
			resp.setPasswordResp(Protocol.LOGIN_ERROR);
		}
		
		return resp;
		
	}

	private static String checkUsername(String username) {
		if (username == null) {
			return Protocol.LOGIN_ERROR;
		}
		return UserDAOImpl.getInstance().checkUsernameExists(username) ? Protocol.OK : Protocol.USERNAME_NOT_VALID_ERROR;
	}

	private static String checkPassword(String username, String password) {
		boolean userExists =  UserDAOImpl.getInstance().checkUsernameAndPassword(username, password);
		return userExists ? Protocol.OK : Protocol.LOGIN_PASSWORD_ERROR;
	}
	
}