package fr.maboite.correction.model;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "MA_PERMISSION")
public class MaPermissionCorrection implements GrantedAuthority{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nom;

	@ManyToMany(mappedBy = "permissions")
	private Set<MonRoleCorrection> roles = new HashSet<>();

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

	public Set<MonRoleCorrection> getRoles() {
		return roles;
	}

	public void setRoles(Set<MonRoleCorrection> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "MaPermission [id=" + id + ", nom=" + nom + "]";
	}

	@Override
	public String getAuthority() {
		return this.nom;
	}



}
