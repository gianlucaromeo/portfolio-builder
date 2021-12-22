package it.unical.demacs.informatica.digitales.app.dashboard;

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
	public String showSingUpPage() {
		return "sign_up";
	}
	
	@GetMapping("forgot_password")
	public String showForgotPasswordPage() {
		return "forgot_password";
	}
	
}
