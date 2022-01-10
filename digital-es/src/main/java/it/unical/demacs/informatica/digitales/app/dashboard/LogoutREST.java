package it.unical.demacs.informatica.digitales.app.dashboard;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/dashboard")
public class LogoutREST {
	
	@GetMapping(value = {"/do_logout", "/moderator/do_logout"})
	public void doLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Cookie userCookie= Servlets.getCookie(req, "logged_username");
		Cookie moderatorCookie= Servlets.getCookie(req, "logged_moderator");
		
		Servlets.deleteCookie(resp, moderatorCookie);
		Servlets.deleteCookie(resp, userCookie);
		
		resp.sendRedirect("/dashboard/login");
		
	}

}
