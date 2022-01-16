package it.unical.demacs.informatica.digitales.app.validator;

import java.util.regex.Pattern;

import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import it.unical.demacs.informatica.digitales.app.beans.CurriculumSkill;
import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.beans.UserMainInformations;
import it.unical.demacs.informatica.digitales.app.beans.validation.ProfileValidatorResponse;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.dashboard.AppServletsHandler;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class ProfileValidator {

	private static final String NAME_RGX = "[A-Z][a-z]*(\s[A-Z][a-z]*)*";
	private static final String USERNAME_RGX = "[a-zA-Z][a-zA-Z0-9]*";
	private static final String PHONE_RGX = "\\d{10}|(?:\\d{3}-){2}\\d{4}|\\(\\d{3}\\)\\d{3}-?\\d{4}"; // 1234567890
																										// 123-456-7890
																										// (123)456-7890
	private static final String LINK_RGX = "^(?:[a-z]*?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$";
	private static final String TITLE_RGX = "^[0-9a-zA-Z\\s]+$";

	private static final Pattern NAME_PATTERN = Pattern.compile(NAME_RGX);
	private static final Pattern USERNAME_PATTERN = Pattern.compile(USERNAME_RGX);
	private static final Pattern PHONE_PATTERN = Pattern.compile(PHONE_RGX);
	private static final Pattern LINK_PATTERN = Pattern.compile(LINK_RGX);
	private static final Pattern TITLE_PATTERN = Pattern.compile(TITLE_RGX);

	public static boolean isValidMainInfo(ProfileValidatorResponse resp) {
		if (resp.getUsername().equals("error"))
			return false;
		if (resp.getFirstName().equals("error"))
			return false;
		if (resp.getLastName().equals("error"))
			return false;
		if (resp.getEmail().equals("error"))
			return false;
		if (resp.getDateOfBirth().equals("error"))
			return false;
		return true;
	}

	public static boolean isValidContacts1(ProfileValidatorResponse resp) {
		if (resp.getContactEmail().equals("error"))
			return false;
		if (resp.getMainPhoneNumber().equals("error"))
			return false;
		if (resp.getSecondaryPhoneNumber().equals("error"))
			return false;
		return true;
	}

	public static boolean isValidContacts2(ProfileValidatorResponse resp) {
		if (resp.getFacebookLinkRef().equals("error"))
			return false;
		if (resp.getTwitterLinkRef().equals("error"))
			return false;
		if (resp.getInstagramLinkRef().equals("error"))
			return false;
		return true;
	}

	public static boolean isValidSkill(ProfileValidatorResponse resp) {
		if (resp.getTitle().equals("error"))
			return false;
		if (resp.getLevel() == -1)
			return false;
		return true;
	}

	public static ProfileValidatorResponse validateMainInfo(User user, User oldUser) {
		ProfileValidatorResponse resp = new ProfileValidatorResponse();
		resp.setFirstName(checkName(user.getFirstName()));
		resp.setLastName(checkName(user.getLastName()));

		if (!user.getUsername().equals(oldUser.getUsername())) {
			resp.setUsername(checkUsername(user.getUsername()));
		} else {
			resp.setUsername(oldUser.getUsername());
		}

		resp.setDateOfBirth(checkDateOfBirth(user.getDateOfBirth()));
		resp.setEmail(oldUser.getEmail());
		return resp;
	}

	public static ProfileValidatorResponse validateContacts1(User user) {
		ProfileValidatorResponse resp = new ProfileValidatorResponse();
		resp.setContactEmail(checkEmail(user.getContactEmail()));
		resp.setMainPhoneNumber(checkPhoneNumber(user.getMainPhoneNumber()));
		resp.setSecondaryPhoneNumber(checkPhoneNumber(user.getSecondaryPhoneNumber()));
		return resp;
	}

	public static ProfileValidatorResponse validateContacts2(UserMainInformations user) {
		ProfileValidatorResponse resp = new ProfileValidatorResponse();
		resp.setFacebookLinkRef(checkLink(user.getFacebookLinkRef()));
		resp.setTwitterLinkRef(checkLink(user.getTwitterLinkRef()));
		resp.setInstagramLinkRef(checkLink(user.getInstagramLinkRef()));
		return resp;
	}

	public static ProfileValidatorResponse validateSkill(CurriculumSkill skill) {
		ProfileValidatorResponse resp = new ProfileValidatorResponse();
		resp.setTitle(checkTitle(skill.getTitle()));
		resp.setLevel(checkLevel(skill.getLevel()));
		return resp;
	}

	public static String checkName(String name) {
		return NAME_PATTERN.matcher(name).matches() ? name : Protocol.ERROR;
	}

	public static String checkTitle(String title) {
		String titleResp = "";
		title = title.trim();
		if (title.equals("") || title == null) {
			titleResp = Protocol.ERROR;
			return titleResp;
		}
		return TITLE_PATTERN.matcher(title).matches() ? title : Protocol.ERROR;
	}

	public static int checkLevel(int level) {
		if (level >= 0 && level <= 100)
			return level;
		return -1;
	}

	public static String checkLink(String link) {
		if (link.equals(""))
			return link;

		return LINK_PATTERN.matcher(link).matches() ? link : Protocol.ERROR;
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
		return email;
	}

	public static String checkPhoneNumber(String phone) {
		if (phone.equals(""))
			return phone;

		return PHONE_PATTERN.matcher(phone).matches() ? phone : Protocol.ERROR;
	}

	public static String checkUsername(String newUsername) {

		if (!USERNAME_PATTERN.matcher(newUsername).matches()) {
			return Protocol.ERROR;
		}

		boolean usernameExists = UserDAOImpl.getInstance().checkUsernameExists(newUsername);
		return usernameExists ? Protocol.ERROR : Protocol.OK;

	}
}
