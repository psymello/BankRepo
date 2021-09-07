package com.bank.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.bank.model.Card;
import com.bank.repo.CardRepository;

@Controller
public class ChangePinController {
	
	@Autowired
	private CardRepository cardRepo;
	
	private Card cardobj;
	
	@ModelAttribute("changecard")
	public Card card() {
		return new Card();
	}
	
	@PostMapping("/changePin/{id}")
    public String changePin(@PathVariable Long id, Model model){
		System.out.println(id);
        Optional<Card> card = cardRepo.findById(id);
        cardobj = card.get();
        return "changepin";
    }
	
	@PostMapping("/cardUpdate")
    public String changePinSubmit(Card card){
		System.out.println(card.getCardPin());
		System.out.println(cardobj.getCardPin());
        return "changepin";
    }

}
