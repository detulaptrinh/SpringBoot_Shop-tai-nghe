package com.example.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.Order;
import com.example.service.OrderService;
import com.fasterxml.jackson.databind.JsonNode;

@CrossOrigin("*")
@RestController
public class OrderRestController {
	@Autowired
	OrderService orderService;
	
	@PostMapping("/rest/orders")
	public Order create(@RequestBody JsonNode orderData) {
		return orderService.create(orderData);
	}
	
	@GetMapping("/rest/admin-orders")
	public List<Order> getAllCheck() {
		return orderService.findByCheck();
	}
	
	@PutMapping("/rest/admin-order/{id}")
	public Order update(@PathVariable("id")Integer id, @RequestBody Order order) {
		return orderService.update(order);
	}
	
	// list order day - yesterday - moth- year
	@GetMapping("/rest/day")
	public List<Order> getDay() {
		return orderService.listDay();
	}
	@GetMapping("/rest/yesterday")
	public List<Order> getYesterday() {
		return orderService.listYesterday();
	}
	@GetMapping("/rest/moth")
	public List<Order> getMoth() {
		return orderService.listMoth();
	}
	@GetMapping("/rest/year")
	public List<Order> getYear() {
		return orderService.listYear();
	}
}
