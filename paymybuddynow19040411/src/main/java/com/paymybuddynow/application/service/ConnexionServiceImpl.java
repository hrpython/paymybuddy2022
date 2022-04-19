package com.paymybuddynow.application.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.paymybuddynow.application.model.Connexion;
import com.paymybuddynow.application.repository.ConnexionRepository;

@Service
public class ConnexionServiceImpl implements ConnexionService{

	public ConnexionServiceImpl(ConnexionRepository connexionRepository) {
		super();
		this.connexionRepository = connexionRepository;
	}
	private ConnexionRepository connexionRepository;
	
	@Override
	public List<Connexion> findConnexion(Long id) {//permet de récuperer la liste des users connéctés à l'user en cours
		
		return connexionRepository.findConnexion(id);
	}

	@Override// méthode obligatoire issue de l'interface
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
