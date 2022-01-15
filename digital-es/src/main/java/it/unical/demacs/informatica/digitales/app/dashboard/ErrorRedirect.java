package it.unical.demacs.informatica.digitales.app.dashboard;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ErrorRedirect implements ErrorController {
	@RequestMapping("/error")
	public String handleError(HttpServletRequest request, HttpServletResponse resp) {
		try {
			Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

			if (status != null) {
				Integer statusCode = Integer.valueOf(status.toString());
				if (statusCode == HttpStatus.NOT_FOUND.value()) {
					resp.sendRedirect("/dashboard/404_page");
				} else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
					resp.sendRedirect("/dashboard/500_page");
				}
			}

			resp.sendRedirect("/dashboard/404_page");
			return null;
		} catch (Exception e) {
			return null;
		}
	}

}