package com.bank.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bank.model.Account;
import com.bank.model.Card;
import com.bank.model.Customer;
import com.bank.repo.AccountRepository;
import com.bank.repo.CustomerRepository;

@Controller
@RequestMapping("/add_customers")
public class AddCustomerController {
	
	@Autowired
	public CustomerRepository customerRepository;
	
	@Autowired
	public AccountRepository accountRepository;

    @ModelAttribute("custom")
    public Customer customer() {
        return new Customer();
    }

    @ModelAttribute("acc")
    public Account account() {
    	return new Account();
    }
    
    @ModelAttribute("cards")
    public Card card() {
    	return new Card();
    }
    
    @ModelAttribute("tests")
    public Iterable<Customer> tests(){
    	return customerRepository.findAll();
    }
    
    @GetMapping
    public String showForm() {
        return "admin/addCustomers";
    }

    @PostMapping
    public String submitForm(@Valid @ModelAttribute("custom") Customer customer, BindingResult bindingResult, Account account) {
        if (bindingResult.hasErrors()) {
            return "admin/addCustomers";
        }
        
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String encodedPassword = encoder.encode(customer.getPassword());
		customer.setPassword(encodedPassword);
		customerRepository.save(customer);
		
		account.setId(customer.getId());
		accountRepository.save(account);
		
        return "redirect:/dashboard";
    }

}
