package it.unical.demacs.informatica.digitales.app.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AppHomePageController {

	@GetMapping("/home")
	public synchronized String showAppHomePage() {
		return "app_homepage";
	}

	@GetMapping(value = { "", "/" })
	public synchronized String showAppHomePageRedirect() {
		return "app_homepage";
	}

}
