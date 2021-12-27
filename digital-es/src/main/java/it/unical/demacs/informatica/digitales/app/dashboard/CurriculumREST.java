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
				System.out.println(curriculumExperiencesToJSON);
				
				return curriculumExperiencesToJSON;
				
			}
		}
		
		return Protocol.NO_USER_EXPERIENCES;
		
	}
	
}
