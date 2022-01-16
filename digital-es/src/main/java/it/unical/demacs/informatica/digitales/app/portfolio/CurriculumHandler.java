package it.unical.demacs.informatica.digitales.app.portfolio;

import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import it.unical.demacs.informatica.digitales.app.beans.CurriculumExperience;
import it.unical.demacs.informatica.digitales.app.beans.CurriculumSkill;
import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.beans.UserMainInformations;
import it.unical.demacs.informatica.digitales.app.dao.CurriculumExperienceDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.CurriculumSkillDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserMainInformationsDAOImpl;

public class CurriculumHandler {

	public static String initCurriculumPage(HttpServletRequest req, String username) {

		if (!UserDAOImpl.getInstance().checkUsernameExists(username)) {
			return "404_page";
		}

		User user = UserDAOImpl.getInstance().findByUsername(username);
		UserMainInformations userInfo = UserMainInformationsDAOImpl.getInstance().findById(user.getId());

		req.setAttribute("firstName", user.getFirstName());
		req.setAttribute("lastName", user.getLastName());
		req.setAttribute("username", username);

		req.setAttribute("profilePicture",
				UserMainInformationsDAOImpl.getInstance().findProfileImageById(user.getId()));

		req.setAttribute("biography", userInfo.getBio());

		Set<CurriculumExperience> experiences = CurriculumExperienceDAOImpl.getInstance().findAllByUserId(user.getId());
		experiences.forEach((exp) -> {
			if (exp.getEndDate() == null || exp.getEndDate().equals("")) {
				exp.setEndDate("To Present");
			}
		});
		req.setAttribute("experiences", experiences);

		Set<CurriculumSkill> skills = CurriculumSkillDAOImpl.getInstance().findAllByUserId(user.getId());
		req.setAttribute("skills", skills);

		req.setAttribute("dateOfBirth", user.getDateOfBirth());
		req.setAttribute("phoneNumber", user.getMainPhoneNumber());
		req.setAttribute("secondaryPhoneNumber", user.getSecondaryPhoneNumber());
		req.setAttribute("email", user.getEmail());
		req.setAttribute("contactEmail", user.getContactEmail());

		// link
		req.setAttribute("facebookLink", userInfo.getFacebookLinkRef());
		req.setAttribute("instagramLink", userInfo.getInstagramLinkRef());
		req.setAttribute("twitterLink", userInfo.getTwitterLinkRef());

		// foto profilo
		req.setAttribute("profilePicture",
				UserMainInformationsDAOImpl.getInstance().findProfileImageById(user.getId()));

		return "portfolio_cv";

	}

}
