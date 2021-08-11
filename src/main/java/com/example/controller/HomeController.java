package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.entity.Product;
import com.example.service.ProductService;

@Controller
public class HomeController {
	@Autowired ProductService productService;
	
	@RequestMapping({"/", "/home"})
	public String home(Model model) {
		List<Product> list = productService.findAll();
		model.addAttribute("items", list);
		return "home";
	}
}
