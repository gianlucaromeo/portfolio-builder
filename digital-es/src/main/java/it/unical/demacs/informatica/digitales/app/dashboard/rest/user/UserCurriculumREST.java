package it.unical.demacs.informatica.digitales.app.dashboard.rest.user;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unical.demacs.informatica.digitales.app.beans.CurriculumExperience;
import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.beans.validation.CurriculumExperienceValidatorResponse;
import it.unical.demacs.informatica.digitales.app.dao.CurriculumExperienceDAOImpl;
import it.unical.demacs.informatica.digitales.app.dashboard.AppServletsHandler;

import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;
import it.unical.demacs.informatica.digitales.app.validator.CurriculumExperienceValidator;

@RestController
public class UserCurriculumREST {

	@PostMapping("/get_curriculum_data_action")
	public synchronized String getCurriculumDataAction(HttpServletRequest req, HttpServletResponse resp) {

		Gson gson = new Gson();

		User user = AppServletsHandler.getLoggedUser(req);

		if (user == null) {
			return Protocol.ERROR;
		}

		Set<CurriculumExperience> curriculumExperiences = CurriculumExperienceDAOImpl.getInstance()
				.findAllByUserId(user.getId());
		String curriculumExperiencesToJSON = gson.toJson(curriculumExperiences);
		return curriculumExperiencesToJSON;

	}

	@PostMapping("/get_user_id_action")
	public synchronized String getUserIdAction(HttpServletRequest req, HttpServletResponse resp) {
		Gson gson = new Gson();
		User user = AppServletsHandler.getLoggedUser(req);
		long userId = -1;
		if (user != null) {
			userId = user.getId();
		}
		return gson.toJson(userId);
	}

	@PostMapping("/update_experience_action")
	public String updateExperienceAction(HttpServletRequest req)
			throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();
		CurriculumExperience curriculumExperience = null;

		curriculumExperience = gson.fromJson(req.getReader(), CurriculumExperience.class);

		CurriculumExperienceValidatorResponse resp = CurriculumExperienceValidator
				.validateCurriculumExperience(curriculumExperience);
		resp.setId(curriculumExperience.getId());
		if (resp.isValid()) {
			CurriculumExperienceDAOImpl.getInstance().update(curriculumExperience);
			curriculumExperience.setId(CurriculumExperienceDAOImpl.getInstance().findId(curriculumExperience));
		} else {
			return gson.toJson(resp);
		}

		return gson.toJson(curriculumExperience);

	}

	@PostMapping("/create_experience_action")
	public String createExperienceAction(HttpServletRequest req)
			throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();

		CurriculumExperience curriculumExperience = gson.fromJson(req.getReader(), CurriculumExperience.class);

		CurriculumExperienceValidatorResponse resp = CurriculumExperienceValidator
				.validateCurriculumExperience(curriculumExperience);
		if (resp.isValid()) {
			if (CurriculumExperienceDAOImpl.getInstance().findId(curriculumExperience) != -1) {
				return Protocol.EXPERIENCE_EXISTS;
			} else {
				CurriculumExperienceDAOImpl.getInstance().create(curriculumExperience);
				curriculumExperience.setId(CurriculumExperienceDAOImpl.getInstance().findId(curriculumExperience));
				return gson.toJson(curriculumExperience);
			}
		}

		return gson.toJson(resp);

	}

	@PostMapping("/delete_experience_action")
	public String deleteExperienceAction(HttpServletRequest req, HttpServletResponse resp)
			throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();
		Long id = gson.fromJson(req.getReader(), Long.class);

		CurriculumExperience experience = CurriculumExperienceDAOImpl.getInstance().findById(id);
		String res = CurriculumExperienceDAOImpl.getInstance().delete(experience);

		return gson.toJson(res);

	}

}
