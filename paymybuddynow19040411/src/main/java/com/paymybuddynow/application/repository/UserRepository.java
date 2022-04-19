package com.paymybuddynow.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.paymybuddynow.application.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findByEmail(String email);
//requete manuelle permettant de récupérer les utilisateurs à l'exception de l'user loggé en ecartant également ceux qui sont déjà connectés à l'user
	@Query(value = "SELECT * FROM user u WHERE NOT ( u.id = ?1 ) and u.id not in(SELECT connexion_id from connexion c where user_id = ?1 ) ", nativeQuery = true)
	List<User> getAllMinusOne(Long id);
}
