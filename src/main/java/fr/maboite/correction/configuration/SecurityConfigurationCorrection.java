package fr.maboite.correction.configuration;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class SecurityConfigurationCorrection {

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(requests -> requests
						// / et /home /error et /contact.html peuvent être requêtées par tout le monde
						.requestMatchers("/", "/home", "/error", "/contact.html").permitAll()
						//De même que les ressources dans les répertoires de static
						.requestMatchers("/css/**", "/js/**", "/img/**").permitAll()
						// Toute autre requête ne peut être émise que par une personne
						// authentifiée
						.anyRequest().authenticated())
				.exceptionHandling(c -> c.accessDeniedPage("/denied.html"))

				// la page de login est accessible via /login
				// et est accessible par tout le monde
				.formLogin(form -> form
						.loginPage("/login")
						.permitAll())
				// La page de logout est aussi accessible
				// par tout le monde
				.logout(logout -> logout.logoutUrl("/logout")
						.logoutSuccessUrl("/logout-done")
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")).permitAll());
		return http.build();

	}

	//Un passwordEncoder gérant plusieurs
	//'codages' de mots de passe en base de données
	@Bean
	public PasswordEncoder passwordEncoder() {
		String idForEncode = "bcrypt";
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
		encoders.put(idForEncode, bCryptPasswordEncoder);
		encoders.put("argon2@SpringSecurity_v5_8", Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8());
		encoders.put("sha256", new StandardPasswordEncoder() );
		encoders.put("noop", NoOpPasswordEncoder.getInstance() );
		return new DelegatingPasswordEncoder(idForEncode, encoders);
	}

}
