package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Product;
import com.example.service.OrderService;
import com.example.service.ProductService;

@Controller
public class OrderController {
	@Autowired 
	ProductService productService;
	
	@Autowired
	OrderService orderService;
	
	@RequestMapping("/payment")
	public String checkout(Model model) {
		List<Product> list = productService.findAll();
		model.addAttribute("items", list);
		return "payment-cart";
	}

	@RequestMapping("/order/detail/{id}")
	public String detail(Model model, @PathVariable("id") Long id) {
		model.addAttribute("order", orderService.findById(id));
		return "order/detail";
	}
	
}
