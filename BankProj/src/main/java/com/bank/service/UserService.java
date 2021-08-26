package com.bank.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.bank.model.User;
import com.bank.repo.UserRepo;

public class UserService {
	private final UserRepo userrepo;
	
	@Autowired
	public UserService(UserRepo userrepo) {
		this.userrepo = userrepo;
	}
	
	public User addUser(User user) {
		return userrepo.save(user);
	}
	
	public List<User> findAllUsers() {
		return userrepo.findAll();
	}
	
	public User updateuser(User user) {
		return userrepo.save(user);
	}
	
	
}
