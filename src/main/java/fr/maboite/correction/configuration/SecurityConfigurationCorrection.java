package fr.maboite.correction.configuration;

import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.argon2.Argon2PasswordEncoder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.StandardPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableMethodSecurity(securedEnabled = true)
public class SecurityConfigurationCorrection {

	private static final String JWT_HS256_SECRET = "a-string-secret-at-least-256-bits-long-a-moi";

	/**
	 * Filtre de sécurité pour authentifier les 
	 * requêtes HTTP à partir de leur jeton JWT contenu dans l'entête Authorization avec le 
	 * schéma Bearer
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
	@Order(1)
	public SecurityFilterChain securityFilterChainJwt(HttpSecurity http) throws Exception {
		return http
				.securityMatcher("/rest/**")
				.authorizeHttpRequests(auth -> auth
						.anyRequest().authenticated())
				// Spring Security va travailler en mode stateless
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				// Ajout des capacités de traitement JWT
				.oauth2ResourceServer(oauth2 -> oauth2
						.jwt(Customizer.withDefaults()))
				.build();
	}

	@Bean
	JwtDecoder jwtDecoder() {
		SecretKey key = new SecretKeySpec(JWT_HS256_SECRET.getBytes(), "HmacSHA256");
		return NimbusJwtDecoder.withSecretKey(key).build();
	}

	/**
	 * Filtre de sécurité pour authentifier les 
	 * requêtes HTTP de la chaîne (login mot de passe) contenue dans l'entête Authorization avec le 
	 * schéma Basic
	 * @param http
	 * @return
	 * @throws Exception
	 */
	public SecurityFilterChain securityFilterChainBasic(HttpSecurity http) throws Exception {
		return http
				.securityMatcher("/rest/**")
				// Active l'authentification basic
				.httpBasic(Customizer.withDefaults())
				// Active les filtres sur les requêtes
				.authorizeHttpRequests(requests -> requests
						// Toute requête ne peut être émise que par une personne
						// authentifiée
						.anyRequest().authenticated())
				// Les sessions sont inutiles ici
				.sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.build();
	}

	/**
	 * Filtre de sécurité pour authentifier 
	 * les utilisateurs humains à partir d'un formulaire
	 * HTML de login, et grâce à un cookie (le jsessionid)
	 * @param http
	 * @return
	 * @throws Exception
	 */
	@Bean
	@Order(2)
	public SecurityFilterChain securityFilterChainHtml(HttpSecurity http) throws Exception {
		http
				.authorizeHttpRequests(requests -> requests
						// / et /home /error et /contact.html peuvent être requêtées par tout le monde
						.requestMatchers("/", "/home", "/error").permitAll()
						// De même que les ressources dans les répertoires de static
						.requestMatchers("image/**").permitAll()
						.requestMatchers("/contact-*.html").permitAll()
						.requestMatchers("/contact.html").hasAuthority("ROLE_ADMIN")
						// Toute autre requête ne peut être émise que par une personne
						// authentifiée
						.anyRequest().authenticated())
				.exceptionHandling(c -> c.accessDeniedPage("/denied.html"))

				// la page de login est accessible via /login
				// et est accessible par tout le monde
				.formLogin(form -> form
						.loginPage("/login")
						.permitAll())
				.rememberMe(c-> c.key("mon-mot-de-passe-introuvable").tokenValiditySeconds(222_000))
				// La page de logout est aussi accessible
				// par tout le monde
				.logout(logout -> logout.logoutUrl("/logout")
						.logoutSuccessUrl("/logout-done")
						.logoutRequestMatcher(new AntPathRequestMatcher("/logout", "GET")).permitAll());
		return http.build();
	}

	// Un passwordEncoder gérant plusieurs
	// 'codages' de mots de passe en base de données
	@Bean
	public PasswordEncoder passwordEncoder() {
		String idForEncode = "bcrypt";
		Map<String, PasswordEncoder> encoders = new HashMap<>();
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(10);
		encoders.put(idForEncode, bCryptPasswordEncoder);
		encoders.put("argon2@SpringSecurity_v5_8", Argon2PasswordEncoder.defaultsForSpringSecurity_v5_8());
		encoders.put("sha256", new StandardPasswordEncoder());
		encoders.put("noop", NoOpPasswordEncoder.getInstance());
		return new DelegatingPasswordEncoder(idForEncode, encoders);
	}

}
