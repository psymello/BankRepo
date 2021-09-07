package com.bank.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.bank.model.Account;
import com.bank.model.Card;
import com.bank.model.Customer;
import com.bank.repo.AccountRepository;
import com.bank.repo.CardRepository;
import com.bank.repo.CustomerRepository;

@Controller
public class AdminController {
	@Autowired
	public CustomerRepository customerRepository;
	
	@Autowired
	public AccountRepository accountRepository;
	
	@Autowired
	public CardRepository cardRepository;
	
	@GetMapping("/dashboard")
	public String dashboardPage(Model model) {
		Iterable<Customer> listCustomers = customerRepository.findAll();
		model.addAttribute("listCustomers",listCustomers);
		return "admin/dashboard";
	}
	
//	@GetMapping("/add_customers")
//	public String addCustomersPage(Model model) {
//		model.addAttribute("custom", new Customer());
//		model.addAttribute("acc", new Account());
//		model.addAttribute("cards", new Card());
//		
//	    Iterable<Customer> tests = customerRepository.findAll();
//	    model.addAttribute("tests", tests);
//	    
//		return "admin/addCustomers";
//	}
	
	@PostMapping("/add_cards")
	public String addCardsSubmit(Card card) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(card.getCardPin());
		card.setCardPin(encodedPassword);
		cardRepository.save(card);
		
		return "redirect:/add_customers";
	}
	
//	@PostMapping("/add_customers_submit")
//	public String addCustomersSubmit(@Valid @ModelAttribute("custom") Customer customer, BindingResult bindingResult, Account account) {
//		if (bindingResult.hasErrors()) {
//            return "admin/addCustomers";
//        }
//		
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		String encodedPassword = encoder.encode(customer.getPassword());
//		customer.setPassword(encodedPassword);
//		customerRepository.save(customer);
//		
//		account.setId(customer.getId());
//		accountRepository.save(account);
//		
//		return "redirect:/add_customers";
//	}
//	
	@PostMapping("/deleteCustomer/{id}")
    public String deleteBuyer(@PathVariable Long id){
        customerRepository.deleteById(id);
        return "redirect:/dashboard";
    }
	
}
