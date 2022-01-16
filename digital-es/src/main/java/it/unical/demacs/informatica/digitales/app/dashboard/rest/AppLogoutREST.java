package it.unical.demacs.informatica.digitales.app.dashboard.rest;

import java.io.IOException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.unical.demacs.informatica.digitales.app.dashboard.AppServletsHandler;

@RestController
@RequestMapping("/dashboard")
public class AppLogoutREST {

	@GetMapping(value = { "/do_logout", "/moderator/do_logout" })
	public void doLogout(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		Cookie userCookie = AppServletsHandler.getCookie(req, "logged_username");
		Cookie moderatorCookie = AppServletsHandler.getCookie(req, "logged_moderator");

		AppServletsHandler.deleteCookie(resp, moderatorCookie);
		AppServletsHandler.deleteCookie(resp, userCookie);

		resp.sendRedirect("/dashboard/login");

	}

}
