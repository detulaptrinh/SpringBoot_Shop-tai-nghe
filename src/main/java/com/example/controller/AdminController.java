package com.example.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.service.AccountService;
import com.example.service.OrderService;

@Controller
public class AdminController {
	@Autowired
	OrderService orderService;
	
	@Autowired
	AccountService accountService;
	
	@RequestMapping("/admin")
	public String customer(Model model, HttpServletRequest request) {
		String username = request.getRemoteUser();
		model.addAttribute("user", accountService.findById(username));
		model.addAttribute("countCheck", orderService.countCheckAdmin());
		model.addAttribute("countSuccess", orderService.countSuccessAdmin());
		model.addAttribute("doanhThu", orderService.doanhThuAdmin());
		return "admin/admin";
	}
	
	// order check
	@RequestMapping("/admin/order-check")
	public String orderCheck(Model model, HttpServletRequest request) {
		String Usernameid = request.getRemoteUser();
		model.addAttribute("user", accountService.findById(Usernameid));
		model.addAttribute("orders", orderService.findByCheck());
		return "admin/admin-order-check";
	}
	
	// order success
	@RequestMapping("/admin/order-success")
	public String orderSuccess(Model model, HttpServletRequest request) {
		String Usernameid = request.getRemoteUser();
		model.addAttribute("user", accountService.findById(Usernameid));
		model.addAttribute("orders", orderService.findBySuccess());
		return "admin/admin-order-success";
	}
	
	// category
	@RequestMapping("/admin/category")
	public String category(Model model, HttpServletRequest request) {
		String Usernameid = request.getRemoteUser();
		model.addAttribute("user", accountService.findById(Usernameid));
		return "admin/admin-category";
	}
	
	// product
	@RequestMapping("/admin/product")
	public String product(Model model, HttpServletRequest request) {
		String Usernameid = request.getRemoteUser();
		model.addAttribute("user", accountService.findById(Usernameid));
		return "admin/admin-product";
	}
	
	// statistics order
	@RequestMapping("/admin/statistics-order")
	public String statisticsOrder(Model model, HttpServletRequest request) {
		String Usernameid = request.getRemoteUser();
		model.addAttribute("user", accountService.findById(Usernameid));
		model.addAttribute("countDay", orderService.countDay());
		model.addAttribute("countYesterday", orderService.countYesterday());
		model.addAttribute("countMoth", orderService.countMoth());
		return "admin/admin-statistics-order";
	}
	
	// statistics revenue
	@RequestMapping("/admin/statistics-revenue")
	public String statisticsRevenue(Model model, HttpServletRequest request) {
		String Usernameid = request.getRemoteUser();
		model.addAttribute("user", accountService.findById(Usernameid));
		Long sumDay = orderService.sumDay();
		if (sumDay == null) {
			model.addAttribute("sumDay", "0");
		} else {
			model.addAttribute("sumDay", orderService.sumDay());
		}
		
		Long sumYesterday = orderService.sumYesterday();
		if (sumYesterday == null) {
			model.addAttribute("sumYesterday", "0");
		} else {
			model.addAttribute("sumYesterday", orderService.sumYesterday());
		}
		model.addAttribute("sumMoth", orderService.sumMoth());
		return "admin/admin-statistics-revenue";
	}
	
	// info & list user
	@RequestMapping("/admin/info")
	public String adminInfo(Model model, HttpServletRequest request) {
		String Usernameid = request.getRemoteUser();
		model.addAttribute("user", accountService.findById(Usernameid));
		model.addAttribute("countAll", accountService.countAll());
		
		return "admin/admin-info";
	}
	
	// info & list user
	@RequestMapping("/admin/password")
	public String adminPassword(Model model, HttpServletRequest request) {
		String Usernameid = request.getRemoteUser();
		model.addAttribute("user", accountService.findById(Usernameid));
		
		return "admin/admin-password";
	}
}
