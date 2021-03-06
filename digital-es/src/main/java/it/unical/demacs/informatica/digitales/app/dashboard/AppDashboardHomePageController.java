package it.unical.demacs.informatica.digitales.app.dashboard;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/dashboard")
public class AppDashboardHomePageController {

	@GetMapping("/login/")
	public void loginPageRedirect(HttpServletResponse resp) throws IOException {
		resp.sendRedirect("/dashboard/login");
	}
	
	@GetMapping(value = {"/login", "/", ""})
	public String showLoginPage() {
		return "app_login";
	}
	
	@GetMapping("sign_up")
	public String showSingUpPage() {
		return "app_signup";
	}
	
	@GetMapping("forgot_password")
	public String showForgotPasswordPage() {
		return "app_forgot_password";
	}
	
	@GetMapping("email_confirmation_page")
	public String showConfirmEmailInfoPage() {
		return "app_email_confirmation_info";
	}
	@GetMapping("banned_user")
	public String showBannedUserPage() {
		return "banned_user_page";
	}

}
