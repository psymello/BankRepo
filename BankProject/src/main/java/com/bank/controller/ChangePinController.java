package com.bank.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bank.model.Card;
import com.bank.model.Customer;
import com.bank.model.SecurityQuestions;
import com.bank.repo.CardRepository;
import com.bank.repo.CustomerRepository;
import com.bank.repo.SecurityQuestionsRepository;

@Controller
public class ChangePinController {
	
	@Autowired
	private CardRepository cardRepo;
	
	@Autowired
	private CustomerRepository custRepo;
	
	@Autowired
	private SecurityQuestionsRepository securRepo;
	
	private Card cardobj;
	
	private Customer customer;
	
	@ModelAttribute("changecard")
	public Card card() {
		return new Card();
	}
	
	@ModelAttribute("question")
	public SecurityQuestions securityquestions() {
		return new SecurityQuestions();
	}
	
	@GetMapping("/addQuestions")
	public String loadPage() {
		return "addquestions";
	}
	
	@GetMapping("/changePin")
	public String loadChangePinPage() {
		return "changepin";
	}
	
	@PostMapping("/changePin/{id}")
    public String changePin(@PathVariable Long id, Model model, Principal principal){
		customer = custRepo.findByUsername(principal.getName());
        Optional<Card> card = cardRepo.findById(id);
        cardobj = card.get();
        if(securRepo.findById(customer.getId()).isEmpty())
		{
			return "redirect:/addQuestions?error";
		}
        return "changepin";
    }
	
	@PostMapping("/cardUpdate")
    public String changePinSubmit(Card card, SecurityQuestions securityQuestions){	
		Optional<SecurityQuestions> opsq = securRepo.findById(customer.getId());
		SecurityQuestions validate = opsq.get();
		System.out.println(validate.getAns1()+""+securityQuestions.getAns1());
		System.out.println(validate.getAns2()+""+securityQuestions.getAns2());
		System.out.println(validate.getAns3()+""+securityQuestions.getAns3());
		if(validate.getAns1().equals(securityQuestions.getAns1()) && validate.getAns2().equals(securityQuestions.getAns2()) && validate.getAns3().equals(securityQuestions.getAns3()))
		{	
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			String encodedPassword = encoder.encode(card.getCardPin());
			cardobj.setCardPin(encodedPassword);
			cardRepo.save(cardobj);
	        return "redirect:/index";
		}
		return "redirect:/changePin?error";
    }
	
	@PostMapping("/addQuestions")
	public String addQuestions(SecurityQuestions securityQuestions) {
		securityQuestions.setId(customer.getId());
		securRepo.save(securityQuestions);
		return "redirect:/index";
	}
}
