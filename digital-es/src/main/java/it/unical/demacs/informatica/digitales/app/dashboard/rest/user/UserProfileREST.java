package it.unical.demacs.informatica.digitales.app.dashboard.rest.user;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unical.demacs.informatica.digitales.app.beans.CurriculumSkill;
import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.beans.UserMainInformations;
import it.unical.demacs.informatica.digitales.app.beans.validation.ProfileValidatorResponse;
import it.unical.demacs.informatica.digitales.app.dao.CurriculumSkillDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserMainInformationsDAOImpl;
import it.unical.demacs.informatica.digitales.app.dashboard.AppServletsHandler;
import it.unical.demacs.informatica.digitales.app.validator.ProfileValidator;

@RestController
public class UserProfileREST {

	@PostMapping("/get_profile_picture")
	public synchronized String getProfilePicture(HttpServletRequest req)
			throws JsonSyntaxException, JsonIOException, IOException {

		User user = AppServletsHandler.getLoggedUser(req);

		if (user == null) {
			return AppServletsHandler.redirect(req, "error_page");
		}

		long userId = user.getId();
		String profileImage = UserMainInformationsDAOImpl.getInstance().findProfileImageById(userId);

		return profileImage;

	}

	@PostMapping("/save_presentation_image")
	public synchronized String savePresentation(HttpServletRequest req)
			throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();
		UserMainInformations newMainInfo = gson.fromJson(req.getReader(), UserMainInformations.class);

		User user = AppServletsHandler.getLoggedUser(req);
		UserMainInformations info = UserMainInformationsDAOImpl.getInstance().findById(user.getId());
;
		info.setPresentationPicture1(newMainInfo.getPresentationPicture1());
		info.setPresentationPicture2(newMainInfo.getPresentationPicture2());
		info.setPresentationPicture3(newMainInfo.getPresentationPicture3());

		UserMainInformationsDAOImpl.getInstance().update(info);

		return "";

	}

	@PostMapping("/get_main_info")
	public synchronized String getMainInfo(HttpServletRequest req)
			throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();

		User user = AppServletsHandler.getLoggedUser(req);
		UserMainInformations info = UserMainInformationsDAOImpl.getInstance().findById(user.getId());

		return gson.toJson(info);

	}

	@PostMapping("/get_main_info_user")
	public synchronized String getMainInfoUser(HttpServletRequest req)
			throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();

		User user = AppServletsHandler.getLoggedUser(req);

		return gson.toJson(user);

	}

	@PostMapping("/save_bio")
	public synchronized String saveBio(HttpServletRequest req)
			throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();
		UserMainInformations newMainInfo = gson.fromJson(req.getReader(), UserMainInformations.class);

		User user = AppServletsHandler.getLoggedUser(req);
		UserMainInformations info = UserMainInformationsDAOImpl.getInstance().findById(user.getId());

		info.setBio(newMainInfo.getBio());
		info.setProfilePicture(newMainInfo.getProfilePicture());

		UserMainInformationsDAOImpl.getInstance().update(info);

		return gson.toJson(info);

	}

	@PostMapping("/save_main_info")
	public synchronized String saveMainInfo(HttpServletRequest req, HttpServletResponse resp)
			throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();
		User newMainInfo = gson.fromJson(req.getReader(), User.class);
		System.out.println(newMainInfo);

		User user = AppServletsHandler.getLoggedUser(req);

		ProfileValidatorResponse validation = ProfileValidator.validateMainInfo(newMainInfo, user);
		if (ProfileValidator.isValidMainInfo(validation)) {
			user.setUsername(newMainInfo.getUsername());
			user.setFirstName(newMainInfo.getFirstName());
			user.setLastName(newMainInfo.getLastName());
			user.setEmail(newMainInfo.getEmail());
			user.setDateOfBirth(newMainInfo.getDateOfBirth());
			UserDAOImpl.getInstance().update(user);
//		TODO CHANGE COOKIE
			Cookie cookie = AppServletsHandler.initLoggedUsernameCookie(req, resp, user.getUsername());
			resp.addCookie(cookie);

		}

		return gson.toJson(validation);
	}

	@PostMapping("/save_contacts1")
	public synchronized String saveContacts1(HttpServletRequest req)
			throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();
		User newUser = gson.fromJson(req.getReader(), User.class);
		System.out.println("NEW DATA");
		System.out.println(newUser);

		User user = AppServletsHandler.getLoggedUser(req);
		System.out.println("CURRENT");
		System.out.println(user);

		ProfileValidatorResponse validation = ProfileValidator.validateContacts1(newUser);
		if (ProfileValidator.isValidContacts1(validation)) {
			user.setContactEmail(newUser.getContactEmail());
			user.setMainPhoneNumber(newUser.getMainPhoneNumber());
			user.setSecondaryPhoneNumber(newUser.getSecondaryPhoneNumber());

			UserDAOImpl.getInstance().update(user);
		}
		return gson.toJson(validation);
	}

	@PostMapping("/save_contacts2")
	public synchronized String saveContacts2(HttpServletRequest req)
			throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();

		User user = AppServletsHandler.getLoggedUser(req);

		UserMainInformations newMainInfo = gson.fromJson(req.getReader(), UserMainInformations.class);
//		System.out.println(newMainInfo);
		UserMainInformations info = UserMainInformationsDAOImpl.getInstance().findById(user.getId());

		ProfileValidatorResponse validation = ProfileValidator.validateContacts2(newMainInfo);
		if (ProfileValidator.isValidContacts2(validation)) {
			info.setFacebookLinkRef(newMainInfo.getFacebookLinkRef());
			info.setTwitterLinkRef(newMainInfo.getTwitterLinkRef());
			info.setInstagramLinkRef(newMainInfo.getInstagramLinkRef());

			UserMainInformationsDAOImpl.getInstance().update(info);
		}
		return gson.toJson(validation);
	}

	@PostMapping("/save_main_skills")
	public synchronized String saveMainSkill(HttpServletRequest req)
			throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();

		User user = AppServletsHandler.getLoggedUser(req);

		UserMainInformations newMainInfo = gson.fromJson(req.getReader(), UserMainInformations.class);
		System.out.println(newMainInfo);
		UserMainInformations info = UserMainInformationsDAOImpl.getInstance().findById(user.getId());

		info.setSpecialSkillName1(newMainInfo.getSpecialSkillName1());
		info.setSpecialSkillDescr1(newMainInfo.getSpecialSkillDescr1());
		info.setSpecialSkillName2(newMainInfo.getSpecialSkillName2());
		info.setSpecialSkillDescr2(newMainInfo.getSpecialSkillDescr2());
		info.setSpecialSkillName3(newMainInfo.getSpecialSkillName3());
		info.setSpecialSkillDescr3(newMainInfo.getSpecialSkillDescr3());

		UserMainInformationsDAOImpl.getInstance().update(info);

		return gson.toJson(info);
	}

	@PostMapping("/load_skills")
	public synchronized String loadSkills(HttpServletRequest req)
			throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();

		User user = AppServletsHandler.getLoggedUser(req);
		System.out.println("USER WHEN LOADING SKILL");
		System.out.println(user);
		Set<CurriculumSkill> skills = CurriculumSkillDAOImpl.getInstance().findAllByUserId(user.getId());
		String skillsToJSON = gson.toJson(skills);
		return skillsToJSON;
	}

	@PostMapping("/add_skill")
	public synchronized String addSkill(HttpServletRequest req)
			throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();

		User user = AppServletsHandler.getLoggedUser(req);

		CurriculumSkill newSkill = gson.fromJson(req.getReader(), CurriculumSkill.class);
		System.out.println(newSkill);
		ProfileValidatorResponse resp = ProfileValidator.validateSkill(newSkill);

		if (ProfileValidator.isValidSkill(resp)) {
			newSkill.setUserId(user.getId());
			CurriculumSkillDAOImpl.getInstance().create(newSkill);
			resp.setId(CurriculumSkillDAOImpl.getInstance().findId(newSkill));
		}

		return gson.toJson(resp);
	}

	@PostMapping("/edit_skill")
	public synchronized String editSkill(HttpServletRequest req)
			throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();

		User user = AppServletsHandler.getLoggedUser(req);

		CurriculumSkill newSkill = gson.fromJson(req.getReader(), CurriculumSkill.class);
		System.out.println(newSkill);
		ProfileValidatorResponse resp = ProfileValidator.validateSkill(newSkill);
		resp.setId(newSkill.getId());

		if (ProfileValidator.isValidSkill(resp)) {
			newSkill.setUserId(user.getId());
			CurriculumSkillDAOImpl.getInstance().update(newSkill);
		}
		return gson.toJson(resp);
	}

	@PostMapping("/remove_skill")
	public synchronized void removeSkill(HttpServletRequest req)
			throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();
		Integer id = gson.fromJson(req.getReader(), Integer.class);
		CurriculumSkill skill = new CurriculumSkill();
		skill.setId(id);
		CurriculumSkillDAOImpl.getInstance().delete(skill);
	}
}
