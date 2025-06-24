package fr.maboite.secureapp.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fr.maboite.secureapp.dao.MonUtilisateurDao;
import fr.maboite.secureapp.model.MonUtilisateur;

@Service
public class MonUtilisateurService {

	@Autowired
	private MonUtilisateurDao monUtilisateurDao;

	public MonUtilisateur save(MonUtilisateur monUtilisateur) {
		return this.monUtilisateurDao.save(monUtilisateur);
	}

	public Optional<MonUtilisateur> findById(Long id) {
		return this.monUtilisateurDao.findById(id);
	}
	
	public Optional<MonUtilisateur> findByLogin(String login){
		return this.monUtilisateurDao.findByLogin(login);
	}

}
