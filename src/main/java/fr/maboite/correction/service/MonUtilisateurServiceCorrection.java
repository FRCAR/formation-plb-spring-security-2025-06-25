package fr.maboite.correction.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import fr.maboite.correction.dao.MonUtilisateurDaoCorrection;
import fr.maboite.correction.model.MonUtilisateurCorrection;

@Service
public class MonUtilisateurServiceCorrection implements UserDetailsService {

	@Autowired
	private MonUtilisateurDaoCorrection monUtilisateurDao;

	public MonUtilisateurCorrection save(MonUtilisateurCorrection monUtilisateur) {
		return this.monUtilisateurDao.save(monUtilisateur);
	}

	public Optional<MonUtilisateurCorrection> findById(Long id) {
		return this.monUtilisateurDao.findById(id);
	}
	
	public Optional<MonUtilisateurCorrection> findByLogin(String login){
		return this.monUtilisateurDao.findByLogin(login);
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return findByLogin(username).orElseThrow(() -> new UsernameNotFoundException("Could not find user"));
	}

}
