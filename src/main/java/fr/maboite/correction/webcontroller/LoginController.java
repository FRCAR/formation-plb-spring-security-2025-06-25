package fr.maboite.correction.webcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/logout-success")
	public String logoutSuccess() {
		return "redirect:/login?logout=true";
	}

	@GetMapping("/logout-done")
	public String logoutPage() {
		return "logout";
	}
}