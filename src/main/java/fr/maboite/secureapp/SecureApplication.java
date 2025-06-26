package fr.maboite.secureapp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import fr.maboite.correction.service.ServiceD;

@SpringBootApplication
public class SecureApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext applicationContext = SpringApplication.run(SecureApplication.class, args);
		applicationContext.getBean(ServiceD.class).affichage();
	}

}
