package fr.maboite.secureapp.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.maboite.secureapp.model.MonUtilisateur;

@Repository
public interface MonUtilisateurDao extends CrudRepository<MonUtilisateur, Long> {

	Optional<MonUtilisateur> findByLogin(String login);

}
