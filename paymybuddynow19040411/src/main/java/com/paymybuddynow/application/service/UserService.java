package com.paymybuddynow.application.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;
import com.paymybuddynow.application.exception.BankTransactionException;
import com.paymybuddynow.application.model.Connexion;
import com.paymybuddynow.application.model.User;

public interface UserService extends UserDetailsService {
	

	User saveUser(User user);

	User getUserById(Long id);

	Connexion saveUserConnexion(Long id);

	List<User> getAllUsersMinusOne(Long id);
	
	User findByEmail(String email);

	public void addAmount(Long id, double amount) throws BankTransactionException;// définition de méthode utilisée pour la validation de la transaction

	void sendMoney(Long fromAccountId, Long toAccountId, double amount, String description)// définition de méthode utilisée pour la validation de la transaction
			throws BankTransactionException;

	User getOne(Long id);
}
