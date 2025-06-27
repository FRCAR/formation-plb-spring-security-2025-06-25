package fr.maboite.correction.webcontroller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import fr.maboite.correction.model.Voiture;
import fr.maboite.correction.service.ContactService;

@Controller
public class ContactController {

	@Autowired
	private ContactService contactService;

	@GetMapping({ "/contact.html" })
	public String showContact() {
		return "vue-contact";
	}

	@GetMapping({ "/contact-pre-authorize.html" })
	public String showContactPreAuthorize() {
		this.contactService.afficheChaineSecurisee();
		return "vue-contact";
	}

	@GetMapping({ "/contact-post-authorize.html" })
	public String showContactPostAuthorize() {
		String chaineSecurisee = this.contactService.renvoieChaineSecurisee();
		System.out.println("Je peux afficher la chaîne sécurisée : " + chaineSecurisee);
		return "vue-contact";
	}

	@GetMapping({ "/contact-pre-filter.html" })
	public String showContactPreFilter() {
		List<String> strings = new ArrayList<>();
		strings.add("user");
		strings.add("admin");
		strings.add("toto");
		this.contactService.afficheChaines(strings);
		return "vue-contact";
	}

	@GetMapping({ "/contact-post-filter.html" })
	public ModelAndView showContactPostFilter() {
		System.out.println("J'affiche des voitures :");
		Set<Voiture> voitures = this.contactService.getVoitures();
		voitures.forEach(System.out::println);
		Map<String, Object> model = new HashMap<>();
		model.put("voitures", voitures);
		return new ModelAndView("vue-voitures", model);
	}

}