package it.unical.demacs.informatica.portofliobuilder.app.dashboard;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardHomePage {

	@GetMapping(value = {"/login", "/", ""})
	public String showLoginPage() {
		return "dashboard_login";
	}
	
	@GetMapping("sign_up")
	public String showSignUpPage() {
		return "sign_up";
	}
	
}
