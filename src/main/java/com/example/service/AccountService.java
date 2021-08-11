package com.example.service;

import java.util.List;

import com.example.entity.Account;
public interface AccountService {
	
	Account findById(String Accountid);
	
	Account update(Account account);

	List<Account> getAdministrators();

	List<Account> findAll();
	
	Integer countAll();
}
