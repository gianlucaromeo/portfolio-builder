package it.unical.demacs.informatica.digitales.app.dashboard;

import java.io.IOException;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.beans.UserMainInformations;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserMainInformationsDAOImpl;
import it.unical.demacs.informatica.digitales.app.settings.ProfileSettings;

@RestController
public class ProfileREST {
	
	@PostMapping("/get_profile_picture")
	public synchronized String getProfilePicture(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {

		User user = Servlets.getLoggedUser(req);
		
		if (user == null) {
			return Servlets.redirect(req, "error_page");
		}

		long userId = user.getId();
		String profileImage =  UserMainInformationsDAOImpl.getInstance().findProfileImageById(userId);
		
		return profileImage;
		
	}
	
	@PostMapping("/save_presentation_image")
	public String savePresentation(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();
		UserMainInformations newMainInfo = gson.fromJson(req.getReader(), UserMainInformations.class);
		System.out.println(newMainInfo);
		
		User user = Servlets.getLoggedUser(req);
		UserMainInformations info=UserMainInformationsDAOImpl.getInstance().findById(user.getId());
		
		info.setProfilePicture(newMainInfo.getProfilePicture());
		info.setPresentationPicture1(newMainInfo.getPresentationPicture1());
		info.setPresentationPicture2(newMainInfo.getPresentationPicture2());
		info.setPresentationPicture3(newMainInfo.getPresentationPicture3());
		
		UserMainInformationsDAOImpl.getInstance().update(info);

		return "";

	}
	
	@PostMapping("/get_main_info")
	public String getMainInfo(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();
		
		User user = Servlets.getLoggedUser(req);
		UserMainInformations info=UserMainInformationsDAOImpl.getInstance().findById(user.getId());
		
		System.out.println(info);
		
		return gson.toJson(info);

	}
	
	@PostMapping("/save_bio")
	public String saveBio(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {

		Gson gson = new Gson();
		UserMainInformations newMainInfo = gson.fromJson(req.getReader(), UserMainInformations.class);
		System.out.println(newMainInfo);
		
		User user = Servlets.getLoggedUser(req);
		UserMainInformations info=UserMainInformationsDAOImpl.getInstance().findById(user.getId());
		
		info.setBio(newMainInfo.getBio());
		
		UserMainInformationsDAOImpl.getInstance().update(info);

		return gson.toJson(info);

	}
}
