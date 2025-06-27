package fr.maboite.correction.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithMockUser;

@SpringBootTest
public class TestContactService {
	
	@Autowired
	private ContactService contactService;
	
	@Test
	public void quelqueChoseAvecUnUtilisateurAuthentifie_sansAuthentification() {
		Assertions.assertThrows(
				AuthenticationCredentialsNotFoundException.class, 
				() -> contactService.faitQuelqueChoseAvecUnUtilisateurAuthentifie());
	}
	
	@Test
	@WithMockUser
	public void quelqueChoseAvecUnUtilisateurAuthentifie_avecAuthentification() {
		contactService.faitQuelqueChoseAvecUnUtilisateurAuthentifie();
	}
	
	@Test
	@WithMockUser(authorities = "ROLE_ADMIN")
	public void testAfficheChaineSecurisee_avecAuthentification() {
		contactService.afficheChaineSecurisee();
	}
	
	@Test
	@WithMultipleMockUsers(names = {"jean","paul"})
	public void testBouclette() {
		System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
	}

}
