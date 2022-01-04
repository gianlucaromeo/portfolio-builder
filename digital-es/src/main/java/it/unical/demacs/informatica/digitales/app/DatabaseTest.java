package it.unical.demacs.informatica.digitales.app;

import it.unical.demacs.informatica.digitales.app.beans.Moderator;
import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.dao.ModeratorDAOImpl;
import it.unical.demacs.informatica.digitales.app.dao.UserDAOImpl;

public class DatabaseTest {
	public static void main(String[] args) {
	/*	Moderator m= new Moderator();
		m.setPassword("ciao");
		m.setUsername("mario");
		m.setEmail("peppino@live.com");
		ModeratorDAOImpl.getInstance().create(m);
		*/
		User u= new User();
		u.setFirstName("peppe");
		u.setConfirmed(false);
		u.setContactEmail("mamajajajakak");
		u.setDateOfBirth("2000-12-11");
		u.setEmail("ciao@live");
		u.setLastName("drami");
		u.setPassword("ciao");
		u.setMainPhoneNumber("33222");
		u.setUsername("peppino69");
		u.setSignUpDate("2000-21-12");
		
		UserDAOImpl.getInstance().create(u);
		
	}

}
