package it.unical.demacs.informatica.digitales.app.validator;

import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.beans.validation.ProfileValidatorResponse;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class ProfileValidator {
	
	private static final String NAME_RGX = "[A-Z][a-z]*(\s[A-Z][a-z]*)*";
	private static final String USERNAME_RGX = "[a-zA-Z][a-zA-Z0-9]*";
	
	private static final Pattern NAME_PATTERN = Pattern.compile(NAME_RGX);
	private static final Pattern USERNAME_PATTERN = Pattern.compile(USERNAME_RGX);
	
public static boolean isValidMainInfo(ProfileValidatorResponse resp) {
	if(resp.getUsername().equals("error"))
		return false;
	if(resp.getFirstName().equals("error"))
		return false;
	if(resp.getLastName().equals("error"))
		return false;
	if(resp.getEmail().equals("error"))
		return false;
	if(resp.getDateOfBirth().equals("error"))
		return false;
	return true;
}
	
public static ProfileValidatorResponse validateMainInfo(User user) {
		ProfileValidatorResponse resp = new ProfileValidatorResponse();
		resp.setFirstName(checkName(user.getFirstName()));
		resp.setLastName(checkName(user.getLastName()));
		resp.setUsername(checkUsername(user.getUsername()));
		resp.setDateOfBirth(checkDateOfBirth(user.getDateOfBirth()));
		resp.setEmail(checkEmail(user.getEmail()));
		return resp;
	}

public static String checkName(String name) {
	return NAME_PATTERN.matcher(name).matches() ? name : Protocol.ERROR;
}

public static String checkDateOfBirth(String dateOfBirth) {
	
	DateTimeFormatter format = DateTimeFormat.forPattern("yyyy-MM-dd");
	DateTime dtDateOfBirth = format.parseDateTime(dateOfBirth);
	
	int daysDiff = Days.daysBetween(dtDateOfBirth.toInstant(), DateTime.now().toInstant()).getDays();
	
	if (daysDiff < 18 * 365) {
		return Protocol.ERROR;
	}
	
	return dateOfBirth;
}

public static String checkEmail(String email) {
//	boolean emailExists = UserDAOImpl.getInstance().checkEmailExists(email);
//	return emailExists ? Protocol.EMAIL_DUPLICATED_ERROR : Protocol.OK;
	return email;
}

public static String checkUsername(String username) {
	
	if (!USERNAME_PATTERN.matcher(username).matches()) {
		return Protocol.ERROR;
	}
	
	//boolean usernameExists = UserDAOImpl.getInstance().checkUsernameExists(username);
	//return usernameExists ? Protocol.USERNAME_DUPLICATED_ERROR : Protocol.OK;
	return username;
	
}
}
