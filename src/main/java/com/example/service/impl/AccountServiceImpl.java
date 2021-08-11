package com.example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.dao.AccountDao;
import com.example.entity.Account;
import com.example.service.AccountService;

@Service
public class AccountServiceImpl implements AccountService {
	@Autowired
	AccountDao dao;
	
	@Override
	public Account findById(String Accountid) {
		return dao.findById(Accountid).get();
	}

	@Override
	public List<Account> getAdministrators() {
		return dao.getAdminstrators();
	}

	@Override
	public List<Account> findAll() {
		return dao.findAll();
	}

	@Override
	public Account update(Account account) {
		return dao.save(account);
	}

	@Override
	public Integer countAll() {
		return dao.countAll();
	}

}
