package fr.maboite.correction.service;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import org.springframework.security.test.context.support.WithSecurityContext;

@Retention(RetentionPolicy.RUNTIME)
@WithSecurityContext(factory = MultipleUsersSecurityContextFactory.class)
public @interface WithMultipleMockUsers {

	String[] names() default "Jean Dupont";
}