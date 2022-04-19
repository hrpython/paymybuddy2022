package com.paymybuddynow.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.paymybuddynow.application.model.Connexion;

public interface ConnexionRepository extends JpaRepository<Connexion, Long> {
	//requete manuelle permettant de récupérer la liste des connectés à l'user en cours 
	@Query(value = "SELECT * FROM connexion c WHERE c.user_id = ?1", nativeQuery = true)
	List<Connexion> findConnexion(Long id);
}
