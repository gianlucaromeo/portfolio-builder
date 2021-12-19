package it.unical.demacs.informatica.digitales.app.validator;

import java.util.ArrayList;
import java.util.List;

import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.beans.validation.SignUpFormValidatorResponse;
import it.unical.demacs.informatica.digitales.app.database.protocol.Protocol;

public class SignUpFormValidator {

	public static SignUpFormValidatorResponse validateUser(User user) {
		SignUpFormValidatorResponse resp = new SignUpFormValidatorResponse();
		resp.setFirstNameResp(checkName(user.getFirstName()));
		resp.setLastNameResp(checkLastName(user.getLastName()));
		resp.setUsernameResp(checkUsername(user.getUsername()));
		resp.setPasswordResp(checkPassword(user.getPassword()));
		resp.setDateOfBirthResp(checkDateOfBirth(user.getDateOfBirth()));
		resp.setEmailResp(checkEmail(user.getEmail()));
		
		
		return resp;
	}
	
	private static String checkName(String name) {
		return Protocol.OK;
	}
	private static String checkLastName(String lastName) {
		return Protocol.ERROR;
	}
	private static String checkDateOfBirth(String dateOfBirth) {
		return Protocol.OK;
	}
	private static String checkEmail(String email) {
		return Protocol.OK;
	}
	private static String checkUsername(String username) {
		return Protocol.OK;
	}
	private static String checkPassword(String password) {
		return Protocol.ERROR;
	}
	
		//EMAIL CON CONTROLLO DOMAIN
		//Pattern pattern=Pattern.compile("[a-zA-Z]+@(([a-zA-Z]+\\.)+([a-zA-Z]){2,3})");
		//Matcher matcher=pattern.matcher(string);
		//ArrayList<String> domains=new ArrayList<String>(); //prendere domini da qualche parte(https://gist.github.com/ammarshah/f5c2624d767f91a7cbdc4e54db8dd0bf)
		//if(matcher.matches()&&domains.contains(matcher.group(1)))
		//	  return Protocol.OK;
		//return Protocol.ERROR;
		
		//SENZA CONTROLLO DOMAIN(togliere domains.contain e la parentesi in più messa per individuare il gruppo)
		
		//NOMI/COGNOMI solo con lettera maiuscola iniziale e possibili più nomi (Marco Antonio, Di Paolo, A B C D)
		//mettere {n,m} per lunghezza min/max(e mettere parentesi)
	    //Pattern pattern=Pattern.compile("[A-Z][a-z]*(\s[A-Z][a-z]*)*");
	    //Matcher matcher=pattern.matcher(string);
		
		//Altri modi di fare duplicati sarebbe fare i methodi findUsername/findEmail
		//USERNAME DUPLICATO
	    //Set<User> s=UserDAOImpl.getInstance().findAll();
		//for(User u:s) {
		//	if(u.getUsername().equals(username))
		//		return Protocol.ERROR;
		//}
		
		//MAIL DUPLICATA
		//Set<User> s=UserDAOImpl.getInstance().findAll();
		//for(User u:s) {
		//	if(u.getEmail().equals(email))
		//		return Protocol.ERROR;
		//}
	
}
