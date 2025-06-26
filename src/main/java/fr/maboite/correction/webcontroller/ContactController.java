package fr.maboite.correction.webcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ContactController {

	@GetMapping({ "/contact.html" })
	public String showContact() {
		return "vue-contact";
	}

}