package com.paymybuddynow.application.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.paymybuddynow.application.model.Transaction;
import com.paymybuddynow.application.repository.TransactionRepository;

@Service
public class TransactionServiceImpl implements TransactionService {

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public void save(Transaction transaction) {
		transactionRepository.save(transaction);

	}

	@Override
	public List<Transaction> getTransactions(Long id) {// récupère la liste des transactions pour laquelle l'user a été le transmetteur
		return transactionRepository.findTransactionOnlyUser(id);
	}

}