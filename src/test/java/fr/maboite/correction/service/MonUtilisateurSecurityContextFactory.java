package fr.maboite.correction.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import fr.maboite.correction.model.MonUtilisateurCorrection;

/**
 * Code exécuté par l'annotation WithMultipleMockUsers 
 * pour créer un contexte de sécurité avant chaque test.
 */
public class MonUtilisateurSecurityContextFactory
		implements WithSecurityContextFactory<WithMonUtilisateurMockUser> {

	@Override
	public SecurityContext createSecurityContext(WithMonUtilisateurMockUser withMonUtilisateurMockUser) {
		if(withMonUtilisateurMockUser.name() == null) {
			throw new IllegalArgumentException("Argument name ne peut être vide ou null");
		}
		
		SecurityContext context = SecurityContextHolder.createEmptyContext();

		MonUtilisateurCorrection principal = new MonUtilisateurCorrection();
	
		principal.setLogin(withMonUtilisateurMockUser.name());
		Authentication auth = UsernamePasswordAuthenticationToken.authenticated(principal, "password",
				principal.getAuthorities());
		context.setAuthentication(auth);
		return context;
	}
}