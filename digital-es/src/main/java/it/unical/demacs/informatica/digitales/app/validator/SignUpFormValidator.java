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
		return resp;
	}
	
	private static String checkName(String name) {
		return Protocol.OK;
	}
	
	
	
}
