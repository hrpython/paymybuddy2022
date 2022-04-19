package com.paymybuddynow.application.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;

import com.paymybuddynow.application.model.Transaction;

public interface TransactionService extends UserDetailsService {
	List<Transaction> getTransactions(Long id);

	void save(Transaction transaction);
}
