package it.unical.demacs.informatica.digitales.app.dashboard;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginRedirectController {
	
	@GetMapping("")
	public synchronized void goToLogin(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.sendRedirect("/dashboard/login");
	}

}
