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

	/* CURRICULUM EXPERIENCES */
	public final static String NO_USER_EXPERIENCES = "no curriculum experiences";

	/* POSTS */
	public final static String POST_EMPTY_FIELD = "post_field_empty";
	public final static String POST_TITLE_NOT_CORRECT = "post_title_not_correct";
	public final static String POST_LINK_NO_VALID = "post_link_not_valid";
	
	/*PROJECTS*/
	public final static String PROJECT_EMPTY_FIELD = "project_field_empty";
	public final static String PROJECT_TITLE_NOT_VALID = "project_title_not_valid";
	public final static String PROJECT_LINK_NO_VALID = "project_link_not_valid";
	
}
