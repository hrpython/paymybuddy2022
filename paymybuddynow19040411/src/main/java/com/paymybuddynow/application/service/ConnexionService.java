package com.paymybuddynow.application.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.paymybuddynow.application.model.Connexion;

public interface ConnexionService extends UserDetailsService{
	List<Connexion> findConnexion(Long id);
	
	
}