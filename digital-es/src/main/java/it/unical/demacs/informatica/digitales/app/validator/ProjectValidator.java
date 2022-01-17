package it.unical.demacs.informatica.digitales.app.validator;

import java.util.regex.Pattern;

import it.unical.demacs.informatica.digitales.app.beans.Project;
import it.unical.demacs.informatica.digitales.app.beans.validation.ProjectValidatorResponse;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class ProjectValidator {
	
	private static final String TITLE_RGX = "^[0-9a-zA-Z\\s!\"#$%&'()*+,-./:;<=>?@]+$";
	private static final Pattern TITLE_PATTERN = Pattern.compile(TITLE_RGX);
	
	private static final String LINK_RGX = "^(?:[a-z]*?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})(.)*$";
	private static final Pattern LINK_PATTERN = Pattern.compile(LINK_RGX);
	
	public static ProjectValidatorResponse validateProject(Project project) {

		ProjectValidatorResponse resp = new ProjectValidatorResponse();
		resp.setId(project.getId());
		resp.setDescription(validateDescription(project.getDescription()));
		resp.setPicture(project.getPicture());
		resp.setLinkRef(validateLinkRef(project.getLinkRef()));
		resp.setTitle(validateTitle(project.getTitle()));
		resp.setUserId(project.getUserId());
		
		return resp;

	}
	public static String validateTitle(String title) {

		String titleResp="";
		title=title.trim();
		if(title.equals("") || title==null) {
			titleResp=Protocol.PROJECT_EMPTY_FIELD;
			return titleResp;
		}
		return TITLE_PATTERN.matcher(title).matches() ? title : Protocol.PROJECT_TITLE_NOT_VALID;
	}
	public static String validateDescription(String description) {

		String descriptionResp="";
		description=description.trim();
		if(description.equals("") || description==null) {
			descriptionResp=Protocol.PROJECT_EMPTY_FIELD;
			return descriptionResp;
		}
		return description;
	}
	
	public static String validateLinkRef(String link) {
		if(link.equals("")||link.equals("#")) return "#";
		else
			return LINK_PATTERN.matcher(link).matches() ? link : Protocol.PROJECT_EMPTY_FIELD;
	}
	
	public static boolean isValidProject(ProjectValidatorResponse resp) {
		if(resp.getTitle().equals(Protocol.PROJECT_EMPTY_FIELD) || resp.getTitle().equals(Protocol.PROJECT_TITLE_NOT_VALID)) {
			return false;
		}
		if(resp.getDescription().equals(Protocol.PROJECT_EMPTY_FIELD)) {
			return false;
		}
		if(resp.getLinkRef().equals(Protocol.PROJECT_EMPTY_FIELD)) {
			return false;
		}
		if(resp.getPicture().equals("undefined")) {
			return false;
		}
		
		return true;
		
		
	}

}
