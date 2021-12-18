package it.unical.demacs.informatica.digitales.app.validator;

import java.util.ArrayList;
import java.util.List;

import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.beans.validation.SignUpFormValidatorResponse;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class SignUpFormValidator {

	public static SignUpFormValidatorResponse validateUser(User user) {
		SignUpFormValidatorResponse resp = new SignUpFormValidatorResponse();
		resp.setFirstNameResp(checkName(user.getFirstName()));
		resp.setLastNameResp(checkLastName(user.getLastName()));
		resp.setUsernameResp(checkUsername(user.getUsername()));
		resp.setPasswordResp(checkPassword(user.getPassword()));
		resp.setDateOfBirthResp(checkDateOfBirth(user.getDateOfBirth()));
		resp.setEmailResp(checkEmail(user.getEmail()));
		
		
		return resp;
	}
	
	private static String checkName(String name) {
		return Protocol.OK;
	}
	private static String checkLastName(String lastName) {
		return Protocol.ERROR;
	}
	private static String checkDateOfBirth(String dateOfBirth) {
		return Protocol.OK;
	}
	private static String checkEmail(String email) {
		return Protocol.OK;
	}
	private static String checkUsername(String username) {
		return Protocol.OK;
	}
	private static String checkPassword(String password) {
		return Protocol.ERROR;
	}
	
	
	
}
