package com.example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.example.dao.AccountDao;

@Controller
public class AccountController {
	@Autowired
	AccountDao dao;
	
	
}
