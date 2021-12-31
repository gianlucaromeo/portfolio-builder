package it.unical.demacs.informatica.digitales.app.validator;

import java.util.regex.Pattern;

import org.apache.commons.validator.UrlValidator;

import it.unical.demacs.informatica.digitales.app.beans.Post;
import it.unical.demacs.informatica.digitales.app.beans.validation.PostValidatorResponse;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class PostValidator {
	
	private static final String TITLE_RGX = "^[0-9a-zA-Z]+$";
	private static final Pattern TITLE_PATTERN = Pattern.compile(TITLE_RGX);
	
	private static final String LINK_RGX = "^(?:[a-z]*?:\\/\\/)?([\\da-z\\.-]+)\\.([a-z\\.]{2,6})([\\/\\w \\.-]*)*\\/?$";
	private static final Pattern LINK_PATTERN = Pattern.compile(LINK_RGX);
	
	
	public static PostValidatorResponse validatePost(Post post) {

		PostValidatorResponse resp = new PostValidatorResponse();
		resp.setId(post.getId());
		resp.setDescription(validateDescription(post.getDescription()));
		resp.setLastEditDate(post.getLastEditDate());
		resp.setPicture(post.getPicture());
		resp.setPubblicationDate(post.getPubblicationDate());
		resp.setRefLink(validateRefLink(post.getRefLink()));
		resp.setTitle(validateTitle(post.getTitle()));
		resp.setUserId(post.getUserId());
		
		return resp;

	}
	public static String validateTitle(String title) {

		String titleResp="";
		title=title.trim();
		if(title.equals("") || title==null) {
			titleResp=Protocol.POST_EMPTY_FIELD;
			return titleResp;
		}
		return TITLE_PATTERN.matcher(title).matches() ? title : Protocol.POST_TITLE_NOT_CORRECT;
	}
	public static String validateDescription(String description) {

		String descriptionResp="";
		description=description.trim();
		if(description.equals("") || description==null) {
			descriptionResp=Protocol.POST_EMPTY_FIELD;
			return descriptionResp;
		}
		return description;
	}
	
	public static String validateRefLink(String link) {
		if(link.equals("")) return "...";
		else
			return LINK_PATTERN.matcher(link).matches() ? link : Protocol.POST_EMPTY_FIELD;
	}
	
	public static boolean isValidPost(PostValidatorResponse resp) {
		if(resp.getTitle().equals(Protocol.POST_EMPTY_FIELD) || resp.getTitle().equals(Protocol.POST_TITLE_NOT_CORRECT)) {
			return false;
		}
		if(resp.getDescription().equals(Protocol.POST_EMPTY_FIELD)) {
			return false;
		}
		if(resp.getRefLink().equals(Protocol.POST_EMPTY_FIELD)) {
			return false;
		}
		
		return true;
		
		
	}
	
}
