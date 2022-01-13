package it.unical.demacs.informatica.digitales.app.dashboard;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppHomePageController {
	
	@GetMapping("/home")
	public synchronized String showAppHomePage() {
		return "app_homepage";
	}
	
	@GetMapping(value = {"", "/"})
	public synchronized String showAppHomePageRedirect() {
		return "app_homepage";
	}

}
