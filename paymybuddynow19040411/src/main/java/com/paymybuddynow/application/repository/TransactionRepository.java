package com.paymybuddynow.application.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.paymybuddynow.application.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{
	//requete manuelle permettant de récupérer les transactions pourlesquelles seul l'user en cours est l'émetteur
	@Query(value = "SELECT * FROM transaction t WHERE ( t.transmitter_id = ?1 ) ", nativeQuery = true)
	List<Transaction> findTransactionOnlyUser(Long id);
}
