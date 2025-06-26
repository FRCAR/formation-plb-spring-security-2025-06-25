package fr.maboite.correction.dao;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import fr.maboite.correction.model.MonUtilisateurCorrection;

@Repository
public interface MonUtilisateurDaoCorrection extends CrudRepository<MonUtilisateurCorrection, Long> {

	Optional<MonUtilisateurCorrection> findByLogin(String login);

}
