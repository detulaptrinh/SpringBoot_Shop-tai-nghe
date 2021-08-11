package com.example.rest.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Account;
import com.example.service.AccountService;

@CrossOrigin("*")
@RestController
public class AccountRestController {
	@Autowired
	AccountService accountService;
	
	@GetMapping("/account/info")
	public Account getOne(HttpServletRequest request) {
		String username = request.getRemoteUser();
		return accountService.findById(username);
	}
	
	@PutMapping("/account/info/{username}")
	public Account update(@PathVariable("username") String username, @RequestBody Account account) {
		return accountService.update(account);
	}
	
	@GetMapping("/account/list")
	public List<Account> getAll() {
		return accountService.findAll();
	}
}
