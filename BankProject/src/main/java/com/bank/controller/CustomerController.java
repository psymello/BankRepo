package com.bank.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bank.model.Account;
import com.bank.model.Card;
import com.bank.model.Customer;
import com.bank.repo.AccountRepository;
import com.bank.repo.CardRepository;
import com.bank.repo.CustomerRepository;


@Controller
public class CustomerController {
	@Autowired
	private CustomerRepository repo;
	
	@Autowired
	private CardRepository cardRepo;

	@Autowired
	private AccountRepository accRepo;


	private Authentication authentication;
	
	@GetMapping("/login")
	public String customerLoginPageRedirect() {
		authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken)
		{
			return "login";
		}
		return "redirect: ";
	}
	
	@GetMapping("/index")
	public String dashboardPage(Principal principal) {
		Customer customer = repo.findByUsername(principal.getName());
		//Optional<Account> account = accRepo.findById(customer.getId());
		Iterable<Card> listCard = cardRepo.findByCustomerId(customer.getId());
		for(Card s:listCard)
			System.out.println(s.getCardNumber());
		System.out.println(customer.getId());
		System.out.println(principal.getName());
		return "index";
	}
	
	@GetMapping("/home")
	public String customerAddPage() {
		//System.out.println(authentication.getName());
		return "index";
	}

	@PostMapping("/process_add")
	public String processAdd(Customer customer) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(customer.getPassword());
		customer.setPassword(encodedPassword);
		repo.save(customer);
		return "add_success";
	}
	
}
