package it.unical.demacs.informatica.digitales.app.dashboard;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class DashboardHomePageController {

	@GetMapping("/login/")
	public void loginPageRedirect(HttpServletResponse resp) throws IOException {
		resp.sendRedirect("/dashboard/login");
	}
	
	@GetMapping(value = {"/login", "/", ""})
	public String showLoginPage() {
		return "login";
	}
	
	@GetMapping("sign_up")
	public String showSingUpPage() {
		return "sign_up";
	}
	
	@GetMapping("forgot_password")
	public String showForgotPasswordPage() {
		return "forgot_password";
	}
	
	@GetMapping("confirm_email")
	public String showConfirmEmailPage() {
		return "confirm_email";
	}

}
