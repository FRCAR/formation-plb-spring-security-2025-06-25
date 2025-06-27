package fr.maboite.correction.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.test.context.support.WithSecurityContextFactory;

import fr.maboite.correction.model.MonUtilisateurCorrection;

public class MultipleUsersSecurityContextFactory
		implements WithSecurityContextFactory<WithMultipleMockUsers> {

	int compteur = 0;

	@Override
	public SecurityContext createSecurityContext(WithMultipleMockUsers multipleUsers) {
		if(multipleUsers.names() == null) {
			throw new IllegalArgumentException("Argument names ne peut être vide ou null");
		}
		int usersSize = multipleUsers.names().length;
		if(usersSize == 0) {
			throw new IllegalArgumentException("Argument names ne peut être vide ou null");
		}
		
		SecurityContext context = SecurityContextHolder.createEmptyContext();

		MonUtilisateurCorrection principal = new MonUtilisateurCorrection();
		if (compteur > usersSize) {
			compteur = 0;
		}
		principal.setLogin(multipleUsers.names()[compteur]);
		Authentication auth = UsernamePasswordAuthenticationToken.authenticated(principal, "password",
				principal.getAuthorities());
		context.setAuthentication(auth);
		compteur++;
		return context;
	}
}