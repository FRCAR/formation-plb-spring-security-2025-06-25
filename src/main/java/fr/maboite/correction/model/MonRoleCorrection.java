package fr.maboite.correction.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

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
public class MonRoleCorrection {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;

	@ManyToMany(mappedBy = "roles")
	private Set<MonUtilisateurCorrection> monUtilisateur = new HashSet<>();

	// Le eager ici est aussi discutable, mais ça va pour une formation
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "ROLE_PERMISSION", inverseJoinColumns = @JoinColumn(name = "PERMISSION_ID"), joinColumns = @JoinColumn(name = "ROLE_ID"))
	private Set<MaPermissionCorrection> permissions = new HashSet<>();

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

	public Set<MonUtilisateurCorrection> getMonUtilisateur() {
		return monUtilisateur;
	}

	public void setMonUtilisateur(Set<MonUtilisateurCorrection> monUtilisateur) {
		this.monUtilisateur = monUtilisateur;
	}

	@Override
	public String toString() {
		return "MonRole [id=" + id + ", nom=" + nom + "]";
	}

	public Set<MaPermissionCorrection> getPermissions() {
		return permissions;
	}

	public void setPermissions(Set<MaPermissionCorrection> permissions) {
		this.permissions = permissions;
	}

}
