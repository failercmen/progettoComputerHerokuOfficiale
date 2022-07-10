package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.controller.validator.CredenzialiValidator;
import com.example.demo.controller.validator.UtenteValidator;
import com.example.demo.model.Credenziali;
import com.example.demo.model.Utente;
import com.example.demo.service.CredenzialiService;


@Controller
public class AuthenticationController {


	@Autowired
	private CredenzialiService CredenzialiService;

	@Autowired
	private UtenteValidator UtenteValidator;

	@Autowired
	private CredenzialiValidator CredenzialiValidator;


	@PostMapping("/register")
	public String registerUser(@ModelAttribute("utente")Utente utente,BindingResult utenteBindingResult,@ModelAttribute("credenziali") Credenziali credenziali,
			BindingResult credenzialiBindingResult, Model model) {

		this.UtenteValidator.validate(utente, utenteBindingResult);// per il controllo dei duplicati---bindingResult ha tutti gli errori
		this.CredenzialiValidator.validate(credenziali, credenzialiBindingResult);

		if(!utenteBindingResult.hasErrors() && !credenzialiBindingResult.hasErrors()) {

			credenziali.setUtente(utente);
			this.CredenzialiService.saveCredentials(credenziali);
			return "registrazioneEffettuata.html"; 
		}
		return "registerForm.html"; 
	}


	//	@RequestMapping(value = "/register", method = RequestMethod.GET) 

	@GetMapping("/register")
	public String showRegisterForm (Model model) {
		model.addAttribute("utente", new Utente());
		model.addAttribute("credenziali", new Credenziali());
		return "registerForm.html";
	}
	//	
	@GetMapping("/login")
	public String showLoginForm (Model model) {
		return "loginForm.html";
	}


	@GetMapping("/logout")
	public String logout(Model model) {
		return "index.html";
	}

	@GetMapping("/default")
	public String defaultAfterLogin(Model model) {
		UserDetails userDetails = (UserDetails)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Credenziali credentials = CredenzialiService.getCredentials(userDetails.getUsername());
		if (credentials.getRuolo().equals(Credenziali.ADMIN_ROLE)) {
			return "admin/home.html";
		}
		return "home.html";
	}

	//pagina di fallimento di autenticazione
	@GetMapping("/failure")
	public String errorLogin(Model model){
		return "failure.html";
	}



}
