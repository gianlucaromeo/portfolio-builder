package it.unical.demacs.informatica.digitales.app.validator;

import java.util.regex.Pattern;

import it.unical.demacs.informatica.digitales.app.beans.CurriculumExperience;
import it.unical.demacs.informatica.digitales.app.beans.validation.CurriculumExperienceValidatorResponse;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;
import it.unical.demacs.informatica.digitales.app.settings.CurriculumSettings;

public class CurriculumExperienceValidator {

	private static final String TITLE_RGX = "^[0-9a-zA-Z\\s]+$";
	private static final String PLACE_RGX = "^[0-9a-zA-Z\\s]+$";
	
	private static final Pattern TITLE_PATTERN = Pattern.compile(TITLE_RGX);
	private static final Pattern PLACE_PATTERN = Pattern.compile(PLACE_RGX);
	
	public static CurriculumExperienceValidatorResponse validateCurriculumExperience(CurriculumExperience experience) {
		
		CurriculumExperienceValidatorResponse resp = new CurriculumExperienceValidatorResponse();
		
		resp.setId(experience.getId());
		resp.setDescription(checkDescription(experience.getDescription()));
		resp.setStartDate(checkStartDate(experience.getStartDate()));
		resp.setEndDate(checkEndDate(experience.getEndDate()));
		resp.setTitle(checkTitle(experience.getTitle()));
		resp.setDescription(checkDescription(experience.getDescription()));
		resp.setPlace(checkPlace(experience.getPlace()));
		resp.setType(checkType(experience.getType()));
		
		return resp;
		
	}

	private static String checkType(String type) {
		
		if (!type.equals(CurriculumSettings.EDUCATION_EXPERIENCE) && !type.equals(CurriculumSettings.WORK_EXPERIENCE)) {
			return Protocol.ERROR;
		}
		
		return Protocol.OK;
	}

	private static String checkPlace(String place) {
		return PLACE_PATTERN.matcher(place).matches() ? Protocol.OK : Protocol.ERROR;
	}

	private static String checkTitle(String title) {
		return TITLE_PATTERN.matcher(title).matches() ? Protocol.OK : Protocol.ERROR;
	}

	private static String checkStartDate(String startDate) {
		// TODO Auto-generated method stub
		return Protocol.OK;
	}

	private static String checkEndDate(String endDate) {
		// TODO Auto-generated method stub
		return Protocol.OK;
	}

	private static String checkDescription(String description) {
		// TODO Auto-generated method stub
		return Protocol.OK;
	}
	
	public static boolean isValid(CurriculumExperienceValidatorResponse resp) {
		return resp.getTitle().equals(Protocol.OK) &&
				resp.getDescription().equals(Protocol.OK) &&
				resp.getStartDate().equals(Protocol.OK) &&
				resp.getEndDate().equals(Protocol.OK) &&
				resp.getPlace().equals(Protocol.OK) &&
				resp.getType().equals(Protocol.OK);				
	}
	
}
