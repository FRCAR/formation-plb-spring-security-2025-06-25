package fr.maboite.correction.service;

public class ServiceD {

	private ServiceA serviceA;

	public ServiceD(ServiceA serviceA) {
		this.serviceA = serviceA;
	}

	public void affichage() {
		System.out.println("Je suis service D, prêt à travailler.");
		System.out.println("Je contiens un service a : ");
		this.serviceA.affichage();
	}
}
