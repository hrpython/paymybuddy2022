package com.paymybuddynow.application.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.paymybuddynow.application.exception.BankTransactionException;
import com.paymybuddynow.application.model.Connexion;
import com.paymybuddynow.application.model.Transaction;
import com.paymybuddynow.application.repository.UserRepository;
import com.paymybuddynow.application.service.ConnexionService;
import com.paymybuddynow.application.service.SendMoneyForm;
import com.paymybuddynow.application.service.TransactionService;
import com.paymybuddynow.application.service.UserService;

@Controller
public class TransactionController {
	// handler de la méthode list connexion and return mode and view
	@Autowired
	private TransactionService transactionService;
	@Autowired
	private ConnexionService connexionService;
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;
	
	
	public TransactionController(TransactionService transactionService, ConnexionService connexionService,
			UserService userService,  UserRepository userRepository) {
		super();
		this.transactionService = transactionService;
		this.connexionService = connexionService;
		this.userService = userService;
		this.userRepository = userRepository;
	}

	// Get all transactions, returns a list of all transactions
	@GetMapping("/transaction")
	public String listTransactions(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String name = auth.getName();
		Long idlogged = (userRepository.findByEmail(name)).getId();
		//permet de récuperer l'id de l'user loggé
		model.addAttribute("users", userService.getUserById(idlogged));
		//permet d'afficher la liste des users
		model.addAttribute("transactions", transactionService.getTransactions(idlogged));
		//permet d'afficher la liste des transactions émises par l'user loggé
		List<Connexion> connexion = connexionService.findConnexion(idlogged);
		model.addAttribute("connexions", connexion);
		//permet d'afficher les users qui ont été connectés pour une transaction avec l'user loggé

		return "transaction";
	}

	@PostMapping("/transaction")//permet de créer la transaction entre l'user loggé et un connecté préalablement choisi dans la liste déroulante
	public String transactionSubmit(@ModelAttribute SendMoneyForm sendMoneyForm, Model model,
			@RequestParam(name = "toAccountId") Long toAccountId, @RequestParam(name = "amount") double amount,
			@RequestParam(name = "description") String description) {
		model.addAttribute("sendMoneyForm", sendMoneyForm);
		// permet de récupérer l'id de l'user courant 
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
	      String email = auth.getName();
	      Long idlogged = (userService.findByEmail(email)).getId();
		try {
			userService.sendMoney(idlogged, //mécanisme de validation de la transaction
					toAccountId, //
					amount, description);
			System.out.println(idlogged);
			System.out.println(toAccountId);

		} catch (BankTransactionException e) {//Si exception retour ici avec message d'erreur
			model.addAttribute("errorMessage", "Error: " + e.getMessage());
			System.out.println(e.getMessage());
			return "/transaction";
		}//si la transaction se passe correctement elle est créée puis enregistrée
		transactionService.save(
				new Transaction(idlogged, toAccountId, (userService.getOne(toAccountId)).getLastName(), amount, description));
		return "redirect:/transaction";
	}
}

