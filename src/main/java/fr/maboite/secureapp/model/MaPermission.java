package fr.maboite.secureapp.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "MA_PERMISSION")
public class MaPermission{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;

	@ManyToMany(mappedBy = "permissions")
	private Set<MonRole> roles = new HashSet<>();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public Set<MonRole> getRoles() {
		return roles;
	}

	public void setRoles(Set<MonRole> roles) {
		this.roles = roles;
	}


}
