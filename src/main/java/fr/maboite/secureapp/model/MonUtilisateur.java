package fr.maboite.secureapp.model;

import java.util.HashSet;
import java.util.Set;

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
public class MonUtilisateur{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String login;
	private String motDePasse;

	// Le eager ici est discutable, mais ça va pour une formation
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "UTILISATEUR_ROLE", inverseJoinColumns = @JoinColumn(name = "ROLE_ID"), joinColumns = @JoinColumn(name = "UTILISATEUR_ID"))
	private Set<MonRole> roles = new HashSet<>();

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

	public Set<MonRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<MonRole> roles) {
		this.roles = roles;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

}
