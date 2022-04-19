package com.paymybuddynow.application.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MainController {
	

	@GetMapping("/login")
	public String login() {
		return "login";
	}

	@GetMapping("/")
	public String home() {
		return "login";
	}

	//sur l'url /login et / avec les méthodes traitement de l'accès et retour vers login en cas d'insuccès 

	
}
