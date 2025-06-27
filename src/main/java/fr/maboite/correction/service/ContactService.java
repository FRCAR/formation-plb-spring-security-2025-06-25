package fr.maboite.correction.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.security.access.prepost.PostAuthorize;
import org.springframework.security.access.prepost.PostFilter;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.access.prepost.PreFilter;
import org.springframework.stereotype.Service;

import fr.maboite.correction.model.Voiture;

@Service
public class ContactService {

	@PreAuthorize("hasRole('USER')")
	//@Secured("ROLE_USER")
	public void afficheChaineSecurisee()  {
		System.out.println("J'affiche une chaîne de caractères.");
	}
	

	@PostAuthorize("authentication.name == 'admin' || !returnObject.startsWith('IMPORTANT')")
	public String renvoieChaineSecurisee() {
		return "IMPORTANT : ceci est réservé à admin";
	}
	

	@PreFilter("filterObject == authentication.name")
	public void afficheChaines(List<String> chaines) {
		System.out.println("J'affiche les chaînes filtrées");
		chaines.forEach(System.out::println);
	}
	

	@PreAuthorize("isAuthenticated()")
	@PostFilter("filterObject.id == principal.id")
	public Set<Voiture> getVoitures() {
		Set<Voiture> tutures = new HashSet<>();
		tutures.add(new Voiture(55, "jean"));
		tutures.add(new Voiture(1, "user"));
		tutures.add(new Voiture(2, "admin"));
		tutures.add(new Voiture(1, "compagne de user"));
		tutures.add(new Voiture(35, "paulo"));
		return tutures;
	}

}
