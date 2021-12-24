package it.unical.demacs.informatica.digitales.app.database.protocol;

public class Protocol {

	public final static String OK = "ok";
	public final static String ERROR = "error"; // Generic
	
	/* SIGN-UP FORM */
	public final static String USERNAME_DUPLICATED_ERROR = "username duplicated error";
	public final static String EMAIL_DUPLICATED_ERROR = "email duplicated error";
	public final static String USERNAME_LENGTH_ERROR = "username length error";
	
	/* LOGIN FORM */
	public final static String LOGIN_ERROR = "login error"; // Generic
	public final static String USERNAME_NOT_VALID_ERROR = "username not valid error";
	public final static String LOGIN_PASSWORD_ERROR = "login password error";
	
	
}
