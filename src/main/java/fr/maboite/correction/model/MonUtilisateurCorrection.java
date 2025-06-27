package fr.maboite.correction.model;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "MON_UTILISATEUR")
public class MonUtilisateurCorrection implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String login;
	private String motDePasse;

	// Le eager ici est discutable, mais ça va pour une formation
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "UTILISATEUR_ROLE", inverseJoinColumns = @JoinColumn(name = "ROLE_ID"), joinColumns = @JoinColumn(name = "UTILISATEUR_ID"))
	private Set<MonRoleCorrection> roles = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMotDePasse() {
		return motDePasse;
	}

	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}

	public Set<MonRoleCorrection> getRoles() {
		return roles;
	}

	public void setRoles(Set<MonRoleCorrection> roles) {
		this.roles = roles;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<GrantedAuthority> authorities = new HashSet<GrantedAuthority>();
		for(MonRoleCorrection role : this.roles) {
			authorities.addAll(role.getPermissions());
		}
		return authorities;
	}

	@Override
	public String getPassword() {
		return this.motDePasse;
	}

	@Override
	public String getUsername() {
		return this.login;
	}

}
