package com.bank.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bank.model.Card;
import com.bank.model.Customer;
import com.bank.repo.CardRepository;

@Controller
public class WebController {
	
	private Authentication authentication;
	
	@Autowired
	private CardRepository cardRepo;
	
	@GetMapping("/login")
	public String customerLoginPageRedirect() {
		authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken)
		{
			return "login";
		}
		return "redirect: ";
	}
	
	@GetMapping("")
	public String indexPage(){
		return "redirect:index";
	}
}
