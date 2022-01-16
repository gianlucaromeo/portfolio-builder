package it.unical.demacs.informatica.digitales.app.validator;

import java.util.regex.Pattern;

import it.unical.demacs.informatica.digitales.app.beans.validation.ResetPasswordValidatorResponse;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class ResetPasswordValidator {
	
	private static final String PASSWORD_RGX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";
	private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_RGX);
	
	public ResetPasswordValidator() {
		// TODO Auto-generated constructor stub
	}
	
	public static ResetPasswordValidatorResponse validatePassword(ResetPasswordValidatorResponse resp) {
		resp.setPassword(checkPassword(resp.getPassword()));
		return resp;	
	}

	public static String checkPassword(String password) {
		return PASSWORD_PATTERN.matcher(password).matches() ? password : Protocol.ERROR;
	}
	
}
