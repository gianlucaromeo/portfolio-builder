package it.unical.demacs.informatica.digitales.app.dashboard;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;

import it.unical.demacs.informatica.digitales.app.beans.User;
import it.unical.demacs.informatica.digitales.app.validator.SignUpFormValidator;

@RestController
public class SignUpREST {
	
	private static Gson gson = new Gson();

	
	/**
	 * SignUpFormValidator controlla se i campi
	 * dello User sono stati inseriti correttamente.
	 * Questa classe produce una risposta sotto forma di
	 * SignUpFormValidatorResponse, che viene poi trasformata
	 * in json per essere spedita al client.
	 * @throws IOException 
	 * @throws JsonIOException 
	 * @throws JsonSyntaxException 
	 * */
	@PostMapping("/sign_up_action")
	public String signUpAction(HttpServletRequest req) throws JsonSyntaxException, JsonIOException, IOException {
		User user = gson.fromJson(req.getReader(), User.class);
		System.out.println(user.toString());
		return gson.toJson(SignUpFormValidator.validateUser(user));
	}
	
}
