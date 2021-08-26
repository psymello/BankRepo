package com.bank.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.bank.model.User;
import com.bank.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	private final UserService userservice;
	
	public UserController(UserService userservice) { 
		this.userservice=userservice;
	}
	
	@GetMapping("/all")
	public ResponseEntity<List<User>> getAllUser(){
		List<User> users = userservice.findAllUsers();
		return new ResponseEntity<>(users, HttpStatus.OK);
	}
	
	/*
	 * @GetMapping("/find/{id}") public ResponseEntity<List<User>>
	 * getUserById(@PathVariable("id") Long id){ List<User> users = userservice.
	 * return new ResponseEntity<>(users, HttpStatus.OK); }
	 */
}
