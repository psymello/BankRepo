package com.bank.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.bank.model.Customer;


@Controller
public class WebController {

	@GetMapping("")
	public String indexPage(){
		return "redirect:index";
	}
}
