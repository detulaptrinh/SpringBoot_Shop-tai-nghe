package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Product;
import com.example.service.ProductService;

@Controller
public class ShoppingCartController {
	@Autowired ProductService productService;
	
	@RequestMapping("/shopping/cart")
	public String viewCart(Model model) {
		List<Product> list = productService.findAll();
		model.addAttribute("items", list);
		return "shopping-cart";
	}
}
