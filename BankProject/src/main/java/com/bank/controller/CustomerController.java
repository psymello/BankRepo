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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bank.model.Account;
import com.bank.model.Card;
import com.bank.model.Customer;
import com.bank.repo.AccountRepository;
import com.bank.repo.CardRepository;
import com.bank.repo.CustomerRepository;
import com.bank.services.AccountService;


@Controller
@RequestMapping("/index")
public class CustomerController {
	@Autowired
	private CustomerRepository repo;
	
	@Autowired
	private CardRepository cardRepo;

	@Autowired
	private AccountRepository accRepo;

	@Autowired
	private AccountService accServ;
	
	@ModelAttribute("customer")
	public Customer customer(Principal principal) {
		return repo.findByUsername(principal.getName());
	}
	
	@ModelAttribute("account")
	public Account account(Principal principal) {
		Customer customer = repo.findByUsername(principal.getName());
		return accServ.getAccountById(customer.getId());
	}
	
	@ModelAttribute("card")
	public Iterable<Card> cards(Principal principal){
		Customer customer = repo.findByUsername(principal.getName());
		Iterable<Card> listCard = cardRepo.findByCustomerId(customer.getId());
		return listCard;
	}
	
	@GetMapping()
	public String dashboardPage(Principal principal) {
//		Customer customer = repo.findByUsername(principal.getName());
//		Account account = accServ.getAccountById(customer.getId());
//		Iterable<Card> listCard = cardRepo.findByCustomerId(customer.getId());
//		for(Card s:listCard)
//			System.out.println(s.getCardNumber());
//		
		return "index";
	}
	
	@GetMapping("/home")
	public String customerAddPage() {
		//System.out.println(authentication.getName());
		return "index";
	}

//	@PostMapping("/process_add")
//	public String processAdd(Customer customer) {
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		String encodedPassword = encoder.encode(customer.getPassword());
//		customer.setPassword(encodedPassword);
//		repo.save(customer);
//		return "add_success";
//	}
	
}
