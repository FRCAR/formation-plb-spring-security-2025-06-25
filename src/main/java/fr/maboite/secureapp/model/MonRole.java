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
@Table(name = "MON_ROLE")
public class MonRole {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;

	@ManyToMany(mappedBy = "roles")
	private Set<MonUtilisateur> monUtilisateur = new HashSet<>();

	// Le eager ici est aussi discutable, mais ça va pour une formation
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "ROLE_PERMISSION", inverseJoinColumns = @JoinColumn(name = "PERMISSION_ID"), joinColumns = @JoinColumn(name = "ROLE_ID"))
	private Set<MaPermission> permissions = new HashSet<>();

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

	public Set<MonUtilisateur> getMonUtilisateur() {
		return monUtilisateur;
	}

	public void setMonUtilisateur(Set<MonUtilisateur> monUtilisateur) {
		this.monUtilisateur = monUtilisateur;
	}

	@Override
	public String toString() {
		return "MonRole [id=" + id + ", nom=" + nom + "]";
	}

	public Set<MaPermission> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<MaPermission> permissions) {
		this.permissions = permissions;
	}

}
