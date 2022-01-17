package it.unical.demacs.informatica.digitales.app;

import it.unical.demacs.informatica.digitales.app.beans.Moderator;
import it.unical.demacs.informatica.digitales.app.dao.ModeratorDAOImpl;

/**
 * Use this class to add Moderators to the Database. 
 * Don't add Moderators Data manually: the password must be encrypted.
 * 
 */
public class CreateModeratorsLauncher {

	public static void main(String[] args) {
		createModerator("moderator1", "moderator1", "moderator1@portfoliobuilder.it");
		createModerator("moderator2", "moderator2", "moderator2@portfoliobuilder.it");
		createModerator("moderator3", "moderator3", "moderator3@portfoliobuilder.it");
		createModerator("moderator4", "moderator4", "moderator4@portfoliobuilder.it");
	}

	private static void createModerator(String username, String password, String email) {
		Moderator m = new Moderator();
		m.setUsername(username);
		m.setPassword(password);
		m.setEmail(email);
		ModeratorDAOImpl.getInstance().create(m);
	}
	
}
