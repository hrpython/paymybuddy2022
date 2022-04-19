package com.paymybuddynow.application.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.paymybuddynow.application.repository.UserRepository;
import com.paymybuddynow.application.service.UserService;

@Controller
public class UserUsersController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserRepository userRepository;

	public UserUsersController(UserService userService, UserRepository userRepository) {
		super();
		this.userService = userService;
		this.userRepository = userRepository;
	}
	
		// handler de la méthode list users and return mode and view affichage des users pouvant être connectés
				@GetMapping("/users")
				public String listUsers(Model model) {
					Authentication auth = SecurityContextHolder.getContext().getAuthentication();
					String name = auth.getName();
					Long idlogged = (userRepository.findByEmail(name)).getId();
					model.addAttribute("users", userService.getAllUsersMinusOne(idlogged));
					return "users";

				}
		// handler ajout dans la liste des connectés

		@GetMapping("/users/{id}")
		public String ajoutUser(@PathVariable Long id) {

			userService.saveUserConnexion(id);
			return "redirect:/transaction?success";

		}

}
