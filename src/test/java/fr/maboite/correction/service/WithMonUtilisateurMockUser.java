package fr.maboite.correction.service;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.test.context.support.WithSecurityContext;

/**
 * Annotation permettant de définir un utilisateur
 * avant qu'un test ne se lance
 */
@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = MonUtilisateurSecurityContextFactory.class)
public @interface WithMonUtilisateurMockUser {

	String name() default "user";
}