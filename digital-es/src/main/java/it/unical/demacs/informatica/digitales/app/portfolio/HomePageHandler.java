package it.unical.demacs.informatica.digitales.app.portfolio;

import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.beans.UserMainInformations;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserMainInformationsDAOImpl;

public class HomePageHandler {

	public static String initHomePage(HttpServletRequest req, String username) {

		if (!UserDAOImpl.getInstance().checkUsernameExists(username)) {
			return "404_page";
		}

		User user = UserDAOImpl.getInstance().findByUsername(username);
		UserMainInformations userInfo = UserMainInformationsDAOImpl.getInstance().findById(user.getId());

		// Nome e Cognome e username
		req.setAttribute("firstName", user.getFirstName());
		req.setAttribute("lastName", user.getLastName());
		req.setAttribute("username", username);

		// foto profilo
		req.setAttribute("profilePicture",
				UserMainInformationsDAOImpl.getInstance().findProfileImageById(user.getId()));

		// bio
		req.setAttribute("biography", userInfo.getBio());

		// foto presentazione
		List<String> presentationPictures = initPresentationPictures(userInfo);
		req.setAttribute("atLeastOnePresentationPicture", false);
		presentationPictures.forEach((p) -> {
			if (p != null && !p.equals("undefined")) {
				req.setAttribute("atLeastOnePresentationPicture", true);
			}
		});
		req.setAttribute("presentationPictures", presentationPictures);

		// 3 skills
		req.setAttribute("skillName1", userInfo.getSpecialSkillName1());
		req.setAttribute("skillName2", userInfo.getSpecialSkillName2());
		req.setAttribute("skillName3", userInfo.getSpecialSkillName3());
		req.setAttribute("skillDescr1", userInfo.getSpecialSkillDescr1());
		req.setAttribute("skillDescr2", userInfo.getSpecialSkillDescr2());
		req.setAttribute("skillDescr3", userInfo.getSpecialSkillDescr3());

		// link
		req.setAttribute("facebookLink", userInfo.getFacebookLinkRef());
		req.setAttribute("instagramLink", userInfo.getInstagramLinkRef());
		req.setAttribute("twitterLink", userInfo.getTwitterLinkRef());

		// foto profilo
		req.setAttribute("profilePicture",
				UserMainInformationsDAOImpl.getInstance().findProfileImageById(user.getId()));

		return "portfolio_homepage";

	}

	private static List<String> initPresentationPictures(UserMainInformations userInfo) {
		String p1 = userInfo.getPresentationPicture1();
		String p2 = userInfo.getPresentationPicture2();
		String p3 = userInfo.getPresentationPicture3();
		return Arrays.asList(p1, p2, p3);
	}

}
