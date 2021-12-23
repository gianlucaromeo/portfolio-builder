package it.unical.demacs.informatica.digitales.app.validator;

import java.util.Set;
import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.Duration;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.beans.validation.SignUpFormValidatorResponse;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class SignUpFormValidator {
	
	private static final String NAME_RGX = "[A-Z][a-z]*(\s[A-Z][a-z]*)*";
	private static final String USERNAME_RGX = "[a-zA-Z][a-zA-Z0-9]*";
	private static final String PASSWORD_RGX = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@$!%*?&])[A-Za-z\\d@$!%*?&]{8,20}$";
	
	private static final Pattern NAME_PATTERN = Pattern.compile(NAME_RGX);
	private static final Pattern USERNAME_PATTERN = Pattern.compile(USERNAME_RGX);
	private static final Pattern PASSWORD_PATTERN = Pattern.compile(PASSWORD_RGX);
	
	public static SignUpFormValidatorResponse validateUser(User user) {
		
		SignUpFormValidatorResponse resp = new SignUpFormValidatorResponse();
		
		resp.setFirstNameResp(checkName(user.getFirstName()));
		resp.setLastNameResp(checkName(user.getLastName()));
		resp.setUsernameResp(checkUsername(user.getUsername()));
		resp.setPasswordResp(checkPassword(user.getPassword()));
		resp.setDateOfBirthResp(checkDateOfBirth(user.getDateOfBirth()));
		resp.setEmailResp(checkEmail(user.getEmail()));
		
		return resp;
		
	}
	
	public static String checkName(String name) {
		return NAME_PATTERN.matcher(name).matches() ? Protocol.OK : Protocol.ERROR;
	}

	public static String checkDateOfBirth(String dateOfBirth) {
		
		DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");
		DateTime dtDateOfBirth = format.parseDateTime(dateOfBirth);
		
		int daysDiff = Days.daysBetween(dtDateOfBirth.toInstant(), DateTime.now().toInstant()).getDays();
		
		if (daysDiff < 18 * 365) {
			return Protocol.ERROR;
		}
		
		return Protocol.OK;
	}
	
	public static String checkEmail(String email) {
		boolean emailExists = UserDAOImpl.getInstance().checkEmailExists(email);
		return emailExists ? Protocol.EMAIL_DUPLICATED_ERROR : Protocol.OK;
	}
	
	public static String checkUsername(String username) {
		
		if (!USERNAME_PATTERN.matcher(username).matches()) {
			return Protocol.ERROR;
		}
		
		boolean usernameExists = UserDAOImpl.getInstance().checkUsernameExists(username);
		return usernameExists ? Protocol.USERNAME_DUPLICATED_ERROR : Protocol.OK;
		
	}
	
	public static String checkPassword(String password) {
		return PASSWORD_PATTERN.matcher(password).matches() ? Protocol.OK : Protocol.ERROR;
	}
	
}
