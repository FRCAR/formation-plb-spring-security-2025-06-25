package fr.maboite.correction.webcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * Annotée par @Controller, cette classe va être considérée par Spring MVC pour
 * traiter des requêtes.
 */
@Controller
public class SalutControleur {

	@GetMapping({ "/salut.html" })
	public String showSalut() {
		return "vue-salut";
	}

}