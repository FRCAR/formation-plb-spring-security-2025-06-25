package fr.maboite.correction.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ServiceA {

	@Autowired
	private ServiceB serviceB;

	public void affichage() {
		System.out.println("Je suis service A.");
	}

	public ServiceB getServiceB() {
		return serviceB;
	}

}
