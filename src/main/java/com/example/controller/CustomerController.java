package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.AccountService;
import com.example.service.OrderService;

@Controller
public class CustomerController {
	@Autowired
	OrderService orderService;
	
	@Autowired
	AccountService accountService;
	
	@RequestMapping("/customer")
	public String customer(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser();
		model.addAttribute("user", accountService.findById(username));
		model.addAttribute("countCheck", orderService.countCheck(username));
		model.addAttribute("countSuccess", orderService.countSuccess(username));
		return "customer/customer";
	}
	
	// order check
	@RequestMapping("/customer/order-check")
	public String orderCheck(Model model, HttpServletRequest request) {
		String Usernameid = request.getRemoteUser();
		model.addAttribute("user", accountService.findById(Usernameid));
		model.addAttribute("orders", orderService.findByUsernameCheck(Usernameid));
		return "customer/customer-order-check";
	}
	
	// order success
	@RequestMapping("/customer/order-success")
	public String orderSuccess(Model model, HttpServletRequest request) {
		String Usernameid = request.getRemoteUser();
		model.addAttribute("user", accountService.findById(Usernameid));
		model.addAttribute("orders", orderService.findByUsernameSuccess(Usernameid));
		return "customer/customer-order-success";
	}
	
	// user info
	@RequestMapping("/customer/user-info")
	public String userInfo(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser();
		model.addAttribute("user", accountService.findById(username));
		return "customer/customer-info";
	}
	
	// user password
	@RequestMapping("/customer/user-password")
	public String userPassword(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser();
		model.addAttribute("user", accountService.findById(username));
		return "customer/customer-password";
	}
	
}
