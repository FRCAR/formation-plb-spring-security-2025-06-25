package fr.maboite.correction.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import fr.maboite.correction.service.ServiceA;
import fr.maboite.correction.service.ServiceC;
import fr.maboite.correction.service.ServiceD;

@Configuration
public class ConfigurationServicesCorrection {

	@Bean
	public ServiceC serviceC() {
		return new ServiceC();
	}

	@Bean
	public ServiceD serviceD(ServiceA serviceA) {
		return new ServiceD(serviceA);
	}
	
}
