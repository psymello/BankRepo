package com.bank.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.bank.model.Customer;
import com.bank.repo.CustomerRepository;


@Controller
public class CustomerController {
	@Autowired
	private CustomerRepository repo;

	@GetMapping("/login")
	public String customerLoginPageRedirect() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication == null || authentication instanceof AnonymousAuthenticationToken)
		{
			return "login";
		}
		
		return "redirect: ";
	}
	
//	@GetMapping("/logout")
//	public String customerLogoutPageRedirect() {
//		return "index";
//	}
	
	@GetMapping("/home")
	public String customerAddPage(Model model) {
		model.addAttribute("cust", new Customer());
		return "home";
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
