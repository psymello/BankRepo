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
import org.springframework.web.bind.annotation.RequestParam;

import com.bank.encoder.CustomerPasswordEncoder;
import com.bank.model.Account;
import com.bank.model.Customer;
import com.bank.repo.CustomerRepository;

@Controller
@RequestMapping("/setNewPassword")
public class SetNewPasswordController {
	
	@Autowired
	private CustomerRepository customerRepository;
	
	@ModelAttribute("custpass")
	public Customer customer() {
		return new Customer();
	}
	
	@GetMapping
	public String viewpage() {
		return "newPassword";
	}
	
	@PostMapping
    public String submitForm(@Valid @ModelAttribute("custpass") Customer customer, BindingResult bindingResult, @RequestParam("user") String user, @RequestParam("confirm") String confirm) {
        if (bindingResult.hasErrors()) {
            return "newPassword";
        }
        
        if(customer.getPassword().equals(confirm)) {
			System.out.println("equal");
			Customer cust = customerRepository.findByUsername(user);
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String encodedPassword = encoder.encode(confirm);
			cust.setPassword(encodedPassword);
			customerRepository.save(cust);
			return "redirect:/index";
		}
        	
        return "redirect:/setNewPassword?error";
    }
}
