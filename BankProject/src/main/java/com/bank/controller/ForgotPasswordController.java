package com.bank.controller;

import java.util.Random;

import org.hibernate.internal.build.AllowSysOut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bank.encoder.CustomerPasswordEncoder;
import com.bank.model.Customer;
import com.bank.repo.CustomerRepository;
import com.bank.services.AccountService;
import com.bank.services.CustomerService;
import com.bank.services.EmailService;

@Controller
public class ForgotPasswordController {
	
	private Random random = new Random();
	
	@Autowired
	private EmailService emailService;
	
	@Autowired
	private AccountService accountService;
	
	@Autowired
	private CustomerService customerService;
	
	@Autowired
	private CustomerRepository customerRepository;
	
	private String otp;
	
	public Customer cust;
	
	private CustomerPasswordEncoder encoder;
	
	@RequestMapping("/forgot")
	public String usernameForm() {
		return "forgotPassForm";
	}

	@RequestMapping("/verifyOtp")
	public String verifyForm() {
		return "verifyOtp";
	}
	
	@PostMapping("/sendOtp")
	public String sendOtp(@RequestParam("username") String username) {
		if(customerService.checkValidUsername(username))
		{
			cust = customerService.getByUsername(username);
			otp = Integer.toString(100000+random.nextInt(900000));
			String subject="OTP For Password Reset";
			String message="OTP : "+otp;
			String email = accountService.getEmailByCustomerUsername(username);
			
			emailService.sendEmail(subject, message, email);
			
			return "verifyOtp";
			
		}
		return "redirect:/forgot?error";
	}
	
	@PostMapping("/verifyOtp")
	public String verifyOtp(@RequestParam("otp") String otp) {
		if(this.otp.equals(otp)) {
			return "redirect:/setNewPassword?user="+cust.getUsername();
		}
		return "redirect:/verifyOtp?error";
	}
	
//	@PostMapping("/setNewPassword")
//	public String setNewPassword(@RequestParam("password") String password,@RequestParam("confirm") String confirm, Customer customer) {
//		if(password.equals(confirm)) {
//			System.out.println("equal");
//			cust.setPassword(encoder.encodedPassword(password));
//			customerRepository.save(cust);
//			return "redirect:/index";
//		}
//		return "redirect:/setNewPassword?error";
//	}
}
