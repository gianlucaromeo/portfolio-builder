package it.unical.demacs.informatica.digitales.app.dashboard;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unical.demacs.informatica.digitales.app.beans.CurriculumExperience;
import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.dao.CurriculumExperienceDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

@RestController
public class CurriculumREST {

	@PostMapping("/get_curriculum_data_action")
	public String getCurriculumDataAction(HttpServletRequest req, HttpServletResponse resp) {
		
		Gson gson = new Gson();
		
		Cookie[] cookies = req.getCookies();
		for (Cookie c : cookies) {

			if (c.getName().equals("logged_username")) {
				
				String username = c.getValue();
				User user = UserDAOImpl.getInstance().findByUsername(username);	
				
				Set<CurriculumExperience> curriculumExperiences =  CurriculumExperienceDAOImpl.getInstance().findAllByUserId(user.getId());
				String curriculumExperiencesToJSON = gson.toJson(curriculumExperiences);
				
				// DEBUG
				//System.out.println(curriculumExperiencesToJSON);
				
				return curriculumExperiencesToJSON;
				
			}
		}
		
		return Protocol.NO_USER_EXPERIENCES;
		
	}
	
	
	@PostMapping("/update_experience_action")
	public String updateExperienceAction(HttpServletRequest req, HttpServletResponse resp) throws JsonSyntaxException, JsonIOException, IOException {
		
		Gson gson = new Gson();
		CurriculumExperience curriculumExperience = null;
		
		curriculumExperience = gson.fromJson(req.getReader(), CurriculumExperience.class);
	
		CurriculumExperienceDAOImpl.getInstance().update(curriculumExperience);
		
		return gson.toJson(curriculumExperience);
		
	}
	
	@PostMapping("/create_experience_action")
	public String createExperienceAction(HttpServletRequest req, HttpServletResponse resp) throws JsonSyntaxException, JsonIOException, IOException {
		
		Gson gson = new Gson();
		CurriculumExperience curriculumExperience = null;
		
		curriculumExperience = gson.fromJson(req.getReader(), CurriculumExperience.class);
	
		CurriculumExperienceDAOImpl.getInstance().create(curriculumExperience);
		
		return gson.toJson(curriculumExperience);
		
	}
	
	@PostMapping("/delete_experience_action")
	public String deleteExperienceAction(HttpServletRequest req, HttpServletResponse resp) throws JsonSyntaxException, JsonIOException, IOException {
		
		Gson gson = new Gson();
		Long id = gson.fromJson(req.getReader(), Long.class);
		
		CurriculumExperience experience = CurriculumExperienceDAOImpl.getInstance().findById(id);
		String res = CurriculumExperienceDAOImpl.getInstance().delete(experience);
		
		return gson.toJson(res);
		
	}
	
}
