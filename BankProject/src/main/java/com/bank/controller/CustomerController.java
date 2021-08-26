package com.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bank.model.Customer;

@Controller
public class CustomerController {
	
	@GetMapping("")
	public String customerLoginPage() {
		return "index";
	}
	
	@GetMapping("/home")
	public String customerAddPage(Model model) {
		model.addAttribute("cust", new Customer());
		return "home";
	}
}
